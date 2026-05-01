package com.example.servlet_day_3;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String course = request.getParameter("course");
        String condition = request.getParameter("terms");

        if (condition == null) {
            out.println("<h2>Please accept Terms & Conditions</h2>");
            return;
        }

        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO users(name, email, password, gender, course) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, gender);
            ps.setString(5, course);

            int result = ps.executeUpdate();

            if (result > 0) {
                out.println("<h2>Registration Successful</h2>");
            } else {
                out.println("<h2>Registration Failed</h2>");
            }

            ps.close();
            con.close();

            response.sendRedirect("RegisterServlet");

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h2>User List</h2>");
        out.println("<table border='1'>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Name</th>");
        out.println("<th>Email</th>");
        out.println("<th>Gender</th>");
        out.println("<th>Course</th>");
        out.println("</tr>");

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT id, name, email, gender, course FROM users";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("gender") + "</td>");
                out.println("<td>" + rs.getString("course") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
