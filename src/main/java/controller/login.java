package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.mydao;
import dto.customer;
//mapping the url
@WebServlet("/login")
public class login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//receive the value front-end
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		//verify if email exists
		mydao d=new mydao();
		customer c=d.fetchByEmail(email);
		if(c==null)
		{
			resp.getWriter().print("<h1 style='color:red'>Invalid email</h1>");
			req.getRequestDispatcher(password);
		}
		if(password.equals(c.getPassword())) {
			resp.getWriter().print("<h1 style='color:green'>Login success</h1>");
		}
		else {
			resp.getWriter().print("<h1 style='color:green'>Invalid password</h1>");

		}
		
	}

}
