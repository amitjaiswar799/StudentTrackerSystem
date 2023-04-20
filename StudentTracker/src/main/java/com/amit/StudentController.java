package com.amit;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/studentController")
public class StudentController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/Student_tracker")
	
	DataSource dataSource;
	
	StudentDbUtil sdu;
	
	//Method That Execute Automatically
	@Override
	public void init() throws ServletException {
		sdu = new StudentDbUtil(dataSource);
	}

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//System.out.println("invoked when form gets Sumbitted");
		
		String wtd = req.getParameter("command");
		//System.out.println(wtd);
		
		if(wtd == null) wtd = "will do";
		
		switch(wtd) {
		case "Auth":
			userAuthentication(req,resp);
			break;
		case "LIST":
			listStudents(req,resp);
			break;
		
		case "AddStudent":
			addStudent(req,resp);
			break;
			
		case "Delete":
			deleteStudent(req,resp);
			break;
			
		case "LOAD":
			loadStudent(req,resp);
			break;
		
		case "UPDATE":
			updateStudent(req,resp);
			break;
		case "Sup":
			addUser(req,resp);
			break;
			default:
				System.out.println("Command has null value");
		}
	}

	//add user for signup
	private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("Adding user is in progress...");
		
		String userName = req.getParameter("Suser");
		String passWord = req.getParameter("Spass");
		
		sdu.SingUp(userName,passWord);
		
		RequestDispatcher rd = req.getRequestDispatcher("/Login.html");
    	rd.forward(req, resp);
		
	}


	private void userAuthentication(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//System.out.println("Auth is in progress...");
		
		String username = req.getParameter("user");
		String password = req.getParameter("pass");
		
		boolean isAuth = sdu.isAuthenticated(username,password);
		
		
		if(isAuth) {
			//showUdated list of students
			listStudents(req,resp);
		}
		else
		{
			RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
			rd.forward(req, resp);
		}
		
		
	}


	private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//System.out.println("Upadating is in progress");
		
		String sid = req.getParameter("studentId");
		Student theStudent = new Student(req.getParameter("fn"),req.getParameter("ln"),req.getParameter("email"));
		
		sdu.updateStudent(sid,theStudent);
		
		//showUdated list of students
		listStudents(req,resp);
		
	}


	//displaying's students details for update
    private void loadStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
    	//System.out.println("loading is in process");
    	String sid = req.getParameter("studentId");
    	
    	Student theStudent = sdu.getStudent(sid);
    	
    	//send this to update-student.jsp
    	
    	req.setAttribute("THE_STUDENT", theStudent);
    	
    	
    	RequestDispatcher rd = req.getRequestDispatcher("/update-students.jsp");
    	rd.forward(req, resp);
    	
	}


	//delete Student
	private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("Delete in progress");
		String sid = req.getParameter("studentId");
		
		sdu.deleteStudent(sid);
		
		//showUdated list of students
				listStudents(req,resp);
		
		
	}

    //add Student
	private void addStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("We will Do");
		Student theStudent = new Student(req.getParameter("fn"),req.getParameter("ln"),req.getParameter("email"));
		
		sdu.addStudent(theStudent);
		
		//showUdated list of students
		listStudents(req,resp);
	}
 
	//list students
	private void listStudents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    
		List<Student> students = sdu.studentLists();
				
		 
		//set students attribute to request object
		
		req.setAttribute("LIST_STUDENT", students);
		
		
		//call list of students JSP page
		RequestDispatcher rd = req.getRequestDispatcher("/list-students.jsp");
		rd.forward(req, resp);
		
	}

}
