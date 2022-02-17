package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Appointment;
import model.Client;
import service.ClientService;
import view.ClientView;

@WebServlet({"/cregister","/clogin"
	,"/chome","/cappointments","/cbook-appointment","/clogout"
	,"/cinsert-appointment","/cinsert-client","/ccancel-appointment","/cauthenticate"})
public class ClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientService clientService;
	private Client client;
   
    public ClientController() {
        clientService=new ClientService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getServletPath();
		
		switch(action) {
			case "/cregister":
				ClientView.registerForm(request, response);
				break;
			case "/clogin":
				ClientView.loginForm(request, response);
				break;
				
			case "/chome":
				home(request, response);
				break;
			case "/cappointments":
				appointments(request, response);
				break;
			case "/cbook-appointment":
				bookAppointment(request, response);
				break;
			case "/clogout":
				logout(request, response);
				break;
				
			case "/cinsert-client":
				insertClient(request, response);
				break;	
			case "/cinsert-appointment":
				insertAppointment(request, response);
				break;
			case "/ccancel-appointment":
				cancelAppointment(request, response);
				break;
			case "/cauthenticate":
				authenticate(request, response);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClientView.home(request, response);
	}
	private void appointments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Appointment> appointmentList=clientService.getAppointmentBookings(client.getId());
		request.setAttribute("appointmentBookings", appointmentList);
		
		ClientView.appointments(request, response);
	}
	private void bookAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClientView.addAppointmentForm(request, response);
	}
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.client=null;
		ClientView.loginForm(request, response);
	}
	
	private void insertClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		boolean isRegistered=false;
		String name=request.getParameter("name");
		String phoneNumber=request.getParameter("phone-number");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String password=request.getParameter("password");
		
		String petName=request.getParameter("pet-name");
		String petType=request.getParameter("pet-type");
		String petBreed=request.getParameter("pet-breed");
		String petDob=request.getParameter("pet-dob");
		
		int petId=clientService.addPet(petName, petType, petBreed, petDob);
		if(petId>0) {
			isRegistered=clientService.register(name, phoneNumber, email, address, password, petId);
			if(isRegistered)
				ClientView.loginForm(request, response);
			else
				ClientView.registerForm(request, response);
		}else
			ClientView.registerForm(request, response);
	}
	private void insertAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String date=request.getParameter("date");
		int slot=Integer.parseInt(request.getParameter("slot"));
		boolean isBooked=false;
		
		isBooked=clientService.bookAppointment(date, slot, client.getId(), client.getPet().getId(), client.getPet().getType());
		
	}
	private void cancelAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int appointmentId=Integer.valueOf(request.getParameter("ap-id"));
		boolean isCancelled=false;
		
		isCancelled=clientService.cancelAppointment(appointmentId);
		
	}
	private void authenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String userId=request.getParameter("user-id");
		String password=request.getParameter("password");
		
		client=clientService.login(userId, password);
		if(client!=null)
			ClientView.home(request, response);
		else
			ClientView.loginForm(request, response);
	}
}
