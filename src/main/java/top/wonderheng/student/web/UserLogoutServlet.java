package top.wonderheng.student.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @BelongsProject: student-manage-servlet
 * @BelongsPackage: top.wonderheng.student.web
 * @Author: WonderHeng
 * @CreateTime: 2018-09-27 22:46
 */
@WebServlet(name = "logoutServlet",urlPatterns = "/logout")
public class UserLogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("currentUser");
        response.sendRedirect("index.jsp");
    }
}
