package guepardoapps.common.classes;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1533208191654743604L;
	
	private String _userName;
	private String _password;

	public User(String userName, String password) {
		_userName = userName;
		_password = password;
	}

	public String GetUserName() {
		return _userName;
	}

	public String GetPassword() {
		return _password;
	}
}