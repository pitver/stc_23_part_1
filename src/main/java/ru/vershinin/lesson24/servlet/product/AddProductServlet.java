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

@WebServlet("/addproduct")
public class AddProductServlet extends HttpServlet {
    @Inject
    private ProductDao productDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("PageTitle", "ADD");
        req.setAttribute("PageBody", "addproduct.jsp");
        req.getRequestDispatcher("layout.jsp")
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String productName = req.getParameter("productName");
        String price = req.getParameter("price");
        String present = req.getParameter("present");
        Product product = new Product(Double.valueOf(price), Boolean.valueOf(present), productName);
        productDao.save(product);

        resp.sendRedirect(req.getContextPath() + "/allproduct");
    }
}
