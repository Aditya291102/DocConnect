package com.hms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hms.entity.Appointment;

public class AppointmentDAO {

	private Connection conn;

	public AppointmentDAO(Connection conn) {
		super();
		this.conn = conn;
	}
        

	//for create appointment
	public boolean addAppointment(Appointment appointment) {

		boolean f = false;

		try {

			String sql = "insert into appointment(userId, fullName, gender, age, appointmentDate, email, phone, diseases, doctorId, address, status) values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);

			pstmt.setInt(1, appointment.getUserId());
			pstmt.setString(2, appointment.getFullName());
			pstmt.setString(3, appointment.getGender());
			pstmt.setString(4, appointment.getAge());
			pstmt.setString(5, appointment.getAppointmentDate());
			pstmt.setString(6, appointment.getEmail());
			pstmt.setString(7, appointment.getPhone());
			pstmt.setString(8, appointment.getDiseases());
			pstmt.setInt(9, appointment.getDoctorId());
			pstmt.setString(10, appointment.getAddress());
			pstmt.setString(11, appointment.getStatus());

			pstmt.executeUpdate();

			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	// get list of appointment for logged in specific user 
	//show appointment list for specific user panel
	public List<Appointment> getAllAppointmentByLoginUser(int userId) {
		List<Appointment> appList = new ArrayList<Appointment>();

		Appointment appointment = null;

		try {

			String sql = "select * from appointment where userId=?";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);

			pstmt.setInt(1, userId);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				appointment = new Appointment();

				appointment.setId(resultSet.getInt(1));// appoint id
				appointment.setUserId(resultSet.getInt(2));// userId
				appointment.setFullName(resultSet.getString(3));
				appointment.setGender(resultSet.getString(4));
				appointment.setAge(resultSet.getString(5));
				appointment.setAppointmentDate(resultSet.getString(6));
				appointment.setEmail(resultSet.getString(7));
				appointment.setPhone(resultSet.getString(8));
				appointment.setDiseases(resultSet.getString(9));
				appointment.setDoctorId(resultSet.getInt(10));
				appointment.setAddress(resultSet.getString(11));
				appointment.setStatus(resultSet.getString(12));
				appList.add(appointment);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return appList;

	}

	// get appointment list of patient for specific doctor
	//show list of appointment in specific doctor panel 
	public List<Appointment> getAllAppointmentByLoginDoctor(int doctorId) {
		List<Appointment> appList = new ArrayList<Appointment>();

		Appointment appointment = null;

		try {

			String sql = "select * from appointment where doctorId=?";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);

			pstmt.setInt(1, doctorId);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				appointment = new Appointment();

				appointment.setId(resultSet.getInt(1));// appoint id
				appointment.setUserId(resultSet.getInt(2));// userId
				appointment.setFullName(resultSet.getString(3));
				appointment.setGender(resultSet.getString(4));
				appointment.setAge(resultSet.getString(5));
				appointment.setAppointmentDate(resultSet.getString(6));
				appointment.setEmail(resultSet.getString(7));
				appointment.setPhone(resultSet.getString(8));
				appointment.setDiseases(resultSet.getString(9));
				appointment.setDoctorId(resultSet.getInt(10));
				appointment.setAddress(resultSet.getString(11));
				appointment.setStatus(resultSet.getString(12));
				appList.add(appointment);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return appList;

	}

	// for doctor comment need specific appointment id
	public Appointment getAppointmentById(int id) {

		Appointment appointment = null;

		try {

			String sql = "select * from appointment where id=?";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);

			pstmt.setInt(1, id);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				appointment = new Appointment();

				appointment.setId(resultSet.getInt(1));// appoint id
				appointment.setUserId(resultSet.getInt(2));// userId
				appointment.setFullName(resultSet.getString(3));
				appointment.setGender(resultSet.getString(4));
				appointment.setAge(resultSet.getString(5));
				appointment.setAppointmentDate(resultSet.getString(6));
				appointment.setEmail(resultSet.getString(7));
				appointment.setPhone(resultSet.getString(8));
				appointment.setDiseases(resultSet.getString(9));
				appointment.setDoctorId(resultSet.getInt(10));
				appointment.setAddress(resultSet.getString(11));
				appointment.setStatus(resultSet.getString(12));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return appointment;

	}

	// for update comment status
	public boolean updateStatusAndComment(int appointmentId, String status, String comment) {
    boolean result = false;
    try {
        String sql = "UPDATE appointment SET status = ?, comment = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, status);
        ps.setString(2, comment);
        ps.setInt(3, appointmentId);
        result = ps.executeUpdate() == 1;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}

//if (f) {
//    System.out.println("Comment updated successfully in the database.");
//} else {
//    System.out.println("Failed to update comment in the database.");
//}

                
	

	// get all appointment in admin panel
	public List<Appointment> getAllAppointment() {
		List<Appointment> appList = new ArrayList<Appointment>();
		Appointment appointment = null;

		try {

			String sql = "select * from appointment order by id desc";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				appointment = new Appointment();

				appointment.setId(resultSet.getInt(1));// appoint id
				appointment.setUserId(resultSet.getInt(2));// userId
				appointment.setFullName(resultSet.getString(3));
				appointment.setGender(resultSet.getString(4));
				appointment.setAge(resultSet.getString(5));
				appointment.setAppointmentDate(resultSet.getString(6));
				appointment.setEmail(resultSet.getString(7));
				appointment.setPhone(resultSet.getString(8));
				appointment.setDiseases(resultSet.getString(9));
				appointment.setDoctorId(resultSet.getInt(10));
				appointment.setAddress(resultSet.getString(11));
				appointment.setStatus(resultSet.getString(12));
				appList.add(appointment);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return appList;
	}
        public boolean saveAppointment(Appointment appointment) {
    boolean flag = false;
    try {
//        Connection conn = DBConnection.getConn();
        PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO appointments (fullName, email, doctorId, appointmentDate ) VALUES (?, ?, ?, ?)"
        );
        ps.setString(1, appointment.getFullName());
        ps.setString(2, appointment.getEmail());
        ps.setInt(3, appointment.getDoctorId());
        ps.setString(4, appointment.getAppointmentDate());

        int i = ps.executeUpdate();
        if (i == 1) flag = true;

    } catch (Exception e) {
        e.printStackTrace();
    }
    return flag;
}


}
