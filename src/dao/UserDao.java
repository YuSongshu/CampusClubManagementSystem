package dao;

import utils.DbUtils;
import java.util.ArrayList;
import java.util.HashMap;

public class UserDao {
    private DbUtils db = new DbUtils();

    // 登录校验
    public ArrayList<HashMap<String, Object>> login(String username, String pwd) {
        String sql = "SELECT * FROM user WHERE username=? AND password=? AND status='正常'";
        return db.preparedQuery(sql, username, pwd);
    }



    // 查询所有用户
    public ArrayList<HashMap<String, Object>> findAllUser() {
        return db.preparedQuery("SELECT * FROM user");
    }

    // 新增学生账号
    public int addStudent(String username, String pwd, String name, String grade, String clazz) {
        String sql = "INSERT INTO user(username,password,name,grade,clazz,role,status) VALUES(?,?,?,?,?,0,'正常')";
        return db.executeUpdate(sql, username, pwd, name, grade, clazz);
    }

    // 修改用户信息
    public int updateUserInfo(int id, String name, String grade, String clazz) {
        String sql = "UPDATE user SET name=?,grade=?,clazz=? WHERE id=?";
        return db.executeUpdate(sql, name, grade, clazz, id);
    }

    // 停用/启用账号
    public int updateUserStatus(int id, String status) {
        String sql = "UPDATE user SET status=? WHERE id=?";
        return db.executeUpdate(sql, status, id);
    }

    // 重置/修改密码
    public int updatePwd(int id, String newPwd) {
        String sql = "UPDATE user SET password=? WHERE id=?";
        return db.executeUpdate(sql, newPwd, id);
    }
}