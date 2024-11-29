package com.example.jdbcemaifetchernew.servlet;

import com.example.jdbcemaifetchernew.util.DBUTIL;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteEmailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        try(Connection conn= DBUTIL.getConnection();
            PreparedStatement ps=conn.prepareStatement("delete from emails where id=?")){
            ps.setInt(1,id);
            ps.executeUpdate();
            resp.sendRedirect("welcome");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
