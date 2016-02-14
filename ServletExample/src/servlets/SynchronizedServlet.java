package servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.math.*;

@WebServlet(name = "SynchronizedServlet",
description = "This is my simple servlet demonstrating thread safety using synchronization",
urlPatterns = {"/SynchronizedServlet"})
public class SynchronizedServlet extends HttpServlet {
	// A variable that is NOT thread-safe!
	private int counter = 0;
	private String mutex = "";

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("<HTML><BODY>");
		resp.getWriter().println(this + ": <br>");
		synchronized (mutex) {
			for (int c = 0; c < 10; c++) {
				resp.getWriter().println("Counter = " + counter + "<BR>");
				try {
					Thread.currentThread().sleep((long) Math.random() * 1000);
					counter++;
				} catch (InterruptedException exc) {
				}
			}
		}
		resp.getWriter().println("</BODY></HTML>");
	}
}