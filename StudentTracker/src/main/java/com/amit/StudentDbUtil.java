package com.amit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {

	private DataSource dataSource;

	public StudentDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//getStudents
 
	public List<Student> studentLists(){
		
		//testing purpose
		//testing get data from db;
				List<Student> students = new ArrayList<Student>();
				String q = "select * from employee";
				try {
					Connection conn = dataSource.getConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(q);
					
					while(rs.next()) {
						System.out.println(rs.getString(2));
						students.add(new Student(rs.getInt(1) , rs.getString(2), rs.getString(3),rs.getString(4)));
					}
			
				} catch (SQLException e) {
					e.printStackTrace();
				}
		  return students;
		
	}

	//add student
	public void addStudent(Student theStudent) {
		String q = "Insert into employee(first_name, last_name,email) values(?,?,?)";
		
		try {
			PreparedStatement ps = dataSource.getConnection().prepareStatement(q);
			ps.setString(1, theStudent.getFirstName());
			ps.setString(2, theStudent.getLastName());
			ps.setString(3, theStudent.getEmail());
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	//delete Students
	public void deleteStudent(String sid) {
		
		String q = "Delete from employee where id = " + sid;
		
		try {
			Statement stmt = dataSource.getConnection().createStatement();
			stmt.execute(q);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	//load Student
	public Student getStudent(String sid) {
		
		String q ="Select * from employee where id = " + sid;
		Student theStudent = null;
		
		try {
			Statement stmt = dataSource.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(q);
			
			if(rs.next()) {
				theStudent = new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return theStudent;
	}

	//update student
	public void updateStudent(String sid, Student theStudent) {

		String q = "Update employee set first_name = ? , last_name = ?, email = ? where id =" + sid;
		
		try {
			PreparedStatement ps = dataSource.getConnection().prepareStatement(q);
			ps.setString(1, theStudent.getFirstName());
			ps.setString(2, theStudent.getLastName());
			ps.setString(3, theStudent.getEmail());
			
			ps.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	//login
	public boolean isAuthenticated(String username, String password) {
		boolean isAuth = false;
		
		String q ="Select * from users where username = ? and password = ?";
		
		try {
			PreparedStatement ps = dataSource.getConnection().prepareStatement(q);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				isAuth= true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		return isAuth;
	}

	//signup student
	public void SingUp(String userName, String passWord) {
		
		String q = "Insert into users(username , password) VALUES (?,?)";
		
		try {
			PreparedStatement ps = dataSource.getConnection().prepareStatement(q);
			ps.setString(1,userName);
			ps.setString(2, passWord);
			ps.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
}
