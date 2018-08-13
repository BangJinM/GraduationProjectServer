package mbj.server.db;

import java.sql.SQLException;

import java.sql.Connection;;

public abstract  class OperationDBInterface<T> {
	/*
	 * 获得一个连接
	 */
	public Connection getConnection() {
		Connection connection=null;
		ConnectionPool connectionPool=ConnectionPool.GetPoolInstance();
		try {
			connection=connectionPool.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(connection==null)
			return null;
		else
			return connection;
	}
	/*
	 * 从数据库读取数据
	 */
	public abstract T getModel(Connection connection);
	/*
	 * 写入数据
	 */
	public abstract int setModel(Connection connection);
}
