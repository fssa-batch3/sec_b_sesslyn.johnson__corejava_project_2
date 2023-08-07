package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.fssa.minimal.interfaces.AppointmentInterface;
import in.fssa.minimal.model.Appointment;
import in.fssa.minimal.util.ConnectionUtil;

public class AppointmentDAO implements AppointmentInterface{
    @Override
    public void create(Appointment newAppointment) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = "INSERT INTO appointment (from_user, to_user, email, phone_number, status, date, time) VALUES (?,?,?,?,?,?,?)";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, newAppointment.getFromUser());  
            ps.setInt(2, newAppointment.getToUser());  
            ps.setString(3, newAppointment.getEmail());       
            ps.setLong(4, newAppointment.getPhoneNumber());
            ps.setString(5, newAppointment.getStatus());
  
            java.sql.Date date = java.sql.Date.valueOf(newAppointment.getDate());
            ps.setDate(6, date);

            java.sql.Time time = java.sql.Time.valueOf(newAppointment.getTime());
            ps.setTime(7, time);
            
            ps.executeUpdate();
            System.out.println("Appointment has been created successfully");
           
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(conn, ps);
        }
    }


	@Override
	public <T> T findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
