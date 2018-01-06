package servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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
 * Servlet implementation class Registe
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("uname");
		String email = request.getParameter("email");		
		String password = request.getParameter("password");
		String confirmedPassword = request.getParameter("confirmedPassword");

		
		if(password.length()<6) {
			request.setAttribute("systemMessage", "Password has to be at least 6 characters long");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}	
		if(!password.equals(confirmedPassword)) {
			request.setAttribute("systemMessage", "Passwords are not the same");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}			
		if(UserProfile.isUserNameInDatabase(uname)) {
			request.setAttribute("systemMessage", "Username is already registered");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}			
		if(UserProfile.isEmailInDatabase(email)) {
			request.setAttribute("systemMessage", "Email is already registered");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		UserProfile userProfile = new UserProfile(uname, email, password);
		userProfile.saveToDatabase();
		
		request.setAttribute("systemMessage", "Account has been created");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
}