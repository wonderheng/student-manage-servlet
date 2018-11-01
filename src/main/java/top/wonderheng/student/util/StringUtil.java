package top.wonderheng.student.util;

/**
 * @BelongsProject: student-manage-servlet
 * @BelongsPackage: top.wonderheng.student.util
 * @Author: WonderHeng
 * @CreateTime: 2018-09-27 22:34
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        return "".equals(str) || str == null;
    }

    public static boolean isNotEmpty(String str) {
        return !"".equals(str) && str != null;
    }
}
