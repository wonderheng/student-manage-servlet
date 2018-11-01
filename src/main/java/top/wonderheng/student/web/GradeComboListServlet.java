package top.wonderheng.student.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import top.wonderheng.student.dao.GradeDao;
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
 * @CreateTime: 2018-09-27 22:42
 */
@WebServlet(name = "gradeComboListServlet",urlPatterns = "/gradeComboList")
public class GradeComboListServlet extends HttpServlet {

    private DbUtil dbUtil = DbUtil.getInstance();

    private GradeDao gradeDao = new GradeDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Connection con = null;
        try {
            con = dbUtil.getConnection();
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", "");
            jsonObject.put("gradeName", "---请选择---");
            jsonArray.add(jsonObject);
            jsonArray.addAll(JsonUtil.formatRsToJsonArray(gradeDao.gradeList(con, null, null)));


            ResponseUtil.write(response, jsonArray);
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
