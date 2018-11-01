package top.wonderheng.student.web;

import top.wonderheng.student.dao.UserDao;
import top.wonderheng.student.model.User;
import top.wonderheng.student.util.DbUtil;
import top.wonderheng.student.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

/**
 * @BelongsProject: student-manage-servlet
 * @BelongsPackage: top.wonderheng.student.web
 * @Author: WonderHeng
 * @CreateTime: 2018-09-27 22:46
 */
@WebServlet(name = "loginServlet",urlPatterns = "/login")
public class UserLoginServlet extends HttpServlet {

    private DbUtil dbUtil = DbUtil.getInstance();

    private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        request.setAttribute("userName", userName);
        request.setAttribute("password", password);
        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
            request.setAttribute("error", "用户名和密码不能为空");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        User user = new User(userName, password);
        Connection con = null;
        try {
            con = dbUtil.getConnection();
            User currentUser = userDao.login(con, user);
            if (currentUser == null) {
                request.setAttribute("error", "用户名或密码错误");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", currentUser);
                response.sendRedirect("main.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeConnection(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
