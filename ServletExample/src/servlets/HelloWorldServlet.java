package servlets;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EchoServlet
 */
@WebServlet(name = "MyOwnServlet",
description = "This is my first annotated servlet",
urlPatterns = {"/HelloWorldServlet","/Hello"})
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private String message;
	
    public HelloWorldServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException
    {
    	System.out.println("in init");
        // Do required initialization
        message = "Hello World";
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("calling doGet");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	      out.println(message);
	      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String v = request.getParameter("id");
		System.out.println(v);
		
		//Alternatively
/*		DataInputStream reader = new DataInputStream(request.getInputStream());
		byte[] bytes = new byte[request.getContentLength()];
		reader.read(bytes);
		System.out.println(new String(bytes));*/
		
	}

}
