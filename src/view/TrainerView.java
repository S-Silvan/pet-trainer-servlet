package view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TrainerView {
	public static void loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/trainer/login.jsp");
		rd.forward(request, response);
	}
	public static void appointments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/trainer/view_appointments.jsp");
		rd.forward(request, response);
	}
	public static void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/trainer/dashboard.jsp");
		rd.forward(request, response);
	}
}
