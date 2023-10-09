package de.ait.shop.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 10/2/2023
 * WebExample
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        request.getReader();

        response.getWriter().write("<h1>Hello, " + name + "</h1>");
    }
}
