package top.wonderheng.student.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import top.wonderheng.student.dao.StudentDao;
import top.wonderheng.student.model.PageBean;
import top.wonderheng.student.model.Student;
import top.wonderheng.student.util.DbUtil;
import top.wonderheng.student.util.JsonUtil;
import top.wonderheng.student.util.ResponseUtil;
import top.wonderheng.student.util.StringUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

/**
 * @BelongsProject: student-manage-servlet
 * @BelongsPackage: top.wonderheng.student.web
 * @Author: WonderHeng
 * @CreateTime: 2018-09-27 22:45
 */
@WebServlet(urlPatterns = "/studentList")
public class StudentListServlet extends HttpServlet {

    private DbUtil dbUtil = DbUtil.getInstance();

    private StudentDao studentDao = new StudentDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String stuNo = request.getParameter("stuNo");
        String stuName = request.getParameter("stuName");
        String sex = request.getParameter("sex");
        String bbirthday = request.getParameter("bbirthday");
        String ebirthday = request.getParameter("ebirthday");
        String gradeId = request.getParameter("gradeId");

        Student student = new Student();
        if (stuNo != null) {
            student.setStuNo(stuNo);
            student.setStuName(stuName);
            student.setSex(sex);
            if (StringUtil.isNotEmpty(gradeId)) {
                student.setGradeId(Integer.parseInt(gradeId));
            }
        }
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Connection con = null;
        try {
            con = dbUtil.getConnection();
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JsonUtil.formatRsToJsonArray(studentDao.studentList(con, pageBean, student, bbirthday, ebirthday));
            int total = studentDao.studentCount(con, student, bbirthday, ebirthday);
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
