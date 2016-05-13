package org.java.nitesh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.java.nitesh.dto.User;
import org.java.nitesh.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id, pw;
		id = request.getParameter("userName");
		pw = request.getParameter("password");
		
		LoginService loginSvc = new LoginService();
		boolean result = loginSvc.authenticate(id, pw);
		if (result) {
			// Can't instantiate printWriter obj and also call sendredirect after that
			// Only use either one
			User user = loginSvc.getUserDetails(id);
			
			/*
			 * Client-side redirection
			 */
			// Save the user object in the session obj for this user 
			// so that the controller can pass it to the view.
			// Can't save it in request obj coz redirect will
			// have the browser send a "new" request
//			request.getSession().setAttribute("user", user);
//			response.sendRedirect("success.jsp");

			/*
			 * Server-side redirection
			 */
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
			dispatcher.forward(request, response);
			
			return;
		} else {	
			response.sendRedirect("login.jsp");
			return;
		}
	}

}
