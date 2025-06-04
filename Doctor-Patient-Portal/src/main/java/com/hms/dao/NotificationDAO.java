package com.hms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hms.entity.Notification;

public class NotificationDAO {
    private Connection conn;

    public NotificationDAO(Connection conn) {
        this.conn = conn;
    }

    // Insert a new notification
    public boolean addNotification(int userId, String message) {
        boolean flag = false;
        try {
            String sql = "INSERT INTO notifications (userId, message) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setString(2, message);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    // Get all notifications for a specific user
    public List<Notification> getNotificationsByUserId(int userId) {
        List<Notification> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM notifications WHERE userId = ? ORDER BY created_at DESC";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Notification notification = new Notification();
                notification.setId(rs.getInt("id"));
                notification.setUserId(rs.getInt("userId"));
                notification.setMessage(rs.getString("message"));
                notification.setStatus(rs.getString("status"));
                notification.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(notification);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Mark a notification as read
    public boolean markAsRead(int notificationId) {
        boolean flag = false;
        try {
            String sql = "UPDATE notifications SET status = 'Read' WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, notificationId);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
