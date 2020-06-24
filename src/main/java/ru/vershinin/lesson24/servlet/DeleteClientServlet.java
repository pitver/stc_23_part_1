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
 * DeleteProductServlet
 *
 * @author Вершинин Пётр
 */
@WebServlet("/deleteclient")
public class DeleteClientServlet extends HttpServlet {
    @Inject
    private ClientDao clientDao;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("id");
        if (productId == null) {
            throw new ServletException("Missing parameter id");
        }
        Client client = clientDao.findById(Integer.valueOf(productId));
        if (client == null) {
            resp.setStatus(404);
            req.setAttribute("PageTitle", "DeleteClient");
            req.setAttribute("PageBody", "notfound.jsp");
            req.getRequestDispatcher("/layout.jsp")
                    .forward(req, resp);
            return;
        }
        clientDao.delete(client);
        resp.sendRedirect(req.getContextPath() + "/allclient");


    }

}
