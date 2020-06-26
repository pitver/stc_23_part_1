package ru.vershinin.lesson24.servlet.order;

import ru.vershinin.lesson24.dao.ClientDao.ClientDao;
import ru.vershinin.lesson24.dao.ProductDao.ProductDao;
import ru.vershinin.lesson24.dao.ShopDao.ShopDao;
import ru.vershinin.lesson24.pojo.Client;
import ru.vershinin.lesson24.pojo.Product;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Allproducts
 *
 * @author Вершинин Пётр
 */
@WebServlet(urlPatterns = "/addtoorder")

public class CreateOrderServlet extends HttpServlet {
    @Inject
    private ProductDao productDao;
    @Inject
    private ClientDao clientDao;
    @Inject
    private ShopDao shopDao;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("id");

        if (productId == null) {
            throw new ServletException("Missing parameter id");
        }

        //подготовка информации для формирования импровизированной карзины
        Product product = productDao.findById(Integer.valueOf(productId));
        Object session = req.getSession().getAttribute("idClient");
        Client client = clientDao.findById((Integer) session);

        if (product == null ||client==null) {
            resp.setStatus(404);
            req.setAttribute("PageTitle", "addtoorder");
            req.setAttribute("PageBody", "notfound.jsp");
            req.getRequestDispatcher("/layout.jsp")
                    .forward(req, resp);
            return;
        }
        shopDao.save(client,product);

        resp.sendRedirect(req.getContextPath() + "/allproduct");

    }




}
