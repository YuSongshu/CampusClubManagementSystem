package utils;

import dao.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class tool {
    public  Scanner sc = new Scanner(System.in);

    // 管理员-用户管理
    public void adminUserManage() {
        System.out.println("\n---------- 用户管理 ----------");
        System.out.println("1. 查看所有用户  2. 新增学生账号  3. 修改用户信息  4. 停用/启用账号  5. 重置密码");
        System.out.print("选择操作：");
        int c = sc.nextInt();
        UserDao dao = new UserDao();
        if (c == 1) {
            ArrayList<HashMap<String, Object>> list = dao.findAllUser();
            for (HashMap<String, Object> m : list) {
                System.out.printf("ID:%d 账号:%s 姓名:%s 年级:%s 班级:%s 身份:%s 状态:%s%n",
                        (Integer) m.get("id"), m.get("username"), m.get("name"), m.get("grade"), m.get("clazz"),
                        (Integer) m.get("role") == 1 ? "管理员" : "学生", m.get("status"));
            }
        } else if (c == 2) {
            System.out.print("账号：");
            String u = sc.next();
            System.out.print("密码：");
            String p = sc.next();
            System.out.print("姓名：");
            String n = sc.next();
            System.out.print("年级：");
            String g = sc.next();
            System.out.print("班级：");
            String cl = sc.next();
            int res = dao.addStudent(u, p, n, g, cl);
            System.out.println(res > 0 ? "新增成功" : "新增失败");
        } else if (c == 3) {
            System.out.print("目标用户ID：");
            int id = sc.nextInt();
            System.out.print("新姓名：");
            String n = sc.next();
            System.out.print("新年级：");
            String g = sc.next();
            System.out.print("新班级：");
            String cl = sc.next();
            int res = dao.updateUserInfo(id, n, g, cl);
            System.out.println(res > 0 ? "修改成功" : "修改失败");
        } else if (c == 4) {
            System.out.print("用户ID：");
            int id = sc.nextInt();
            System.out.print("设置状态(正常/停用)：");
            String s = sc.next();
            int res = dao.updateUserStatus(id, s);
            System.out.println(res > 0 ? "操作成功" : "操作失败");
        } else if (c == 5) {
            System.out.print("用户ID：");
            int id = sc.nextInt();
            System.out.print("新密码：");
            String p = sc.next();
            int res = dao.updatePwd(id, p);
            System.out.println(res > 0 ? "密码重置成功" : "重置失败");
        }
    }

    // 管理员-社团管理
    public  void adminClubManage() {
        System.out.println("\n---------- 社团管理 ----------");
        System.out.println("1. 查看所有社团  2. 创建新社团  3. 修改社团资料  4. 注销社团");
        System.out.print("选择操作：");
        int c = sc.nextInt();
        ClubDao dao = new ClubDao();
        if (c == 1) {
            ArrayList<HashMap<String, Object>> list = dao.findAllClub();
            for (HashMap<String, Object> m : list) {
                System.out.printf("ID:%d 名称:%s 简介:%s 人数上限:%s 创建时间:%s 状态:%s 经费:%.2f%n",
                        (Integer) m.get("id"), m.get("name"), m.get("info"), m.get("max_num"),
                        m.get("create_time"), m.get("status"), ((java.math.BigDecimal)m.get("fund")).doubleValue());
            }
        } else if (c == 2) {
            System.out.print("社团名称：");
            String name = sc.next();
            System.out.print("社团简介：");
            String info = sc.next();
            System.out.print("人数上限：");
            int max = sc.nextInt();
            int res = dao.addClub(name, info, max);
            System.out.println(res > 0 ? "社团创建成功" : "创建失败");
        } else if (c == 3) {
            sc.next();
            System.out.print("社团ID：");
            int id = sc.nextInt();
            sc.next();
            System.out.print("新名称：");
            String name = sc.next();
            System.out.print("新简介：");
            String info = sc.next();
            System.out.print("新人数上限：");
            int max = sc.nextInt();
            int res = dao.updateClub(id, name, info, max);
            System.out.println(res > 0 ? "修改成功" : "修改失败");
        } else if (c == 4) {
            System.out.print("社团ID：");
            int id = sc.nextInt();
            int res = dao.cancelClub(id);
            System.out.println(res > 0 ? "社团已注销" : "操作失败");
        }
    }

    // 管理员-活动管理
    public  void adminActivityManage() {
        System.out.println("\n---------- 活动管理 ----------");
        System.out.println("1. 查看所有活动  2. 发布新活动  3. 活动上架/下架  4. 标记活动结束  5. 按条件查询活动");
        System.out.print("选择操作：");
        int c = sc.nextInt();
        ActivityDao dao = new ActivityDao();
        if (c == 1) {
            ArrayList<HashMap<String, Object>> list = dao.findAllActivity();
            for (HashMap<String, Object> m : list) {
                System.out.printf("活动ID:%d 社团ID:%d 主题:%s 地点:%s 时间:%s 状态:%s%n",
                        (Integer) m.get("id"), (Integer) m.get("club_id"), m.get("title"), m.get("place"), m.get("act_time"), m.get("status"));
            }
        } else if (c == 2) {
            System.out.print("所属社团ID：");
            int cid = sc.nextInt();
            System.out.print("活动主题：");
            String title = sc.next();
            System.out.print("活动地点：");
            String place = sc.next();
            System.out.print("活动时间(yyyy-MM-dd HH:mm:ss)：");
            String time = sc.next();
            System.out.print("人数限制：");
            int max = sc.nextInt();
            sc.next();
            System.out.print("活动内容：");
            String content = sc.next();
            int res = dao.addActivity(cid, title, place, time, max, content);
            System.out.println(res > 0 ? "活动提交待审核" : "提交失败");
        } else if (c == 3) {
            System.out.print("活动ID：");
            int aid = sc.nextInt();
            System.out.print("设置状态(进行中/下架)：");
            String s = sc.next();
            int res = dao.updateActivityStatus(aid, s);
            System.out.println(res > 0 ? "状态修改成功" : "修改失败");
        } else if (c == 4) {
            System.out.print("活动ID：");
            int aid = sc.nextInt();
            int res = dao.finishActivity(aid);
            System.out.println(res > 0 ? "已标记为已结束" : "操作失败");
        } else if (c == 5) {
            System.out.println("1.按社团名称查询  2.按活动日期查询");
            int sub = sc.nextInt();
            if (sub == 1) {
                sc.next();
                System.out.print("社团名称：");
                String name = sc.next();
                ArrayList<HashMap<String, Object>> list = dao.findActivityByClubName(name);
                list.forEach(System.out::println);
            } else {
                System.out.print("日期(yyyy-MM-dd)：");
                String t = sc.next();
                ArrayList<HashMap<String, Object>> list = dao.findActivityByTime(t);
                list.forEach(System.out::println);
            }
        }
    }

    // 管理员-申请审批
    public  void adminApplyAudit() {
        System.out.println("\n---------- 申请审批 ----------");
        ApplyDao dao = new ApplyDao();
        ArrayList<HashMap<String, Object>> list = dao.findAllApply();
        for (HashMap<String, Object> m : list) {
            System.out.printf("申请ID:%d 学生ID:%d 社团ID:%d 类型:%s 理由:%s 状态:%s%n",
                    (Integer) m.get("id"), (Integer) m.get("student_id"), (Integer) m.get("club_id"), m.get("apply_type"),
                    m.get("reason"), m.get("status"));
        }
        System.out.print("请输入要审批的申请ID：");
        int aid = sc.nextInt();
        System.out.print("审批结果(同意/驳回)：");
        String res = sc.next();
        int r = dao.auditApply(aid, res);
        if (r > 0) {
            System.out.println("审批完成");
            HashMap<String, Object> apply = dao.findAllApply().get(0);
            int sid = (Integer) apply.get("student_id");
            int cid = (Integer) apply.get("club_id");
            String type = (String) apply.get("apply_type");
            if ("同意".equals(res)) {
                if ("入社".equals(type)) {
                    ClubDao c = new ClubDao();
                    DbUtils db = new DbUtils();
                    String sql = "select count(*) as total from club_member where club_id=?";
                    ArrayList<HashMap<String, Object>> num = db.preparedQuery(sql, cid);
                    int now = (((Number) num.get(0).get("total")).intValue());
                    ArrayList<HashMap<String, Object>> clubList = c.findClubById(cid);
                    if (clubList != null && !clubList.isEmpty()) {
                        HashMap<String, Object> club = clubList.get(0);
                        if (club.get("max_num") != null) {
                            int maxNum = ((Number) club.get("max_num")).intValue();
                            if (now < maxNum) {
                                dao.addMember(sid, cid);
                            } else {
                                System.out.println("社团人数已满，已删除该申请");
                                db.executeUpdate("delete from apply where id=?", aid);
                            }
                        }
                    }else{
                        System.out.println("社团信息不存在");
                    }
                }else {
                    dao.delMember(sid, cid);
                }
            }
        } else {
            System.out.println("审批失败");
        }
    }

    // 管理员-数据统计
    public  void adminDataCount() {
        System.out.println("\n---------- 数据统计 ----------");
        ClubDao cDao = new ClubDao();
        ActivityDao aDao = new ActivityDao();
        // 社团总数
        ArrayList<HashMap<String, Object>> clubTotal = cDao.countClub();
        System.out.println("全校社团总数：" + clubTotal.get(0).get("total"));
        // 各社团人数
        System.out.println("\n各社团社员人数：");
        cDao.countMemberPerClub().forEach(m -> System.out.println("社团:" + m.get("name") + " 人数:" + m.get("member_num")));
        // 月度活动统计
        System.out.println("\n月度活动举办数量：");
        aDao.countActivityByMonth().forEach(m -> System.out.println("月份:" + m.get("month") + " 活动数:" + m.get("act_num")));
    }




    // 学生-浏览社团
    public  void studentViewClub() {
        System.out.println("\n---------- 全校社团信息 ----------");
        ClubDao dao = new ClubDao();
        ArrayList<HashMap<String, Object>> list = dao.findAllNormalClub();
        for (HashMap<String, Object> m : list) {
            System.out.printf("ID:%d 名称:%s 简介:%s 人数上限:%s 创建时间:%s 状态:%s 经费:%.2f%n",
                    (Integer) m.get("id"),m.get("name"),m.get("info"),m.get("max_num"),
                    m.get("create_time"),m.get("status"), ((java.math.BigDecimal)m.get("fund")).doubleValue());
        }
    }

    // 学生-提交入社申请
    public boolean hasApply(int stuId) {
        DbUtils db = new DbUtils();
        String sql = "SELECT * FROM apply WHERE student_id=? ";
        ArrayList<HashMap<String,Object>> list = db.preparedQuery(sql, stuId);
        return !list.isEmpty();
    }

    public  void studentApplyJoin(int loginId) {
        DbUtils db = new DbUtils();
        if (!hasApply(loginId)){
            System.out.print("目标社团ID：");
            int cid = sc.nextInt();
            String sql2 = "SELECT COUNT(*) num FROM club_member WHERE club_id=?";
            ArrayList<HashMap<String, Object>> num = db.preparedQuery(sql2, cid);
            int now = Integer.parseInt(num.get(0).get("num").toString());
            String sql3 = "SELECT max_num FROM club WHERE id=?";
            ArrayList<HashMap<String,Object>> maxList = db.preparedQuery(sql3, cid );
            int max = Integer.parseInt(maxList.get(0).get("max_num").toString());
            if (now >= max) {
                System.out.println("社团人数已满");
                return;
            }

            System.out.print("个人特长/申请理由：");
            String reason = sc.next();
            ApplyDao dao = new ApplyDao();
            int res = dao.addJoinApply(loginId, cid, reason);
            System.out.println(res>0?"入社申请提交成功，等待审核":"提交失败");
        }else{
            System.out.println("提交失败,已提交申请或已有社团");
        }

    }

    // 学生-我的社团+退社申请
    public  void studentMyClubAndQuit(int loginId) {
        System.out.println("\n---------- 我已加入的社团 ----------");
        ClubDao dao = new ClubDao();
        ArrayList<HashMap<String, Object>> list = dao.findClubByStudent(loginId);
        if (list.isEmpty()) {
            System.out.println("暂无已加入社团");
            return;
        }
        list.forEach(System.out::println);
        System.out.print("是否提交退社申请？(1是 0否)：");
        int opt = sc.nextInt();
        if (opt == 1) {
            System.out.print("社团ID："); int cid = sc.nextInt();
            sc.next();
            System.out.print("退社原因："); String reason = sc.next();
            ApplyDao aDao = new ApplyDao();
            int res = aDao.addQuitApply(loginId, cid, reason);
            System.out.println(res>0?"退社申请已提交":"提交失败");
        }
    }

    // 学生-活动报名/取消
    public  void studentActivitySign(int loginId) {
        System.out.println("\n---------- 全部社团活动 ----------");
        ActivityDao aDao = new ActivityDao();
        ArrayList<HashMap<String, Object>> list = aDao.findAllActivity();
        list.forEach(System.out::println);
        System.out.println("1.报名活动  2.取消报名");
        int opt = sc.nextInt();
        ActivitySignDao sDao = new ActivitySignDao();
        System.out.print("活动ID："); int aid = sc.nextInt();
        if (opt == 1) {
            int res = sDao.signActivity(loginId, aid);
            System.out.println(res>0?"报名成功":"报名失败");
        } else {
            int res = sDao.cancelSign(loginId, aid);
            System.out.println(res>0?"已取消报名":"操作失败");
        }
    }

    // 学生-查看个人活动记录
    public void studentViewMyRecord(int loginId) {
        System.out.println("\n---------- 我的活动参与记录 ----------");
        ActivitySignDao dao = new ActivitySignDao();
        ArrayList<HashMap<String, Object>> list = dao.findMySign(loginId);

        if (list.isEmpty()) {
            System.out.println("暂无参与记录");
        } else {
            for (HashMap<String, Object> m : list) {
                System.out.printf("活动ID:%d   主题:%s   地点:%s   时间:%s   人数限制:%d   内容:%s   状态:%s%n",
                        (Integer) m.get("id"),
                        m.get("title"),
                        m.get("place"),
                        m.get("act_time"),
                        (Integer) m.get("max_num"),
                        m.get("content"),
                        m.get("status")
                );
            }
        }
    }






    // 通用
    // 修改个人密码
    public void updatePwd(String loginName, int loginId) {
        System.out.print("请输入原密码：");
        String oldPwd = sc.next();
        UserDao dao = new UserDao();
        ArrayList<HashMap<String, Object>> user = dao.login(loginName, oldPwd);
        if (user.isEmpty()) {
            System.out.println("原密码错误，修改失败");
            return;
        }
        System.out.print("请输入新密码：");
        String newPwd = sc.next();
        int res = dao.updatePwd(loginId, newPwd);
        System.out.println(res > 0 ? "密码修改成功" : "修改失败");
    }

    
}