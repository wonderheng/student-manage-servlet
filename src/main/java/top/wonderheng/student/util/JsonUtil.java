package top.wonderheng.student.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

/**
 * @BelongsProject: student-manage-servlet
 * @BelongsPackage: top.wonderheng.student.util
 * @Author: WonderHeng
 * @CreateTime: 2018-09-27 22:32
 */
public class JsonUtil {

    public static JSONArray formatRsToJsonArray(ResultSet rs) throws Exception {
        ResultSetMetaData md = rs.getMetaData();
        int num = md.getColumnCount();
        JSONArray array = new JSONArray();
        while (rs.next()) {
            JSONObject mapOfColValues = new JSONObject();
            for (int i = 1; i <= num; i++) {
                Object o = rs.getObject(i);
                if (o instanceof Date) {
                    mapOfColValues.put(md.getColumnName(i), DateUtil.formatDate((Date) o, "yyyy-MM-dd"));
                } else {
                    mapOfColValues.put(md.getColumnName(i), rs.getObject(i));
                }
            }
            array.add(mapOfColValues);
        }
        return array;
    }
}
