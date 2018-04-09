package mbj.server.db;

public class PlayerInformation {
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
}
