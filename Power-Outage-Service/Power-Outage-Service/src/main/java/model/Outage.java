package model;

import java.sql.*;

import DBUtil.DBConnect;

public class Outage {
	
	
	//Insert Data
	public String insertOutage(String cusid , String cusname , String outarea ,String outdate, String outtime ,String outdesc) {
		
		String output = "";
		
		
		
		try {
			
			Connection con = DBConnect.connect();
			if(con == null) {
				return "error while connecting database";
			}
			
			String query = "insert into outage(`outageID` , `cusID` ,`cusName` , `outArea` , `outDate`,`outTime` , `outDesc`)"
					        + "value (?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, cusid); 
			 preparedStmt.setString(3, cusname); 
			 preparedStmt.setString(4, outarea);
			 preparedStmt.setString(5, outdate);
			 preparedStmt.setString(6, outtime);
             preparedStmt.setString(7, outdesc);
			 
			//execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newUsers = readOutages(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
					 newUsers + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
			 System.err.println(e.getMessage()); 
			 }
			 return output; 
			 } 
	
	//read Data
		public String readOutages() {
			String output  = "";
			
			try {
				Connection con = DBConnect.connect();
				
				if (con == null) {
					return "error while connecting database for reading";
				}
				
				// Prepare the html table to be displayed
				 output = "<table border='1'><tr><th>Customer ID</th>" 
				 +"<th>Customer Name</th><th>Outage Area</th>"
				 + "<th>Outage Date</th>"  + "<th>Outage Time</th>"
				 + "<th>Outage Description</th>"
				 + "<th>Update</th><th>Remove</th></tr>";
				 
				 String query = "select * from outage"; 
				 Statement stmt = con.createStatement(); 
				 ResultSet rs = stmt.executeQuery(query);
				 
				 while (rs.next()) 
				 { 
					 String outageID = Integer.toString(rs.getInt("outageID")); 
					 String cusID = Integer.toString(rs.getInt("cusID")); 
					 String cusName = rs.getString("cusName"); 
					 String outArea =  rs.getString("outArea"); 
					 String outDate = rs.getString("outDate"); 
	                 String outTime = rs.getString("outTime");
	                 String outDesc = rs.getString("outDesc");
					 
	                 // Add a row into the html table
					 output += "<tr><td>" + cusID + "</td>"; 
					 output += "<td>" + cusName + "</td>"; 
					 output += "<td>" + outArea + "</td>";
					 output += "<td>" + outDate + "</td>"; 
	                 output += "<td>" + outTime + "</td>";
	                 output += "<td>" + outDesc + "</td>";
					 
					 // buttons
	                 output += "<td><input name='btnUpdate' type='button' value='Update' "
	                		 + "class='btnUpdate btn btn-secondary' data-itemid='" +outageID  + "'></td>"
	                		 + "<td><input name='btnRemove' type='button' value='Remove' "
	                		 + "class='btnRemove btn btn-danger' data-itemid='" + outageID  + "'></td></tr>";  
				 } 
				 
				 con.close(); 
				 // To complete the html table
				 output += "</table>"; 
				 
				 
			}catch(Exception e) {
				
				output = "error while reading outages";
				System.err.println(e.getMessage());
				
			}
			
			return output;
		}
		
		//update
				public String updateOutage(String outid , String cusid , String cusname , String outarea ,String outdate, String outtime ,String outdesc) {
					
					String output = ""; 
					
					try {
						
						Connection con = DBConnect.connect(); 
						
						if (con == null){
							
							return "Error while connecting to the database for updating."; 
						} 
						
						 // create a prepared statement
						 String query = "UPDATE outage SET cusID=?,cusName=?,outArea=?,outDate=?,outTime=?,outDesc=? WHERE outageID=?";
						 
						 PreparedStatement preparedStmt = con.prepareStatement(query); 
						 
						 // binding values
					         preparedStmt.setString(1, cusid); 
					         preparedStmt.setString(2, cusname); 
					         preparedStmt.setString(3, outarea);
					         preparedStmt.setString(4, outdate);
					         preparedStmt.setString(5, outtime);
			                 preparedStmt.setString(6, outdesc);
			                 preparedStmt.setInt(7, Integer.parseInt(outid)); 
						 
						 // execute the statement
						 preparedStmt.execute(); 
						 
						 con.close(); 
						 String newCustomers = readOutages(); 
						 output = "{\"status\":\"success\", \"data\": \"" + 
						 		 newCustomers + "\"}"; 
						 } 
						 catch (Exception e) 
						 { 
						 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
						 System.err.println(e.getMessage()); 
						 } 
						 return output; 
						 } 

				//delete
				public String deleteOutage(String outageID) { 
					String output = ""; 
					try{ 
						Connection con = DBConnect.connect(); 
						if (con == null) { 
							return "Error while connecting to the database for deleting."; 
						} 
						
						// create a prepared statement
						String query = "delete from outage where outageID=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(outageID)); 
				 
						// execute the statement
						preparedStmt.execute(); 
						
						con.close(); 
						
						output = "Deleted successfully"; 
					}catch (Exception e) { 
						
						output = "Error while deleting the outage."; 
						System.err.println(e.getMessage()); 
					} 
					
					return output; 
					
				}
	
}
