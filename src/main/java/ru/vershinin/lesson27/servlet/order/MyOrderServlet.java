package ru.vershinin.lesson27.servlet.order;

import ru.vershinin.lesson27.dao.ShopDao.ShopDao;
import ru.vershinin.lesson27.pojo.Order;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * MyOrderServlet
 *
 * @author Вершинин Пётр
 */
@WebServlet("/myorder")
public class MyOrderServlet extends HttpServlet {
    @Inject
    private ShopDao shopDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Object session = req.getSession().getAttribute("idClient");
        List<Order> myOrder = shopDao.findOrderByClientId((Integer) session);
        req.setAttribute("order", myOrder);
        req.setAttribute("PageTitle", "ALL");
        req.setAttribute("PageBody", "myorder.jsp");
        req.getRequestDispatcher("layout.jsp")
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        shopDao.OrderFulfillmentController();
        resp.sendRedirect(req.getContextPath() + "/myorder");
    }


}
