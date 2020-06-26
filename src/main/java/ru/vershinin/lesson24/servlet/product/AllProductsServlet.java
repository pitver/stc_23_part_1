package ru.vershinin.lesson24.servlet.product;

import ru.vershinin.lesson24.dao.ProductDao.ProductDao;
import ru.vershinin.lesson24.pojo.Product;

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
        List<Product> product = productDao.findAll();

        req.setAttribute("product", product);
        req.setAttribute("PageTitle", "ALL");
        req.setAttribute("PageBody", "allproducts.jsp");
        req.getRequestDispatcher("layout.jsp")
                .forward(req, resp);

    }


}
