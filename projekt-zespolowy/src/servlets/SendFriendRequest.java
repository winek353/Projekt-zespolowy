package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.ColleagueRequest;
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
		 
	     if(session!=null && session.getAttribute("userName") != null){
	    	 String friendName = request.getParameter("friendName");	
	    	 
	    	if(friendName.equals(session.getAttribute("userName"))) {
	    		out.print("you can not add yourself as a friend (forever alone)");
	    		return;
	    	}  		
	    		
	 		if( UserProfile.isUserNameInDatabase (friendName)) { //and it is not friend already or yourself!!!!!!!!!!!!!!
	 			UserProfile friendProfile = UserProfile.getUserProfileFromDatabase(friendName);
	 			
				
		        String userName=(String)session.getAttribute("userName");  
		        UserProfile userProfile = UserProfile.getUserProfileFromDatabase(userName);
		        
		        ColleagueRequest.sendCollegueRequest (userProfile.getId(), friendProfile.getId());
		        
		        out.print("Friend request sent to " + friendProfile.getUserName()); 
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
