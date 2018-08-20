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
	public PlayerInformation getModel(ResultSet rs) {
		List<PlayerInformation> players=new ArrayList();
			try {
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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return players.get(0);
	}
	
	@Override
	public int setModel(Connection connection) {
		// TODO Auto-generated method stub
		return 0;
	}
}
