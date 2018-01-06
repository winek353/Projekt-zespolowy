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
import logic.PasswordHasher;

/**
 * Servlet implementation class AddFriend
 */
@WebServlet("/AddFriend")
public class AddFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriend() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session=request.getSession(false);
		 PrintWriter out=response.getWriter();
		 
	     if(session!=null && session.getAttribute("userId") != null){
	    	 int friendRequestId = Integer.parseInt(request.getParameter("request_id"));

	    	 FriendRequest friendRequest = FriendRequest
	    			 .getFriendRequestFromDatabase(friendRequestId);
	    	 
	    	 int friendId = friendRequest.getRequesterId();
	    	
	    	 UserProfile friendProfile = UserProfile.getUserProfileFromDatabase(friendId);
	    	 
	    	 int userId= (int) session.getAttribute("userId");   
		     UserProfile userProfile = UserProfile.getUserProfileFromDatabase(userId);
		     
		     userProfile.addFriend(friendProfile);
		     friendProfile.addFriend(userProfile);
		     
		     friendRequest.deleteFriendRequestFromDatabase ();
		     
		     out.print("you are friend with " + friendProfile.getUserName()); 	
	     }  
	     else{  
	         out.print("Please login first");  
	     }
	}

}
