package ru.vershinin.lesson27.servlet.client;

import ru.vershinin.lesson27.dao.ClientDao.ClientDao;
import ru.vershinin.lesson27.pojo.Client;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Inject
    private ClientDao clientDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("PageTitle", "login");
        req.setAttribute("PageBody", "login.jsp");
        req.getRequestDispatcher("layout.jsp")
                .forward(req, resp);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String page = "login";

        try {
            Client check = clientDao.findByClient(new Client.Builder()
                    .withUsername(username)
                    .withPassword(password)
                    .build());
            if (check!=null) {
                session.setAttribute("nik", username);
                session.setAttribute("idClient", check.getId());
                session.setAttribute("role",check.getRoles());
                page = "allproduct";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/" + page);
    }
}
