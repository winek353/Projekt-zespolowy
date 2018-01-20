package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Event;
import entity.UserProfile;

/**
 * Servlet implementation class CreateEvent
 */
@WebServlet("/CreateEvent")
public class CreateEvent extends SessionCheckingServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CreateEvent() {
        // TODO Auto-generated constructor stub
    }


	@Override
	protected void coreDoGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void coreDoPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);
		PrintWriter out=response.getWriter();
		
		String eventName = request.getParameter("eventName");
		
		//check if event name already exist
		int userId= (int) session.getAttribute("loggedInUserId");  
		UserProfile userProfile = UserProfile.getHibernateFacade().getFromDatabase(userId);
		
		Event event = new Event(eventName, userProfile.getId());
		event.create();
	}

}
