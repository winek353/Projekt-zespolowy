package logic;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import entity.FriendRequest;
import entity.UserProfile;

public class AlreadySentByFriend implements SendFriendRequestStrategy {
	private UserProfile userProfile;
	private UserProfile friendProfile;
	HttpServletResponse response;

	
	public AlreadySentByFriend(UserProfile userProfile, UserProfile friendProfile, HttpServletResponse response) {
		super();
		this.userProfile = userProfile;
		this.friendProfile = friendProfile;
		this.response = response;
	}

	@Override
	public void execute(UserProfile userProfile, UserProfile friendProfile, HttpServletResponse response) {
		FriendRequest friendRequest = userProfile.getFriendRequest (friendProfile.getId());
		
		userProfile.addFriend(friendProfile);
	    friendProfile.addFriend(userProfile);
	     
	    friendRequest.deleteFriendRequestFromDatabase ();
	    
	    try {
			response.getWriter().print("you are now friend with " + friendProfile.getUserName()
			+ " because request was already sent by "+ friendProfile.getUserName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
