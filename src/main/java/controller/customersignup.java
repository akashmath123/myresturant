package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.mydao;
import dto.customer;

//this is to map the action url to this class(should be same as action-case sensitive)
@WebServlet("/signup")
//this is to make class as servlet class
@MultipartConfig
//this is make class as servlet class
public class customersignup extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//login to recive values from front end
		String firstname=req.getParameter("firstname");
		String password=req.getParameter("password");
		long mobile=Long.parseLong(req.getParameter("mobile"));
		String email =req.getParameter("email");
		String gender=req.getParameter("gender");
		String country=req.getParameter("country");
		LocalDate dob=LocalDate.parse(req.getParameter("DOB"));
		int age=Period.between(dob, LocalDate.now()).getYears();
		
		
		Part pic=req.getPart("picture");
		byte[] picture=null;
		picture=new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);
		
		mydao dao=new mydao();
		//logic to verify email and mobile is not repeated
		if(dao.fetchByEmail(email)==null && dao.fetchByMobile(mobile)==null){
       
		
		customer c=new customer();	
		c.setFirstname(firstname);
		c.setAge(age);
		c.setPassword(password);
		c.setMobile(mobile);
		c.setGender(gender);
		c.setCountry(country);
		c.setDob(dob);
		c.setPicture(picture);
		c.setEmail(email);
		
	
		
	    //persisting
		dao.save(c);
		
		resp.getWriter().print("<h1 style='color:green'>Account created successfully</h1>");
	}
	
	//when there is form and we want data to be secured 
        else {
        	resp.getWriter().print("<h1 style='color:red'>email and mobile should be unique</h1>");
        
	}
}
}