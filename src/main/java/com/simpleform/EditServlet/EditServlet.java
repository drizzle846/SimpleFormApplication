package com.simpleform.EditServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/edit/{id}")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the ID from the query parameter
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_db", "postgres", "********");

            // Prepare the SQL query to fetch the entry with the given ID
            String sql = "SELECT * FROM your_table WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            // Execute the query
            resultSet = statement.executeQuery();

            // Process the result set to retrieve the data for editing
            if (resultSet.next()) {
                // Retrieve the data from the result set
                int retrievedId = resultSet.getInt("id");
                String retrievedFullname = resultSet.getString("fullname");
                String retrievedCity = resultSet.getString("city");
				String retrievedMobile = resultSet.getString("mobile");
				String retrievedEmail = resultSet.getString("email");
                // Generate the HTML form pre-filled with the retrieved data
                String form = "<form action='update' method='post'>" +
                        "<input type='hidden' name='id' value='" + retrievedId + "'>" +
                        "Name: <input type='text' name='fullname' value='" + retrievedFullname + "'>" +
                        "City: <input type='text' name='city' value='" + retrievedCity + "'>" +
                        "Mobile: <input type='tel' name='mobile' value='" + retrievedMobile + "'>" +
                        "Email: <input type='email' name='email' value='" + retrievedEmail + "'>" +
                        "<input type='submit' value='Update'>" +
                        "</form>";

                // Send the form as the response
                response.setContentType("text/html");
                response.getWriter().write(form);
            } else {
                // Entry not found
                response.getWriter().write("Entry not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the result set, statement, and connection
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the submitted form data
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        String newName = request.getParameter("fullname");
        String newCity = request.getParameter("city");
        String newMobile = request.getParameter("mobile");
        String newEmail = request.getParameter("email");


        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_db", "postgres", "SS080807");

            // Prepare the SQL query to update the entry with the given ID
            String sql = "UPDATE users_table SET fullname = ?, city = ?, mobile = ?, email = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, newName);
            statement.setInt(0, id);
            statement.setString(2, newCity);
            statement.setString(3, newMobile);
            statement.setString(4, newEmail);



            // Execute the update
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Update successful
                response.getWriter().write("Entry updated successfully.");
            } else {
                // Entry not found or update failed
                response.getWriter().write("Entry not found or update failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the statement and connection
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
