package ru.vershinin.lesson22.servlet;

import ru.vershinin.lesson22.dao.ClientDao;
import ru.vershinin.lesson22.dao.ProductDao;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Allproducts
 *
 * @author Вершинин Пётр
 */
@WebServlet(urlPatterns = "/allclient", name = "client")

public class AllClientServlet extends HttpServlet {
    @Inject
    private ClientDao clientDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<?> client = clientDao.showClient();

        req.setAttribute("client", client);
        req.setAttribute("PageTitle", "ALL");
        req.setAttribute("PageBody", "allclient.jsp");
        req.getRequestDispatcher("layout.jsp")
                .forward(req, resp);

    }


}
