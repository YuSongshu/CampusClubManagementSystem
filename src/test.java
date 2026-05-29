import utils.DbUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class test {
    public static void main(String[] args) {
        DbUtils db = new DbUtils();
        String sql = "select count(*) as total from club_member where club_id=?";
        ArrayList<HashMap<String, Object>> num = db.preparedQuery(sql, 1);
        int now = (((Number) num.get(0).get("total")).intValue());
        System.out.println(now);
    }
}
