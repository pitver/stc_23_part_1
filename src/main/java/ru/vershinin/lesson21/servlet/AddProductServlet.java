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
import java.util.List;

@WebServlet("/addproduct")
public class AddProductServlet extends HttpServlet {
    @Inject
    private ShopDao shopDao;
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
        Product product =new Product(Double.valueOf(price), Boolean.valueOf(present),productName);
        shopDao.addProduct(product);


        resp.sendRedirect(req.getContextPath() + "/allproduct");
    }
}
