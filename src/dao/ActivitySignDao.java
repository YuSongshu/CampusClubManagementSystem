package dao;

import utils.DbUtils;
import java.util.ArrayList;
import java.util.HashMap;

public class ActivitySignDao {
    private DbUtils db = new DbUtils();

    // 报名活动
    public int signActivity(int stuId, int actId) {
        String sql = "INSERT INTO activity_sign(student_id,activity_id,sign_time) VALUES(?,?,NOW())";
        return db.executeUpdate(sql, stuId, actId);
    }

    // 取消报名
    public int cancelSign(int stuId, int actId) {
        String sql = "DELETE FROM activity_sign WHERE student_id=? AND activity_id=?";
        return db.executeUpdate(sql, stuId, actId);
    }

    // 查询个人报名记录
    public ArrayList<HashMap<String, Object>> findMySign(int stuId) {
        String sql = "SELECT a.* FROM activity a JOIN activity_sign asg ON a.id=asg.activity_id WHERE asg.student_id=?";
        return db.preparedQuery(sql, stuId);
    }
}