package top.wonderheng.student.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @BelongsProject: student-manage-servlet
 * @BelongsPackage: top.wonderheng.student.util
 * @Author: WonderHeng
 * @CreateTime: 2018-09-27 22:34
 */
public class ResponseUtil {

    public static void write(HttpServletResponse response, Object o) throws Exception {
        if (o instanceof JSONArray || o instanceof JSONObject) {
            response.setContentType("application/json;charset=utf-8");
        } else {
            response.setContentType("text/html;charset=utf-8");

        }
        PrintWriter out = response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }
}
