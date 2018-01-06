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
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessage() {
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
	    	 String text = request.getParameter("message");
	 		 String recipient = request.getParameter("recipient");
	 		 System.out.println("Do = " + recipient+ " " + "text = " + text);
	 		 
	 		int userId= (int) session.getAttribute("userId");  
	 		UserProfile sender = UserProfile.getUserProfileFromDatabase(userId);
	    	UserProfile recipent = UserProfile.getUserProfileFromDatabase(recipient);
	    	 
	 		 
	 		 Message message = new Message(text, sender.getId());
	 		 message.send(sender.getId(), recipent.getId());
	 		 
	 		out.print("Message sent");  
		     
	     }  
	     else{  
	         out.print("Please login first");  
	         //request.getRequestDispatcher("login.html").include(request, response);  
	         //wyswietlanie bledu dopiero na stronie logowania
	     }
	
		
	}

}
