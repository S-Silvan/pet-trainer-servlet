package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.Appointment;
import model.Client;
import model.Pet;
import model.Profile;
import model.Trainer;

public class AdminService implements User{
	@Override
	public Profile login(String userId, String password) {
		Profile admin=null;
		String query="SELECT * FROM admin "
				+ "WHERE ad_email=? AND ad_password=?";
		Connection conn = JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=conn.prepareStatement(query);
			
			ps.setString(1,userId);
			ps.setString(2,password);
			
			rs=ps.executeQuery();
			if(rs.next()) {
				admin=new Admin();
				
				admin.setId(rs.getInt("ad_id"));
				admin.setName(rs.getString("ad_name"));
				admin.setPhoneNumber(rs.getString("ad_phone_number"));
				admin.setEmail(rs.getString("ad_email"));
				admin.setAddress(rs.getString("ad_address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(ps!=null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return admin;
	}
	public List<Appointment> getAppointments() {
		List<Appointment> bookingList=new ArrayList<>();
		String query="SELECT * FROM appointment "
				+ "INNER JOIN client ON appointment.cl_id=client.cl_id "
				+ "INNER JOIN trainer ON appointment.tr_id=trainer.tr_id "
				+ "INNER JOIN pet ON client.pt_id=pet.pt_id";
		Connection conn=JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=conn.prepareStatement(query);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				Appointment appointment=new Appointment();
				Client client=new Client();
				Pet pet=new Pet();
				Trainer trainer=new Trainer();
				
				client.setId(rs.getInt("cl_id"));
				client.setName(rs.getString("cl_name"));
				client.setPhoneNumber(rs.getString("cl_phone_number"));
				client.setEmail(rs.getString("cl_email"));
				client.setAddress(rs.getString("cl_address"));
				
				trainer.setId(rs.getInt("tr_id"));
				trainer.setName(rs.getString("tr_name"));
				trainer.setPhoneNumber(rs.getString("tr_phone_number"));
				trainer.setEmail(rs.getString("tr_email"));
				trainer.setAddress(rs.getString("tr_address"));
				trainer.setType(rs.getString("tr_type"));
				
				pet.setId(rs.getInt("pt_id"));
				pet.setName(rs.getString("pt_name"));
				pet.setType(rs.getString("pt_type"));
				pet.setBreed(rs.getString("pt_breed"));
				pet.setDob(LocalDate.parse(rs.getString("pt_dob"),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				
				appointment.setTrainer(trainer);
				appointment.setPet(pet);
				appointment.setClient(client);
				appointment.setId(rs.getInt("ap_id"));
				appointment.setDate(LocalDate.parse(rs.getString("ap_date"),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				appointment.setSlot(rs.getInt("ap_slot"));
				appointment.setStatus(rs.getString("ap_status"));
				
				bookingList.add(appointment);
			}
			
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}finally {
			if(ps!=null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return bookingList;
	}
	public List<Trainer> getTrainers() {
		List<Trainer> trainerList=new ArrayList<>();
		String query="SELECT * FROM trainer";
		Connection conn=JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=conn.prepareStatement(query);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				Trainer trainer=new Trainer();
				
				trainer.setId(rs.getInt("tr_id"));
				trainer.setName(rs.getString("tr_name"));
				trainer.setPhoneNumber(rs.getString("tr_phone_number"));
				trainer.setEmail(rs.getString("tr_email"));
				trainer.setAddress(rs.getString("tr_address"));
				trainer.setType(rs.getString("tr_type"));
				
				trainerList.add(trainer);
			}
			
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}finally {
			if(ps!=null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return trainerList;
	}
	public boolean addTrainer(String name,String phoneNumber,String email,String address,String password,String type) {
		boolean isTrainerAdded=false;
		
		String query="INSERT INTO"
				+" trainer"
				+" (tr_name,tr_phone_number,tr_email,tr_address,tr_password,tr_type)"
				+" VALUES(?,?,?,?,?,?)";
		Connection conn=JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		
		try {
			ps=conn.prepareStatement(query);
			
			ps.setString(1,name);
			ps.setString(2,phoneNumber);
			ps.setString(3,email);
			ps.setString(4,address);
			ps.setString(5,password);
			ps.setString(6,type);
			
			int result=ps.executeUpdate();
			if(result==1)
				isTrainerAdded=true;
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}finally {
			if(ps!=null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return isTrainerAdded;
	}
}
