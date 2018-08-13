package mbj.server.db;

import java.sql.SQLException;

import java.sql.Connection;;

public abstract  class OperationDBInterface<T> {
	/*
	 * ���һ������
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
	 * �����ݿ��ȡ����
	 */
	public abstract T getModel(Connection connection);
	/*
	 * д������
	 */
	public abstract int setModel(Connection connection);
}
