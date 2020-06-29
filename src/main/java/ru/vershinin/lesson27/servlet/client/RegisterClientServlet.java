package ru.vershinin.lesson27.servlet.client;

import ru.vershinin.lesson27.dao.ClientDao.ClientDao;
import ru.vershinin.lesson27.pojo.Client;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterClientServlet extends HttpServlet {
    @Inject
    private ClientDao clientDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setAttribute("PageTitle", "Registration");
        req.setAttribute("PageBody", "register.jsp");
        req.getRequestDispatcher("layout.jsp")
                .forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        Client client = new Client.Builder()
                .withUsername(request.getParameter("username"))
                .withFirstName(request.getParameter("lastName"))
                .withLastName(request.getParameter("lastName"))
                .withPassword(request.getParameter("password"))
                .build();


        try {
            clientDao.save(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
