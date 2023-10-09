package servlet;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    String name = request.getParameter("name");
    try {
      request.getReader();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    try {
      response.getWriter().write("<h1> Hello, "+name+"</h1>");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
