package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Appointment;
import model.Trainer;
import service.TrainerService;
import view.TrainerView;

@WebServlet({"/tlogin",
	"/thome","/tappointments","/tlogout"
	,"/tupdate-appointment-status","/tauthenticate"})
public class TrainerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Trainer trainer;
	TrainerService trainerService;
   
    public TrainerController() {
    	trainerService=new TrainerService();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getServletPath();
		
		switch(action) {
			case "/tlogin":
				TrainerView.loginForm(request, response);
				break;
				
			case "/thome":
				home(request, response);
				break;
			case "/tappointments":
				appointments(request, response);
				break;
			case "/tlogout":
				logout(request, response);
				break;
				
			case "/tupdate-appointment-status":
				updateAppointmentStatus(request, response);
				break;
			case "/tauthenticate":
				authenticate(request, response);
				break;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TrainerView.home(request, response);
	}
	public void appointments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Appointment> appointmentList=trainerService.getAppointmentBookings(trainer.getId());
		request.setAttribute("appointmentBookings", appointmentList);
		
		TrainerView.appointments(request, response);
	}
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.trainer=null;
		
		TrainerView.loginForm(request, response);
	}
	
	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String userId=request.getParameter("user-id");
		String password=request.getParameter("password");
		
		System.out.println(userId+"="+password);
		trainer=(Trainer) trainerService.login(userId, password);
		if(trainer!=null)
			TrainerView.home(request, response);
		else 
			TrainerView.loginForm(request, response);
	}
	public void updateAppointmentStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int appointmentId=Integer.parseInt(request.getParameter("ap-id"));
		boolean isCompleted=false;
		
		isCompleted=trainerService.completeAppointment("Completed", appointmentId);
		
	}
}
