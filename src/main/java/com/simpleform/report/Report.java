package com.simpleform.report;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Report {

	
	
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_db", "postgres", "SS080807");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM users_table");
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/khushi/Desktop/Software Construction/simple-form/src/main/resources/templates/report.html"));
			writer.write("<html xmlns:th=\"http://www.thymeleaf.org\"> <head><title>Report</title></head><style>\n"
					+ "table, th, td {\n"
					+ "  border:1px solid black;\n"
					+ "}\n"
					+ "</style> <body>");
			writer.write("<form method=\"GET\" action=\"/search\">\n"
					+ "  <input type=\"text\" name=\"fullname\" placeholder=\"Name\" />\n"
					+ "  <button type=\"submit\">Search by Name</button>\n"
					+ "</form>\n"
					+ "<form method=\"GET\" action=\"/search\">\n"
					+ "  <input type=\"text\" name=\"city\" placeholder=\"City\" />\n"
					+ "  <button type=\"submit\">Search by City</button>\n"
					+ "</form>\n"
					+ "\n"
					+ "<form method=\"GET\" action=\"/search\">\n"
					+ "  <input type=\"text\" name=\"mobile\" placeholder=\"Mobile\" />\n"
					+ "  <button type=\"submit\">Search by Mobile</button>\n"
					+ "</form>"
					+ "<form method=\"GET\" action=\"/search\">\n"
					+ "  <input type=\"text\" name=\"email\" placeholder=\"Email\" />\n"
					+ "  <button type=\"submit\">Search by Email</button>\n"
					+ "</form>"
					+ " <table><tr><th>ID</th><th>Full Name</th> <th>City</th> <th>Mobile</th> <th>Email</th><th>Edit</th><th>QRCode</th></tr>");
			Map<Integer, String> editUrls = new HashMap<>();
			while(resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("fullname");
				String city = resultSet.getString("city");
				String mobile = resultSet.getString("mobile");
				String email = resultSet.getString("email");
				// Generate the edit URL for each data entry
                String editUrl = "/update/" + id; // Replace with the appropriate URL pattern for your application
                // Store the ID-to-URL mapping
               // String data = "Name%3A%20"+name.replaceAll("\\s+", "")+ "%20City%3A"+ city.replaceAll("\\s+", "") +"%20Mobile%3A%20" + mobile;
                String da = "http://localhost:8099/userinfoqr/" + id;
                String url = "https://chart.googleapis.com/chart?cht=qr&chs=150x150&chl="+ da;
                editUrls.put(id, editUrl);
				writer.write("<tr><td> "+ id + "</td><td> " + name + "</td><td> "+ city + "</td><td> " + mobile+ "</td><td> " + email + "</td><td><a href='" + editUrl + "'>Edit</a></td>"
						+"<td>"+"<img src=" + url+ ">"  + "</td></tr>");
//writer.write("<tr th:each=\"entry : ${entries}\">\n"
//		+ "    <td th:text=\"${entry.id}\"></td>\n"
//		+ "    <td th:text=\"${entry.name}\"></td>\n"
//		+ "    <td th:text=\"${entry.age}\"></td>\n"
//		+ "    <td th:text=\"${entry.dob}\"></td>\n"
//		+ "    <td th:text=\"${entry.email}\"></td>\n"
//		+ "    <td th:text=\"${entry.password}\"></td>\n"
//		+ "    <td th:text=\"${entry.username}\"></td>\n"
//		+ "    <td th:text=\"${entry.gender}\"></td>\n"
//		+ "</td><td><a href='" + editUrl + "'>Edit</a></td>"
//		+ "    <td>\n"
//		+ "        <img th:src=\"${entry.qrCode}\" alt=\"QR Code\" />\n"
//		+ "    </td>\n"
//		+ "</tr>\n");
}
			writer.write("</table>");
			writer.write("</body> </html>");
			writer.close();
			
			System.out.println("Edit URLs:");
            for (Map.Entry<Integer, String> entry : editUrls.entrySet()) {
                System.out.println("ID: " + entry.getKey() + ", URL: " + entry.getValue());
            }
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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

}
