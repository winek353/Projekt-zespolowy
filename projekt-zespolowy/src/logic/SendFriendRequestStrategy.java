package logic;

import javax.servlet.http.HttpServletResponse;

import entity.UserProfile;

public interface SendFriendRequestStrategy {
	public void execute(UserProfile userProfile,UserProfile friendProfile, HttpServletResponse response);
}
