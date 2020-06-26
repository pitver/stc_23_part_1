package ru.vershinin.lesson24.servlet;

import javax.servlet.*;
import java.io.IOException;


public class CharacterSetFilter  implements Filter {
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain next) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        next.doFilter(request, response);
    }
}
