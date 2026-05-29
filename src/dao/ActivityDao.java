package dao;

import utils.DbUtils;
import java.util.ArrayList;
import java.util.HashMap;

public class ActivityDao {
    private DbUtils db = new DbUtils();

    // 发布活动
    public int addActivity(int clubId, String title, String place, String actTime, int maxNum, String content) {
        String sql = "INSERT INTO activity(club_id,title,place,act_time,max_num,content,status) VALUES(?,?,?,?,?,?,'待发布')";
        return db.executeUpdate(sql, clubId, title, place, actTime, maxNum, content);
    }

    // 审核：发布/下架活动
    public int updateActivityStatus(int actId, String status) {
        String sql = "UPDATE activity SET status=? WHERE id=?";
        return db.executeUpdate(sql, status, actId);
    }

    // 标记活动已结束
    public int finishActivity(int actId) {
        return updateActivityStatus(actId, "已结束");
    }

    // 查询所有活动
    public ArrayList<HashMap<String, Object>> findAllActivity() {
        return db.preparedQuery("SELECT * FROM activity");
    }

    // 按社团名称查询活动
    public ArrayList<HashMap<String, Object>> findActivityByClubName(String clubName) {
        String sql = "SELECT a.* FROM activity a JOIN club c ON a.club_id=c.id WHERE c.name LIKE ?";
        return db.preparedQuery(sql, "%" + clubName + "%");
    }

    // 按活动时间查询
    public ArrayList<HashMap<String, Object>> findActivityByTime(String time) {
        String sql = "SELECT * FROM activity WHERE DATE(act_time)=?";
        return db.preparedQuery(sql, time);
    }

    // 月度活动统计
    public ArrayList<HashMap<String, Object>> countActivityByMonth() {
        String sql = "SELECT DATE_FORMAT(act_time,'%Y-%m') month,COUNT(*) act_num FROM activity GROUP BY month";
        return db.preparedQuery(sql);
    }
}