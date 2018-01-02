package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.UserProfile;

/**
 * Servlet implementation class DisplayFriendRequests
 */
@WebServlet("/DisplayFriendRequests")
public class DisplayFriendRequests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayFriendRequests() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		 PrintWriter out=response.getWriter();
		 
	     if(session!=null && session.getAttribute("userName") != null){ 
	    	 String userName=(String)session.getAttribute("userName");  
		     UserProfile userProfile = UserProfile.getUserProfileFromDatabase(userName);

		     request.setAttribute("colleagueRequests", userProfile.getColleagueRequests());
		     request.getRequestDispatcher("/friends.jsp").forward(request, response);
	     }  
	     else{  
	         out.print("Please login first");  
	         //request.getRequestDispatcher("login.html").include(request, response);  
	         //wyswietlanie bledu dopiero na stronie logowania
	     }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	}

}
