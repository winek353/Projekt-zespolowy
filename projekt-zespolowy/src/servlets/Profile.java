package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.UserProfile;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends SessionCheckingServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }
	@Override
	protected void coreDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);
		int userId= (int) session.getAttribute("loggedInUserId");  
	     UserProfile userProfile = UserProfile.getUserProfileFromDatabase(userId);
	     
	     request.setAttribute("friends", userProfile.getFriends());
	     request.setAttribute("userName", userProfile.getUserName());
	     request.setAttribute("userEmail", userProfile.getEmail());
	     request.getRequestDispatcher("/profile.jsp").forward(request, response);
	}

	@Override
	protected void coreDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
