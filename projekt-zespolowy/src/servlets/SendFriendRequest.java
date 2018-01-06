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

/**
 * Servlet implementation class SendFriendRequest
 */
@WebServlet("/SendFriendRequest")
public class SendFriendRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendFriendRequest() {
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
	    	 String friendName = request.getParameter("friendName");	
	    	 int userId= (int) session.getAttribute("userId");
	    	 UserProfile userProfile = UserProfile.getUserProfileFromDatabase(userId);
	    	 
	    	if(friendName.equals(userProfile.getUserName())) {
	    		out.print("you can not add yourself as a friend (forever alone)");
	    		response.sendRedirect("http://i3.kym-cdn.com/photos/images/newsfeed/000/150/505/f30fd24c56e1bcfc926883d6a51d5a00.gif");
	    		return;
	    	}  		
	    		
	 		if( UserProfile.isUserNameInDatabase (friendName)) { //and it is not friend already!!!!!!!!!!!!!!
	 			UserProfile friendProfile = UserProfile.getUserProfileFromDatabase(friendName);
	 			if(friendProfile.isFriendRequestSent(userId)) {
	 				out.print("already sent");
	 				return;
	 			}
	 			
	 			if(!userProfile.isFriend(friendProfile.getId())) {
	 				FriendRequest.sendFriendRequest (userProfile.getId(), friendProfile.getId());
			        
			        out.print("Friend request sent to " + friendProfile.getUserName());
	 			}
	 			else
	 				out.print("you are friends already"); 
		         
	 		}
	 		else {
	 			out.print("your friend doesn't exist :D");  
	 		}
	     }  
	     else{  
	         out.print("Please login first");  
	     }
	}

}
