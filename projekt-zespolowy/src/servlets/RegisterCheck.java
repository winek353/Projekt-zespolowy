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
 * Servlet implementation class RegisterCheck
 */
@WebServlet("/RegisterCheck")
public class RegisterCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterCheck() {
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
		
		String uname = request.getParameter("uname");
		String email = request.getParameter("email");		
		String password = request.getParameter("password");
		String confirmedPassword = request.getParameter("confirmedPassword");
		
		if(password.length()>=6){//trzeba co� z tym zrobi� :)
			if(password.equals(confirmedPassword)) {
				if(!UserProfile.isUserNameInDatabase(uname)) {
					if(!UserProfile.isEmailInDatabase(email)) {
						
						UserProfile userProfile = new UserProfile(uname, email, password);
						userProfile.saveToDatabase();
						
						response.getWriter().append("<html><b>konto zostalo utworzone</b>").append(" </html>");
					}
					else
						response.getWriter().append("<html><b>podany email jest zajety</b>").append(" </html>");
				}
				else 
					response.getWriter().append("<html><b>nazwa u�ytkownika jest zajeta</b>").append(" </html>");
			}
			else
				response.getWriter().append("<html><b>podane hasla sa rozne od siebie</b>").append(" </html>");
		}
		else
			response.getWriter().append("<html><b>haslo musi miec co najmniej 6 znakow</b>").append(" </html>");
	}
}
