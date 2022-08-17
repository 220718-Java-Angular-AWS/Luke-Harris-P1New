package com.revature.daos;

import com.revature.pojos.Requests;
import com.revature.services.ConnectionManager;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RequestsDAO implements DatasourceCRUD<Requests> {
    Connection connection;
    public RequestsDAO() {
        connection = ConnectionManager.connectionManager.getConnection();

    }
    @Override
    public void create(Requests requests) {
        try {
            String sql = "INSERT INTO requests (user_id, reason_for_reimbursement, reason_for_request, amount_requested) VALUES (?, ?, ?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, requests.getUser_id());
            pstmt.setString(2, requests.getReason_for_reimbursement());
            pstmt.setString(3, requests.getReason_for_request());
            pstmt.setDouble(4, requests.getAmount_requested());


            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Requests read(int id) {
        Requests requests = new Requests();
        try {
            String sql = "SELECT * FROM requests WHERE request_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet results = pstmt.executeQuery();

            if(results.next()) {
                requests.setRequest_id(results.getInt("request_id"));
                requests.setUser_id(results.getInt("user_id"));
                requests.setReason_for_reimbursement(results.getString("reason_for_reimbursement"));
                requests.setReason_for_request(results.getString("reason_for_request"));
                requests.setAmount_requested(results.getDouble("amount_requested"));
                requests.setApproved_denied(results.getBoolean("approved_denied"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public List<Requests> readAll() {
        List<Requests> requestsList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM requests";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();

            while (results.next()) {
                Requests requests = new Requests();
                requests.setRequest_id(results.getInt("request_id"));
                requests.setUser_id(results.getInt("user_id"));
                requests.setReason_for_reimbursement(results.getString("reason_for_reimbursement"));
                requests.setReason_for_request(results.getString("reason_for_request"));
                requests.setAmount_requested(results.getDouble("amount_requested"));
                requests.setApproved_denied(results.getBoolean("approved_denied"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestsList;
    }

    @Override
    public void update(Requests requests) {
        try {
            String sql = "UPDATE requests SET request_id = ?, user_id = ?, reason_for_reimbursement = ?, reason_for_request = ?, amount_requested = ?, approved_denied = false";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, requests.getRequest_id());
            pstmt.setInt(2, requests.getUser_id());
            pstmt.setString(3, requests.getReason_for_reimbursement());
            pstmt.setString(4, requests.getReason_for_request());
            pstmt.setDouble(5, requests.getAmount_requested());
            pstmt.executeUpdate();
            ResultSet id = pstmt.getGeneratedKeys();
            if(id.next()) {
                Integer key = id.getInt("request_id");
                System.out.println("Request ID: " +key);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM requests WHERE request_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
