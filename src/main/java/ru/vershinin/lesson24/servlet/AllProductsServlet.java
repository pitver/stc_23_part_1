package ru.vershinin.lesson24.servlet;

import ru.vershinin.lesson24.dao.ProductDao;

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
@WebServlet(urlPatterns = "/allproduct", name = "product")

public class AllProductsServlet extends HttpServlet {
    @Inject
    private ProductDao productDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<?> product = productDao.findAll();

        req.setAttribute("product", product);
        req.setAttribute("PageTitle", "ALL");
        req.setAttribute("PageBody", "allproducts.jsp");
        req.getRequestDispatcher("layout.jsp")
                .forward(req, resp);

    }


}