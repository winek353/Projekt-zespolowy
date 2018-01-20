package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.FriendRequest;
import entity.UserProfile;
import logic.FriendWithSelf;
import logic.SendFriendRequestStrategy;

/**
 * Servlet implementation class SendFriendRequest
 */
@WebServlet("/SendFriendRequest")
public class SendFriendRequest extends SessionCheckingServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendFriendRequest() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	@Override
	protected void coreDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void coreDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		String friendName = request.getParameter("friendName");	
		int userId= (int) session.getAttribute("loggedInUserId");
    	UserProfile userProfile = UserProfile.getHibernateFacade().getFromDatabase(userId);
    	UserProfile friendProfile = UserProfile.getUserProfileFromDatabase(friendName);
    	
    	SendFriendRequestStrategy sendFriendRequestStrategy =
    			FriendRequest.selectSendFriendRequestStrategy(userProfile, friendProfile, response);
    	
    	sendFriendRequestStrategy.execute(userProfile, friendProfile, response);
	}

}
