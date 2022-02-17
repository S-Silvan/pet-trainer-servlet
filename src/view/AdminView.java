package view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminView {
	public static void loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/admin/login.jsp");
		rd.forward(request, response);
	}
	public static void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/admin/dashboard.jsp");
		rd.forward(request, response);
	}
	public static void appointments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/admin/view_appointments.jsp");
		rd.forward(request, response);
	}
	public static void trainers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/admin/view_trainers.jsp");
		rd.forward(request, response);
	}
	public static void addTrainerForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/admin/add_trainer.jsp");
		rd.forward(request, response);
	}
}
