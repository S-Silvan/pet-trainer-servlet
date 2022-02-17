package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Admin;
import model.Appointment;
import model.Trainer;
import service.AdminService;
import view.AdminView;

@WebServlet({"/alogin",
	"/ahome","/aappointments","/atrainers","/aadd-trainer","/alogout",
	"/ainsert-trainer","/aauthenticate"})
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Admin admin;
    private AdminService adminService;
    
    public AdminController() {
    	adminService=new AdminService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getServletPath();
		
		switch(action) {
			case "/alogin":
				AdminView.loginForm(request, response);
				break;
				
			case "/ahome":
				home(request, response);
				break;
			case "/aappointments":
				appointments(request, response);
				break;
			case "/atrainers":
				trainers(request, response);
				break;
			case "/aadd-trainer":
				addTrainer(request, response);
				break;
			case "/alogout":
				logout(request, response);
				break;
			
			case "/ainsert-trainer":
				insertTrainer(request, response);
				break;
			case "/aauthenticate":
				authenticate(request, response);
				break;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminView.home(request, response);
	}
	private void appointments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Appointment> appointmentList=adminService.getAppointments();
		request.setAttribute("appointmentBookings", appointmentList);
		
		AdminView.appointments(request, response);
	}
	private void trainers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Trainer> trainerList=adminService.getTrainers();
		request.setAttribute("trainers", trainerList);
		
		AdminView.trainers(request, response);
	}
	private void addTrainer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminView.addTrainerForm(request, response);
	}
	
	private void authenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String userId=request.getParameter("user-id");
		String password=request.getParameter("password");
		
		admin=(Admin) adminService.login(userId, password);
		if(admin!=null) 
			AdminView.home(request, response);
		else 
			AdminView.loginForm(request, response);
	}
	private void insertTrainer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		boolean isAdded=false;
		String name=request.getParameter("name");
		String phoneNumber=request.getParameter("phone-number");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String type=request.getParameter("type");
		String address=request.getParameter("address");
		
		isAdded=adminService.addTrainer(name, phoneNumber, email, address, password, type);

	}
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.admin=null;
		
		AdminView.loginForm(request, response);
	}
}
