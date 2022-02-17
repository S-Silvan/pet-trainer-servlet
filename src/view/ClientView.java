package view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientView {
	public static void registerForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/client/register.jsp");
		rd.forward(request, response);
	}
	public static void loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/client/login.jsp");
		rd.forward(request, response);
	}
	public static void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/client/dashboard.jsp");
		rd.forward(request, response);
	}
	public static void appointments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/client/view_appointments.jsp");
		rd.forward(request, response);
	}
	public static void addAppointmentForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/client/book_appointment.jsp");
		rd.forward(request, response);
	}
}
