package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Event;
import entity.UserProfile;

/**
 * Servlet implementation class DisplayUserEvents
 */
@WebServlet("/DisplayUserEvents")
public class DisplayUserEvents extends SessionCheckingServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DisplayUserEvents() {
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void coreDoGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);
		int userId= (int) session.getAttribute("loggedInUserId");   
	    UserProfile userProfile = UserProfile.getHibernateFacade().getFromDatabase(userId);

	    request.setAttribute("events", userProfile.getEvents());
	    request.getRequestDispatcher("/events.jsp").forward(request, response);	
		
	}

	@Override
	protected void coreDoPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
