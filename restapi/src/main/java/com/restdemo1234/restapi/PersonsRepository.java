package com.restdemo1234.restapi;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
public class PersonsRepository {
	
	Connection con = null;
	public PersonsRepository() {
		 String url ="jdbc:mysql://localhost:3306/restapijava";
	     String uname = "root";
	     String pword ="1234";
	     try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,uname,pword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	 public List<Persons> getPersons(){
		 
		 List<Persons> persons = new ArrayList<>();
		 String sql = "select * from person";
		 try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Persons p = new Persons();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setPoints(rs.getInt(3));
				
				persons.add(p);
				
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		 
	    	return persons;
	    }
	    
	    public Persons getPerson(int id) {
			 List<Persons> persons = new ArrayList<>();
			 String sql = "select * from person where id="+id;
			 Persons p = new Persons();
			 try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				if(rs.next()) {
					
					p.setId(rs.getInt(1));
					p.setName(rs.getString(2));
					p.setPoints(rs.getInt(3));
					
				}
			} catch (SQLException e) {		
				e.printStackTrace();
			}
			 
		    	return p;
		}

		public void create(Persons p) {
			String sql = "insert into person values (?,?,?)";
			 try {
				PreparedStatement st = con.prepareStatement(sql);
				st.setInt(1, p.getId());
				st.setString(2, p.getName());
				st.setInt(3,p.getPoints());
			    st.executeUpdate();
				
			} catch (SQLException e) {		
				e.printStackTrace();
			}
			
		}
		
		public void update(Persons p) {
			String sql = "update person set name = ? , points = ? where id=?";
			 try {
				PreparedStatement st = con.prepareStatement(sql);
				
				st.setString(1, p.getName());
				st.setInt(2,p.getPoints());
				st.setInt(3, p.getId());
				
			    st.executeUpdate();
				
			} catch (SQLException e) {		
				e.printStackTrace();
			}
			
		}


		public void delete(int id) {
			String sql = "delete from person where id=?";
			 try {
				PreparedStatement st = con.prepareStatement(sql);
				st.setInt(1,id);				
			    st.executeUpdate();
				
			} catch (SQLException e) {		
				e.printStackTrace();
			}
			
			
		}
	
	
///////////without db///////////// 
//    List<Persons> persons;
//    
//    public PersonsRepository() {
//     	
//     persons = new ArrayList<>();
//  	  
//     Persons p1 = new Persons();
//      p1.setId(101);
//  	  p1.setName("Tharindu");
//  	  p1.setPoints(10);
//  	  
//  	 Persons p2 = new Persons();
//  	  p2.setId(102);
//  	  p2.setName("Tharindu2");
//  	  p2.setPoints(30);
//  	  
//  	  persons.add(p1);
//  	  persons.add(p2);
//    }
//    
//    public List<Persons> getPersons(){
//    	return persons;
//    }
//    
//    public Persons getPerson(int id) {
//		for(Persons p : persons) {
//			if(p.getId() == id)
//				return p;
//		}
//		return null;
//	}
//
//	public void create(Persons p) {
//		
//		persons.add(p);
//	}
}
