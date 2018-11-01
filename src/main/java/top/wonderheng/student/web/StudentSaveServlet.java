package top.wonderheng.student.web;

import com.alibaba.fastjson.JSONObject;
import top.wonderheng.student.dao.StudentDao;
import top.wonderheng.student.model.Student;
import top.wonderheng.student.util.DateUtil;
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
 * @CreateTime: 2018-09-27 22:45
 */
@WebServlet(urlPatterns = "/studentSave")
public class StudentSaveServlet extends HttpServlet {

    private DbUtil dbUtil = DbUtil.getInstance();

    private StudentDao studentDao = new StudentDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        String stuNo = request.getParameter("stuNo");
        String stuName = request.getParameter("stuName");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String gradeId = request.getParameter("gradeId");
        String email = request.getParameter("email");
        String stuDesc = request.getParameter("stuDesc");
        String stuId = request.getParameter("stuId");

        Student student = null;
        try {
            student = new Student(stuNo, stuName, sex, DateUtil.formatString(birthday, "yyyy-MM-dd"),
                    Integer.parseInt(gradeId), email, stuDesc);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (StringUtil.isNotEmpty(stuId)) {
            student.setStuId(Integer.parseInt(stuId));
        }
        Connection con = null;
        try {
            con = dbUtil.getConnection();
            int saveNums = 0;
            JSONObject result = new JSONObject();
            if (StringUtil.isNotEmpty(stuId)) {
                saveNums = studentDao.studentModify(con, student);
            } else {
                saveNums = studentDao.studentAdd(con, student);
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
