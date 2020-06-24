package ru.vershinin.lesson24.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AppErrorHandler
 *
 * @author Вершинин Пётр
 */
public class AppErrorHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        processError(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        processError(request, response);
    }

    private void processError(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String servletName = (String) request.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME);
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        if (statusCode != 500) {
            request.setAttribute("statusCode", statusCode);
            request.setAttribute("requestUri", requestUri);
        } else {
            request.setAttribute("statusCode", statusCode);
            request.setAttribute("requestUri", requestUri);
            request.setAttribute("servletName", servletName);
            request.setAttribute("throwableName", throwable.getClass().getName());
            request.setAttribute("throwableMessage", throwable.getMessage());
        }
        request.getRequestDispatcher("/error.jsp")
                .forward(request, response);
    }
}
