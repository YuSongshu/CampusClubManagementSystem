package dao;

import utils.DbUtils;
import java.util.ArrayList;
import java.util.HashMap;

public class ClubDao {
    private DbUtils db = new DbUtils();

    // 查询所有正常社团（学生）
    public ArrayList<HashMap<String, Object>> findAllNormalClub() {
        String sql = "SELECT * FROM club WHERE status='正常'";
        return db.preparedQuery(sql);
    }

    // 查询全部社团（管理员）
    public ArrayList<HashMap<String, Object>> findAllClub() {
        return db.preparedQuery("SELECT * FROM club");
    }

    // 创建新社团
    public int addClub(String name, String info, int maxNum) {
        String sql = "INSERT INTO club(name,info,max_num,create_time,status,fund) VALUES(?,?,?,CURDATE(),'正常',0)";
        return db.executeUpdate(sql, name, info, maxNum);
    }

    // 修改社团资料&人数上限
    public int updateClub(int id, String name, String info, int maxNum) {
        String sql = "UPDATE club SET name=?,info=?,max_num=? WHERE id=?";
        return db.executeUpdate(sql, name, info, maxNum, id);
    }

    // 注销社团
    public int cancelClub(int id) {
        String sql = "DELETE FROM club WHERE id=?";
        return db.executeUpdate(sql, id);
    }

    // 查询学生已加入社团
    public ArrayList<HashMap<String, Object>> findClubByStudent(int stuId) {
        String sql = "SELECT c.* FROM club c JOIN club_member cm ON c.id=cm.club_id WHERE cm.student_id=?";
        return db.preparedQuery(sql, stuId);
    }

    // 统计社团总数
    public ArrayList<HashMap<String, Object>> countClub() {
        return db.preparedQuery("SELECT COUNT(*) total FROM club");
    }

    // 统计每个社团人数
    public ArrayList<HashMap<String, Object>> countMemberPerClub() {
        String sql = "SELECT c.name,COUNT(cm.id) member_num FROM club c LEFT JOIN club_member cm ON c.id=cm.club_id GROUP BY c.id";
        return db.preparedQuery(sql);
    }

    public ArrayList<HashMap<String, Object>> findClubById(int id) {
        String sql = "SELECT * FROM club WHERE id=?";
        return db.preparedQuery(sql, id);
    }
}