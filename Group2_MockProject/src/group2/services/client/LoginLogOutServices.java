package group2.services.client;

import java.util.List;

import group2.models.User;
import group2.utils.Constant;
import group2.utils.File;

public class LoginLogOutServices {
	
	//Check user/admin
	public int checkRole(String name, String pass) {
		List<User> listOfUsers = File.read(Constant.USER_PATH);
		int role = -1;
		for(User user : listOfUsers) {
			if(name.equals(user.getUser_name()) & pass.equals(user.getPassword())) {
				role = user.getRole();
			}
		}
		return role;
	}

}
