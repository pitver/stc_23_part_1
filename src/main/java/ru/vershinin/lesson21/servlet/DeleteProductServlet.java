package ru.vershinin.lesson21.servlet;

import ru.vershinin.lesson21.dao.ShopDao;
import ru.vershinin.lesson21.pojo.Product;

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
@WebServlet("/delete")
public class DeleteProductServlet extends HttpServlet {
    @Inject
    private ShopDao shopDao;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("id");
        if (productId == null) {
            throw new ServletException("Missing parameter id");
        }
        Product product = shopDao.getProductById(Integer.valueOf(productId));
        if (product == null) {
            resp.setStatus(404);
            req.setAttribute("PageTitle", "DeleteProduct");
            req.setAttribute("PageBody", "notfound.jsp");
            req.getRequestDispatcher("/layout.jsp")
                    .forward(req, resp);
            return;
        }
        shopDao.deleteProduct(product);
        resp.sendRedirect(req.getContextPath() + "/allproduct");


    }

}
