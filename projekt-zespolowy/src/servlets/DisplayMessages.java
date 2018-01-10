package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Message;
import entity.UserProfile;

/**
 * Servlet implementation class DisplayMessages
 */
@WebServlet("/DisplayMessages")
public class DisplayMessages extends SessionCheckingServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayMessages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void coreDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false); 
		int userId= (int) session.getAttribute("loggedInUserId");  
	     UserProfile userProfile = UserProfile.getUserProfileFromDatabase(userId);

	     request.setAttribute("messages", userProfile.getMessages());
	     request.getRequestDispatcher("/messages.jsp").forward(request, response);
		
	}

	@Override
	protected void coreDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
