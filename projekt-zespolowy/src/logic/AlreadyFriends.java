package logic;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import entity.UserProfile;

public class AlreadyFriends implements SendFriendRequestStrategy {
	private UserProfile userProfile;
	private UserProfile friendProfile;
	HttpServletResponse response;
	
	public AlreadyFriends(UserProfile userProfile, UserProfile friendProfile, HttpServletResponse response) {
		super();
		this.userProfile = userProfile;
		this.friendProfile = friendProfile;
		this.response = response;
	}

	@Override
	public void execute(UserProfile userProfile, UserProfile friendProfile, HttpServletResponse response) {
		
		try {
			response.getWriter().print("you are already friend with " + friendProfile.getUserName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
