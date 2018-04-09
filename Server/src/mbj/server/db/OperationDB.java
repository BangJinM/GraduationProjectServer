package mbj.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

public class OperationDB {
	static ConnectionPool connPool = ConnectionPool.GetPoolInstance();// 单例模式创建连接池对象
	public static List<PlayerInformation> QueryPlayerWithAccountName(String account) {
		List<PlayerInformation> players=new ArrayList();
		String sql="SELECT * FROM `userinformation` WHERE `Player_Account`="+account;
		try {
			// SQL测试语句
			Connection conn = connPool.getConnection(); // 从连接库中获取一个可用的连接
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				PlayerInformation playerInformation=new PlayerInformation();
				System.out.println(rs.getInt("Player_ID"));
				System.out.println(rs.getInt("Player_Account"));
				System.out.println(rs.getInt("Player_Password"));
				System.out.println(rs.getInt("Player_NickName"));
				playerInformation.setPlayer_ID(rs.getInt("Player_ID"));
				playerInformation.setPlayer_Account(rs.getString("Player_Account"));
				playerInformation.setPlayer_NickName(rs.getString("Player_Password"));
				playerInformation.setPlayer_Password(rs.getString("Player_NickName"));
				players.add(playerInformation);
			}
			rs.close();
			stmt.close();
			connPool.returnConnection(conn);// 连接使用完后释放连接到连接池
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("sql语句为："+sql+"查询成功！/n");
		return players;
	}

	public boolean DeleteDB(String sql) {
		return executeSQL(sql);
	}
	

	public boolean updateDB(String sql) {
		return executeSQL(sql);
	}
	
	@SuppressWarnings("finally")
	private boolean executeSQL(String sql){
		try {

			Connection conn = connPool.getConnection(); // 从连接库中获取一个可用的连接
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.close();
			stmt.close();
			connPool.returnConnection(conn);// 连接使用完后释放连接到连接池
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			return true;
		}
	}
}
