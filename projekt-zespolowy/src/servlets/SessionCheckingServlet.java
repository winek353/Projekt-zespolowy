package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionCheckingServlet
 */
@WebServlet("/SessionCheckingServlet")
public abstract class SessionCheckingServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session=request.getSession(false);
		if(session!=null && session.getAttribute("loggedInUserId") != null)
		{
			coreDoGet(request,  response);
		}
		else
		{
			response.getWriter().print("Please login first");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session=request.getSession(false);
		if(session!=null && session.getAttribute("loggedInUserId") != null)
		{
			coreDoPost(request,  response);
		}
		else
		{
			response.getWriter().print("Please login first");
		}
	}
	protected abstract void coreDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	protected abstract void coreDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
