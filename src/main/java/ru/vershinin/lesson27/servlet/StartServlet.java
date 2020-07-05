package ru.vershinin.lesson27.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Start
 *
 * @author Вершинин Пётр
 */
@WebServlet("/")
public class StartServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("PageTitle", "Home");
        req.setAttribute("PageBody", "home.jsp");
        req.getRequestDispatcher("layout.jsp")
                .forward(req, resp);
        /*req.getRequestDispatcher("login.jsp")
                .forward(req, resp);*/
    }
}
