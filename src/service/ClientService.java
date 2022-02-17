package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.Appointment;
import model.Client;
import model.Pet;
import model.Trainer;

public class ClientService implements User{
	public int addPet(String name,String type,String breed,String dob) {
		int result=0;
		String query="INSERT INTO"
				+" pet"
				+" (pt_name,pt_type,pt_breed,pt_dob)"
				+" VALUES(?,?,?,?)";
		Connection conn=JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		
		try {
			ps=conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1,name);
			ps.setString(2,type);
			ps.setString(3,breed);
			ps.setString(4,dob);
			
			ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			generatedKeys.next();
			return generatedKeys.getInt(1);
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
		
		return result;
	}
	
	public boolean register(String name,String phone,String email,String address,String password,int petId){
		String query="INSERT INTO"
				+" client"
				+" (cl_name,cl_phone_number,cl_email,cl_address,cl_password,pt_id)"
				+" VALUES(?,?,?,?,?,?)";
		Connection conn=JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		
		try {
			ps=conn.prepareStatement(query);
			
			ps.setString(1,name);
			ps.setString(2,phone);
			ps.setString(3,email);
			ps.setString(4,address);
			ps.setString(5,password);
			ps.setInt(6,petId);
			
			int result=ps.executeUpdate();
			if(result==1)
				return true;
			else
				return false;
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
		
		return false;
	}
	
	public List<Appointment> getAppointmentBookings(int id) {
		List<Appointment> bookingList=new ArrayList<>();
		String query="SELECT * FROM appointment "
				+ "INNER JOIN trainer ON appointment.tr_id=trainer.tr_id "
				+ "WHERE cl_id="+id;
		Connection conn=JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=conn.prepareStatement(query);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				Appointment appointment=new Appointment();
				Trainer trainer=new Trainer();
				
				trainer.setId(rs.getInt("tr_id"));
				trainer.setName(rs.getString("tr_name"));
				trainer.setPhoneNumber(rs.getString("tr_phone_number"));
				trainer.setEmail(rs.getString("tr_email"));
				trainer.setAddress(rs.getString("tr_address"));
				trainer.setType(rs.getString("tr_type"));
				
				appointment.setTrainer(trainer);
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
	
	public boolean checkSlotAvailability(String date,String type,int slot) {
		boolean isSlotAvailable=false;
		String query="SELECT * FROM appointment"
				+ " INNER JOIN trainer ON appointment.tr_id=trainer.tr_id"
				+ " WHERE tr_type='"+type+"' AND ap_date='"+date+"' AND ap_slot="+slot;
		Connection conn = JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=conn.prepareStatement(query);
			rs=ps.executeQuery();
			
			if(rs.next())
				return isSlotAvailable;
			else
				return true;
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
		
		return isSlotAvailable;
	}
	public int assignTrainer(String type) {
		int trainerId=0;
		String query="SELECT * FROM trainer"
				+ " WHERE tr_type='"+type+"'";
		Connection conn = JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=conn.prepareStatement(query);
			rs=ps.executeQuery();
			
			if(rs.next())
				trainerId=rs.getInt("tr_id");
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
		
		return trainerId;
	}
	public boolean addAppointment(String date,int slot,int clientId,int trainerId,int petId) {
		boolean isAdded=false;
		String query="INSERT INTO"
				+ " appointment (ap_date,ap_slot,cl_id,tr_id,pt_id)"
				+" VALUES(?,?,?,?,?)";
		Connection conn=JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		
		try {
			ps=conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1,date);
			ps.setInt(2,slot);
			ps.setInt(3,clientId);
			ps.setInt(4,trainerId);
			ps.setInt(5,petId);
			
			int result=ps.executeUpdate();
			
			if(result==1)
				isAdded=true;
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
		
		return isAdded;
	}
	
	public boolean bookAppointment(String date,int slot,int clientId,int petId,String petType) {
		boolean isSlotAvailable=false;
		boolean isBooked=false;
		
		isSlotAvailable=checkSlotAvailability(date,petType,slot);
		if(isSlotAvailable) {
			int trainerId=assignTrainer(petType);
			if(trainerId!=0)
				isBooked=addAppointment(date,slot,clientId,trainerId,petId);	
		}
		
		return isBooked;
	}
	
	@Override
	public Client login(String userId,String password) {
		String query="SELECT * FROM client "
				+ "INNER JOIN pet ON client.pt_id=pet.pt_id "
				+ "WHERE cl_email=? AND cl_password=?";
		Connection conn = JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=conn.prepareStatement(query);
			
			ps.setString(1,userId);
			ps.setString(2,password);
			
			rs=ps.executeQuery();
			if(rs.next()) {
				Client client=new Client();
				Pet pet=new Pet();
				
				pet.setId(rs.getInt("pt_id"));
				pet.setName(rs.getString("pt_name"));
				pet.setDob(LocalDate.parse(rs.getString("pt_dob"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				pet.setType(rs.getString("pt_type"));
				pet.setBreed(rs.getString("pt_breed"));
				
				client.setId(rs.getInt("cl_id"));
				client.setName(rs.getString("cl_name"));
				client.setPhoneNumber(rs.getString("cl_phone_number"));
				client.setEmail(rs.getString("cl_email"));
				client.setAddress(rs.getString("cl_address"));
				client.setPet(pet);
				
				return client;
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
		
		return null;
	}
	
	public boolean cancelAppointment(int bookingId) {
		boolean isCanceled=false;
		
		String query="SELECT * FROM appointment"
				+ " WHERE ap_id='"+bookingId+"'";
		Connection conn = JDBC.getInstance().getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=conn.prepareStatement(query);
			rs=ps.executeQuery();
			
			if(rs.next())
				if(!rs.getString("ap_status").equalsIgnoreCase("completed")) {
					String queryu="UPDATE appointment SET ap_status='Cancelled' WHERE ap_id="+bookingId;
					PreparedStatement psu=conn.prepareStatement(queryu);
					int result=psu.executeUpdate();
					if(result==1)
						isCanceled=true;
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
		
		return isCanceled;
	}
}
