package top.wonderheng.student.dao;

import top.wonderheng.student.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @BelongsProject: student-manage-servlet
 * @BelongsPackage: top.wonderheng.student.dao
 * @Author: WonderHeng
 * @CreateTime: 2018-09-27 22:48
 */
public class UserDao {
    public User login(Connection con, User user) throws Exception {
        User resultUser = null;
        String sql = "select * from t_user where userName=? and password=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, user.getUserName());
        pstmt.setString(2, user.getPassword());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            resultUser = new User();
            resultUser.setUserName(rs.getString("userName"));
            resultUser.setPassword(rs.getString("password"));
        }
        return resultUser;
    }

    public User register(Connection con, User user) throws Exception {
        String sql = "insert  into t_user (userName,password) values (?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, user.getUserName());
        pstmt.setString(2, user.getPassword());
        int effect = pstmt.executeUpdate();
        return user;
    }
}
