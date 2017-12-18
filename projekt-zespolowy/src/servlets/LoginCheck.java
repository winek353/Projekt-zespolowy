package servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.UserProfile;
import logic.PasswordHasher;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String param = request.getParameter("param");
		response.getWriter().append("<html><b>Hello World</b> <br>param = ").append(param).append(" </html>");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//cos
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");
		
		System.out.print(uname);
		UserProfile userProfile = UserProfile.getUserProfileFromDatabase(uname);
		
		if(userProfile != null && PasswordHasher.validatePassword(password, userProfile.getPassword()) ) {
			response.sendRedirect("member.jsp");
			//utworzenie sesji 
		}
		else 
			response.sendRedirect("error.jsp");
	}

}
