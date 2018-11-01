package top.wonderheng.student.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import top.wonderheng.student.dao.GradeDao;
import top.wonderheng.student.model.Grade;
import top.wonderheng.student.model.PageBean;
import top.wonderheng.student.util.DbUtil;
import top.wonderheng.student.util.JsonUtil;
import top.wonderheng.student.util.ResponseUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

/**
 * @BelongsProject: student-manage-servlet
 * @BelongsPackage: top.wonderheng.student.web
 * @Author: WonderHeng
 * @CreateTime: 2018-09-27 22:44
 */
@WebServlet(name = "gradeListServlet",urlPatterns = "/gradeList")
public class GradeListServlet extends HttpServlet {

    private DbUtil dbUtil = DbUtil.getInstance();

    private GradeDao gradeDao = new GradeDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String gradeName = request.getParameter("gradeName");
        if (gradeName == null) {
            gradeName = "";
        }
        Grade grade = new Grade();
        grade.setGradeName(gradeName);
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Connection con = null;
        try {
            con = dbUtil.getConnection();
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JsonUtil.formatRsToJsonArray(gradeDao.gradeList(con, pageBean, grade));
            int total = gradeDao.gradeCount(con, grade);
            result.put("rows", jsonArray);
            result.put("total", total);
            ResponseUtil.write(response, result);
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