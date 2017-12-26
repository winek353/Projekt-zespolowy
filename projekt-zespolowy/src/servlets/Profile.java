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
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
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
	        String usetName=(String)session.getAttribute("userName");  
	        UserProfile userProfile = UserProfile.getUserProfileFromDatabase(usetName);
	        
	        String docType =
	                "<!doctype html public \"-//w3c//dtd html 4.0 " +
	                "transitional//en\">\n";

	             out.println(docType +
	                "<html>\n" +
	                   "<head><title>" + "title" + "</title></head>\n" +
	                   "<body>" +
	                    	"<table border = \\1\\>" +
	                    	 "<tr>" +
                       			"<th>" + "user name:" + "</th>" + "<th>" +  userProfile.getUserName() +"</th>" +
                       		 "</tr>" +
                       			
                       		"<tr>" +
                   				"<th>" + "email:" + "</th>" + "<th>" +  userProfile.getEmail() +"</th>" +
                   			"</tr>" + 
	                    	"</table>" +
	                   "</body>" +
	               "</html>"
	             );
	        
	        //out.print("Hello, "+name+" Welcome to Profile");  
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
		// TODO Auto-generated method stub
		//doGet(request, response);
		
	}

}
