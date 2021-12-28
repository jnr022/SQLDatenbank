package datenbank;

import java.sql.*;

public class Datenbank {

	public static void main(String[] args) {
		String url ="jdbc:mysql://localhost:3306/datenbank";         
		String user ="root";
		String password="password";

		try (Connection conn = DriverManager.getConnection(url, user, password)) { 
			System.out.println("Erfolgreich mit Datenbank verbunden.");
			String query = " UPDATE personen SET Vorname='Beate' WHERE PersonenID=6 ";
			Statement stmt = conn.createStatement();  
			stmt.execute(query);
			stmt.close();
			query = " SELECT * FROM personen ORDER BY PersonenID ASC";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query); 
			int columns = rs.getMetaData().getColumnCount();
			for(int i =1; i<= columns ; i++) 
				System.out.print(String.format("%-15s", rs.getMetaData().getColumnLabel(i)));   
				System.out.println(); 
				System.out.println("------------------------------------------------------------"); 

				while(rs.next()) {
					for(int j=1; j<= columns ; j++) {
						System.out.print(String.format("%-15s", rs.getString(j)));
						}  
						System.out.println();
				}
				rs.close();
				stmt.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
