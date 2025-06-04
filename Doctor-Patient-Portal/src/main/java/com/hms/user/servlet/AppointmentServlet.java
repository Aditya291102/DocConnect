package com.hms.user.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hms.dao.DoctorDAO;
import com.hms.entity.Doctor;
import com.hms.dao.AppointmentDAO;
import com.hms.db.DBConnection;
import com.hms.entity.Appointment;
import com.hms.entity.EmailUtil;

@WebServlet("/addAppointment")
public class AppointmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            int userId = Integer.parseInt(req.getParameter("userId"));
            String fullName = req.getParameter("fullName");
            String gender = req.getParameter("gender");
            String age = req.getParameter("age");
            String appointmentDate = req.getParameter("appointmentDate");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            String diseases = req.getParameter("diseases");
            int doctorId = Integer.parseInt(req.getParameter("doctorNameSelect"));
            String address = req.getParameter("address");

            // By default, set status to "Pending"
            Appointment appointment = new Appointment(userId, fullName, gender, age,
                    appointmentDate, email, phone, diseases, doctorId, address, "Pending");
DoctorDAO doctorDAO = new DoctorDAO(DBConnection.getConn());
Doctor doctor = doctorDAO.getDoctorById(doctorId);
    String doctorName = doctor.getFullName();
            AppointmentDAO appointmentDAO = new AppointmentDAO(DBConnection.getConn());
            boolean f = appointmentDAO.addAppointment(appointment);

            if (f) {
                String subject = "Appointment Scheduled Successfully";
                String messageBody = "Dear " + fullName + ",\n\n"
                        + "Your appointment with Doctor: " + doctorName + " has been scheduled for " + appointmentDate + ".\n"
                        + "If you have any questions, feel free to contact us.\n\n"
                        + "Thank you,\nDocConnect Team";

                EmailUtil.sendEmail(email, subject, messageBody); // Send email

                resp.getWriter().write("success");
            } else {
                resp.getWriter().write("error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("error");
        }
    }
}
