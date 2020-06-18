package ru.vershinin.lesson21.servlet;

import ru.vershinin.lesson21.dao.ShopDao;
import ru.vershinin.lesson21.pojo.Client;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Inject
    private ShopDao shopDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("PageTitle", "login");
        req.setAttribute("PageBody", "login.jsp");
        req.getRequestDispatcher("WEB-INF/jsp/l.jsp")
                .forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String page = "login";

        try {
            boolean check = shopDao.findClient(username, password);
            if(check){
                page="allproduct";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/"+page);
    }
}
