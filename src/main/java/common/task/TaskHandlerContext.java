package common.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public enum TaskHandlerContext {

	/** 枚举单例 */
	INSTANCE;

	private final int CORE_SIZE = Runtime.getRuntime().availableProcessors();
	/** task worker pool */
	private final List<TaskWorker> workerPool = new ArrayList<>();

	private final AtomicBoolean run = new AtomicBoolean(true);
	
	private ConcurrentMap<Thread, AbstractDistributeTask> currentTasks = new ConcurrentHashMap<>();

	private final long MONITOR_INTERVAL = 5000L;
	
	private final long MAX_EXEC_TIME = 30000L;
	
	public void initialize() {
		for (int i=1; i<=CORE_SIZE; i++) {
			TaskWorker worker = new TaskWorker(i);
			workerPool.add(worker);
			new Thread(worker).start();
		}
		new Thread(new TaskMonitor()).start();
	}

	/**
	 * @param task
	 */
	public void acceptTask(AbstractDistributeTask task) {
		if (task == null) {
			throw new NullPointerException("task is null");
		}
		int distributeKey = task.distributeKey() % workerPool.size();
		workerPool.get(distributeKey).addTask(task);
	}

	/**
	 * shut context
	 */
	public void shutDown() {
		run.set(false);
	}

	private class TaskWorker implements Runnable {
		private int i= 0 ;
		/** worker id */
		private int id;
		/** task consumer queue */
		private BlockingQueue<AbstractDistributeTask> taskQueue = new LinkedBlockingQueue<>();

		TaskWorker(int index) {
			this.id = index;
		}

		public void addTask(AbstractDistributeTask task) {
			this.taskQueue.add(task);
		}

		@Override
		public void run() {
			//accept task all the time
			while(run.get()) {
				try {
					i ++;
					System.out.println("task id = " + id+ "  i = " + i );
					AbstractDistributeTask task = taskQueue.take();
					task.markStartMillis();
					Thread t = Thread.currentThread();
					currentTasks.put(t, task);
					task.action();
					currentTasks.remove(t);
					task.actionSuccess();
					task.markEndMillis();
				} catch (Exception e) {
//					logger.error("task Worker" + id, e);
					System.out.println("task Worker" + id);
				}
			}
		}
	}
	
	class TaskMonitor implements Runnable {

		@Override
		public void run() {
			for (; ;) {
				try {
					Thread.sleep(MONITOR_INTERVAL);
				} catch (InterruptedException e) {
				}
				
				for (Map.Entry<Thread, AbstractDistributeTask> entry: currentTasks.entrySet()) {
					Thread t = entry.getKey();
					AbstractDistributeTask task = entry.getValue();
					if (task != null) {
						long now = System.currentTimeMillis();
						if (now - task.getStartMillis() > MAX_EXEC_TIME) {
							System.out.println("执行任务超时");
						}
					}
				}
			}
		}
		
	}
}

