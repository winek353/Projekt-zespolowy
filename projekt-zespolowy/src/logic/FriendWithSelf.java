package logic;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import entity.UserProfile;

public class FriendWithSelf implements SendFriendRequestStrategy {
	private UserProfile userProfile;
	private UserProfile friendProfile;
	HttpServletResponse response;
	

	public FriendWithSelf(UserProfile userProfile, UserProfile friendProfile, HttpServletResponse response) {
		super();
		this.userProfile = userProfile;
		this.friendProfile = friendProfile;
		this.response = response;
	}


	@Override
	public void execute(UserProfile userProfile, UserProfile friendProfile, HttpServletResponse response) {
		try {
			response.sendRedirect("http://i3.kym-cdn.com/photos/images/newsfeed/000/150/505/f30fd24c56e1bcfc926883d6a51d5a00.gif");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
