package ru.vershinin.lesson22.servlet;

import ru.vershinin.lesson22.dao.ClientDao;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session=req.getSession();
        session.setAttribute("nik", null);
        req.setAttribute("PageTitle", "login");
        req.setAttribute("PageBody", "login.jsp");
        req.getRequestDispatcher("layout.jsp")
                .forward(req, resp);

    }


}
