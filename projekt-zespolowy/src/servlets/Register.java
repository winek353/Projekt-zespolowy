package servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
    public static boolean containsWhitespace(String str) {
        for (int i = 0; i < str.length(); i++) 
          if (Character.isWhitespace(str.charAt(i))) 
            return true;
        return false;
      }
    
    public static boolean isRegistrationCorrect(String uname, String email, 
    		String password, Object confirmedPassword, ServletRequest request,
    		ServletResponse response) throws ServletException, IOException {
    	
    	if(containsWhitespace(uname) || uname.isEmpty()) {
			request.setAttribute("systemMessage", "Username cannot contain whitespace or be empty");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return false;
		}
		if(containsWhitespace(email) || email.isEmpty()) {
			request.setAttribute("systemMessage", "Email cannot contain whitespace or be empty");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return false;
		}
		if(password.length()<6) {
			request.setAttribute("systemMessage", "Password has to be at least 6 characters long");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return false;
		}	
		if(!password.equals(confirmedPassword)) {
			request.setAttribute("systemMessage", "Passwords are not the same");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return false;
		}			
		if(UserProfile.isUserNameInDatabase(uname)) {
			request.setAttribute("systemMessage", "Username is already registered");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return false;
		}			
		if(UserProfile.isEmailInDatabase(email)) {
			request.setAttribute("systemMessage", "Email is already registered");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return false;
		}
		return true;
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("uname");
		String email = request.getParameter("email");		
		String password = request.getParameter("password");
		String confirmedPassword = request.getParameter("confirmedPassword");

		if(isRegistrationCorrect(uname, email, password, confirmedPassword, request, response) ) {
			UserProfile userProfile = new UserProfile(uname, email, password);
			userProfile.saveToDatabase();
			
			request.setAttribute("systemMessage", "Account has been created");
			request.getRequestDispatcher("/login.jsp").forward(request, response);		
		}
		
	}
}