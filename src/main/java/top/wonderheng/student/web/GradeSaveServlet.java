package top.wonderheng.student.web;

import com.alibaba.fastjson.JSONObject;
import top.wonderheng.student.dao.GradeDao;
import top.wonderheng.student.model.Grade;
import top.wonderheng.student.util.DbUtil;
import top.wonderheng.student.util.ResponseUtil;
import top.wonderheng.student.util.StringUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * @BelongsProject: student-manage-servlet
 * @BelongsPackage: top.wonderheng.student.web
 * @Author: WonderHeng
 * @CreateTime: 2018-09-27 22:44
 */
@WebServlet(urlPatterns = "/gradeSave")
public class GradeSaveServlet extends HttpServlet {

    private DbUtil dbUtil = DbUtil.getInstance();

    private GradeDao gradeDao = new GradeDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        String gradeName = request.getParameter("gradeName");
        String gradeDesc = request.getParameter("gradeDesc");
        String id = request.getParameter("id");
        Grade grade = new Grade(gradeName, gradeDesc);
        if (StringUtil.isNotEmpty(id)) {
            grade.setId(Integer.parseInt(id));
        }
        Connection con = null;
        try {
            con = dbUtil.getConnection();
            int saveNums = 0;
            JSONObject result = new JSONObject();
            if (StringUtil.isNotEmpty(id)) {
                saveNums = gradeDao.gradeModify(con, grade);
            } else {
                saveNums = gradeDao.gradeAdd(con, grade);
            }
            if (saveNums > 0) {
                result.put("success", "true");
            } else {
                result.put("success", "true");
                result.put("errorMsg", "保存失败");
            }
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
