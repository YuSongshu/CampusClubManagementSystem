package dao;

import utils.DbUtils;
import java.util.ArrayList;
import java.util.HashMap;

public class ApplyDao {
    private DbUtils db = new DbUtils();

    // 提交入社申请
    public int addJoinApply(int stuId, int clubId, String reason) {
        String sql = "INSERT INTO apply(student_id,club_id,reason,apply_type,status,create_time) VALUES(?,?,?,'入社','待审核',NOW())";
        return db.executeUpdate(sql, stuId, clubId, reason);
    }

    // 提交退社申请
    public int addQuitApply(int stuId, int clubId, String reason) {
        String sql = "INSERT INTO apply(student_id,club_id,reason,apply_type,status,create_time) VALUES(?,?,?,'退社','待审核',NOW())";
        return db.executeUpdate(sql, stuId, clubId, reason);
    }

    // 查询所有申请记录（管理员）
    public ArrayList<HashMap<String, Object>> findAllApply() {
        return db.preparedQuery("SELECT * FROM apply");
    }

    // 审核申请
    public int auditApply(int applyId, String status) {
        String sql = "UPDATE apply SET status=? WHERE id=?";
        return db.executeUpdate(sql, status, applyId);
    }

    // 审核通过后加入社员表
    public int addMember(int stuId, int clubId) {
        String sql = "INSERT INTO club_member(student_id,club_id,join_time) VALUES(?,?,NOW())";
        return db.executeUpdate(sql, stuId, clubId);
    }

    // 退社通过后删除社员记录
    public int delMember(int stuId, int clubId) {
        String sql = "DELETE FROM club_member WHERE student_id=? AND club_id=?";
        return db.executeUpdate(sql, stuId, clubId);
    }
}