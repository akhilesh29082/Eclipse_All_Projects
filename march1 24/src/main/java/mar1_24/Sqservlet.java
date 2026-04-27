package mar1_24;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Sqservlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
//		HttpSession session=req.getSession();
//		int k=(int) session.getAttribute("k");
//		k=k*k;
//		PrintWriter out = res.getWriter();
//	    out.println("result = " + k);
		
		
//		int k=(int) req.getAttribute("k");
//		k=k*k;
//		PrintWriter out = res.getWriter();
//	    out.println("result = " + k);
		
		int k=0;
		jakarta.servlet.http.Cookie[] cookies = req.getCookies();
		for(Cookie c:cookies ) {
			if(c.getName().equals("k")) {
				k=Integer.parseInt(c.getValue());
				
			}
		}
		k=k*k;
		PrintWriter out = res.getWriter();
	    out.println("result = " + k);
	}
}
