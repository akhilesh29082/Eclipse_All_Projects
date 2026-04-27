package com.mit;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Addservlat")
public class Addservlat extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		
	    int i = Integer.parseInt(req.getParameter("num1"));
	    int j = Integer.parseInt(req.getParameter("num2"));
	
	    int k = i + j;   // only sum
	
//	    PrintWriter out = res.getWriter();
//	    out.println("Sum of two numbers = " + k);
	    req.setAttribute("k",k);
	    RequestDispatcher rd = req.getRequestDispatcher("sq");
	    rd.forward(req, res);
	}		
		
	}
    



































//protected void doPost(HttpServletRequest req, HttpServletResponse res)
//        throws ServletException, IOException {
//
//    int i = Integer.parseInt(req.getParameter("num1"));
//    int j = Integer.parseInt(req.getParameter("num2"));
//
//    int k = i + j;   // only sum
//
//    PrintWriter out = res.getWriter();
//    out.println("Sum of two numbers = " + k);
//}