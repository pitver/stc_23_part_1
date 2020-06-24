package ru.vershinin.lesson24.servlet;


import ru.vershinin.lesson24.dao.ClientDao;
import ru.vershinin.lesson24.pojo.Client;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * EditProduct
 *
 * @author Вершинин Пётр
 */
@WebServlet("/editclient")
public class EditClient extends HttpServlet {
    @Inject
    private ClientDao clientDao;
    String clientId = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        clientId = req.getParameter("id");
        if (clientId == null) {
            throw new ServletException("Missing parameter id");
        }
        Client client = clientDao.findById(Integer.valueOf(clientId));
        if (client == null) {
            resp.setStatus(404);
            req.setAttribute("PageTitle", "EditClient");
            req.setAttribute("PageBody", "notfound.jsp");
            req.getRequestDispatcher("/layout.jsp")
                    .forward(req, resp);
            return;
        }
        req.setAttribute("client", client);
        req.setAttribute("PageTitle", "EditClient");
        req.setAttribute("PageBody", "editclient.jsp");
        req.getRequestDispatcher("/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        Client client = new Client.Builder()
                .withUsername(req.getParameter("username"))
                .withFirstName(req.getParameter("lastName"))
                .withLastName(req.getParameter("lastName"))
                .withPassword(req.getParameter("password"))
                .build();
       clientDao.editClient(client);


        resp.sendRedirect(req.getContextPath() + "/allclient");
    }

}