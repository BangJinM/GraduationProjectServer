package mbj.server.db;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;;

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
	
	public T ExcuteSQL(Connection conn,String sql) {
		T model=null;
		try {
			// SQL�������
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			model=getModel(rs);
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/*
	 * �����ݿ��ȡ����
	 */
	public abstract T getModel(ResultSet rs);
	/*
	 * д������
	 */
	public abstract int setModel(Connection connection);
}
