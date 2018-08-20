package mbj.server.db;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;;

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
	
	public T ExcuteSQL(Connection conn,String sql) {
		T model=null;
		try {
			// SQL测试语句
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
	 * 从数据库读取数据
	 */
	public abstract T getModel(ResultSet rs);
	/*
	 * 写入数据
	 */
	public abstract int setModel(Connection connection);
}
