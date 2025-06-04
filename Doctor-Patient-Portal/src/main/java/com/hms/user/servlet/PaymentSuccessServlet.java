package com.hms.user.servlet;

import com.hms.dao.AppointmentDAO;
import com.hms.db.DBConnection;
import com.hms.entity.Appointment;
import com.hms.entity.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/payment-success")
public class PaymentSuccessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        int doctorId = Integer.parseInt(req.getParameter("doctor"));
        String date = req.getParameter("date");

        AppointmentDAO dao = new AppointmentDAO(DBConnection.getConn());
        Appointment appointment = new Appointment(name, email, doctorId, date);

        boolean success = dao.saveAppointment(appointment);

        if (success) {
            // Send email notification
            String subject = "Appointment Confirmation";
            String message = "Dear " + name + ",\n\nYour appointment is successfully booked with Dr. " + doctorId +
                             " on " + date + ".\n\nThank you!";
            EmailUtil.sendEmail(email, subject, message);

            resp.sendRedirect("appointment_success.jsp");
        } else {
            resp.sendRedirect("error.jsp");
        }
    }
}
