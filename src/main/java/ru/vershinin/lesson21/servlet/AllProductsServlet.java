package ru.vershinin.lesson21.servlet;

import ru.vershinin.lesson21.dao.ShopDao;

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
    private ShopDao shopDao;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<?> product = shopDao.showProduct();

        req.setAttribute("product", product);
        req.getRequestDispatcher("WEB-INF/jsp/allproducts.jsp").forward(req, resp);
    }


}
