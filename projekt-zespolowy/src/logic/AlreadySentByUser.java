package logic;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import entity.UserProfile;

public class AlreadySentByUser implements SendFriendRequestStrategy {
	private UserProfile userProfile;
	private UserProfile friendProfile;
	HttpServletResponse response;
	
	public AlreadySentByUser(UserProfile userProfile, UserProfile friendProfile, HttpServletResponse response) {
		super();
		this.userProfile = userProfile;
		this.friendProfile = friendProfile;
		this.response = response;
	}

	@Override
	public void execute(UserProfile userProfile, UserProfile friendProfile, HttpServletResponse response) {
		
		try {
			response.getWriter().print("Friend request was already sent to " + friendProfile.getUserName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
