package mbj.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlayerInformation extends OperationDBInterface<PlayerInformation>{
	public int 		player_ID;
	public String 	player_NickName;
	public String 	player_Account;
	public String getPlayer_Account() {
		return player_Account;
	}
	public void setPlayer_Account(String player_Account) {
		this.player_Account = player_Account;
	}
	public String 	player_Password;
	
	public int getPlayer_ID() {
		return player_ID;
	}
	public void setPlayer_ID(int player_ID) {
		this.player_ID = player_ID;
	}
	public String getPlayer_NickName() {
		return player_NickName;
	}
	public void setPlayer_NickName(String player_NickName) {
		this.player_NickName = player_NickName;
	}
	public String getPlayer_Password() {
		return player_Password;
	}
	public void setPlayer_Password(String player_Password) {
		this.player_Password = player_Password;
	}
	@Override
	public PlayerInformation getModel(Connection connection) {
		ConnectionPool connPool=ConnectionPool.GetPoolInstance();
		List<PlayerInformation> players=new ArrayList();
		String sql="SELECT * FROM `userinformation` WHERE `Player_Account`="+"";
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
		return players.get(0);
	}
	
	public ResultSet ExcuteSQL(Connection conn,String sql) {
		try {
			// SQL测试语句
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int setModel(Connection connection) {
		// TODO Auto-generated method stub
		return 0;
	}
}
