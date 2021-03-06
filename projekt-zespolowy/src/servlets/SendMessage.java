package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Message;
import entity.UserProfile;

/**
 * Servlet implementation class SendMessage
 */
@WebServlet("/SendMessage")
public class SendMessage extends SessionCheckingServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessage() {
        super();
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
		
		String text = request.getParameter("message");
		String recipient = request.getParameter("recipient");
		 
		System.out.println("by�em  w send message");
		if(UserProfile.isUserNameInDatabase(recipient)) {
			int userId= (int) session.getAttribute("loggedInUserId");  
			//UserProfile sender = UserProfile.getUserProfileFromDatabase(userId);
			UserProfile sender = UserProfile.getHibernateFacade().getFromDatabase(userId);
			UserProfile recipent = UserProfile.getUserProfileFromDatabase(recipient);
 
			Message message = new Message(text, sender.getId());
			message.send(sender.getId(), recipent.getId()); 
			out.print("Message sent"); 
		}
		else
			out.print("Recipent does't exist"); 
		 
		
	}

}
