package ru.vershinin.lesson24.servlet;


import ru.vershinin.lesson24.dao.ProductDao;
import ru.vershinin.lesson24.pojo.Product;

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
@WebServlet("/edit")
public class EditProduct extends HttpServlet {
    @Inject
    private ProductDao productDao;
    String productId = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        productId = req.getParameter("id");
        if (productId == null) {
            throw new ServletException("Missing parameter id");
        }
        Product product = productDao.findById(Integer.valueOf(productId));
        if (product == null) {
            resp.setStatus(404);
            req.setAttribute("PageTitle", "EditProduct");
            req.setAttribute("PageBody", "notfound.jsp");
            req.getRequestDispatcher("/layout.jsp")
                    .forward(req, resp);
            return;
        }
        req.setAttribute("product", product);
        req.setAttribute("PageTitle", "EditProduct");
        req.setAttribute("PageBody", "edit.jsp");
        req.getRequestDispatcher("/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String productName = req.getParameter("productName");
        String price = req.getParameter("price");
        String present = req.getParameter("present");
        Product product = new Product(Integer.valueOf(productId), productName, Double.valueOf(price), Boolean.valueOf(present));
        productDao.editProduct(product);


        resp.sendRedirect(req.getContextPath() + "/allproduct");
    }

}
