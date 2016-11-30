package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Checking the action parameter
		String action = request.getParameter("action");

		// If no action is selected
		if (action == null) {
			// Set the index jsp as the home page
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		}
		// IF action selected is 'login'
		else if (action.equals("login")) {
			
			//Setting the above entered attributes so that they are remembered
			request.setAttribute("email", "");
			//Not advised to store password like this as insecure, but this is only for demo purposes
			request.setAttribute("password", "");
			request.setAttribute("validationmessage", "");
			
			// Forward user to the login.jsp page
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Checking the action parameter
		String action = request.getParameter("action");

		// If no action is selected
		if (action == null) {
			// Set the index jsp as the home page
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		}
		// IF action selected is 'dologin'
		else if (action.equals("dologin")) {
			// Validating the entered information
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			//Setting the above entered attributes so that they are remembered
			request.setAttribute("email", email);
			//Not advised to store password like this as insecure, but this is only for demo purposes
			request.setAttribute("password", password);
			
			//Creating new bean
			User user = new User(email, password);
			
			//Calling Validation method
			if(user.validate()) {
				
				//Forward to successful login
				request.getRequestDispatcher("/loginsuccess.jsp").forward(request, response);
			}
			else {
				//Get the error message from class USer
				request.setAttribute("validationmessage", user.getMessage());
				//Forward back to the login page
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
	}

}
