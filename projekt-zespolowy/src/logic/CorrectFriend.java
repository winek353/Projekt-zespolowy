package logic;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import entity.FriendRequest;
import entity.UserProfile;

public class CorrectFriend implements SendFriendRequestStrategy {
	private UserProfile userProfile;
	private UserProfile friendProfile;
	HttpServletResponse response;
	
	
	
	public CorrectFriend(UserProfile userProfile, UserProfile friendProfile, HttpServletResponse response) {
		super();
		this.userProfile = userProfile;
		this.friendProfile = friendProfile;
		this.response = response;
	}

	@Override
	public void execute(UserProfile userProfile, UserProfile friendProfile, HttpServletResponse response) {

		FriendRequest.sendFriendRequest (userProfile.getId(), friendProfile.getId());
        try {
			response.getWriter().print("Friend request sent to " + friendProfile.getUserName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
