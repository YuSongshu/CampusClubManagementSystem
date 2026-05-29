import dao.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import utils.tool;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static int loginId;
    public static int loginRole;
    public static String loginName;

    public static void main(String[] args) {
        System.out.println("========== 校园社团管理系统 ==========");

        while (true) {
            System.out.println("是否退出系统：y/n");
            String b = sc.next();
            if (b.equals("y")) {
               break;
            }else if(b.equals("n")){
                System.out.println("登录");
                System.out.print("请输入账号：");
                String username = sc.next();
                System.out.print("请输入密码：");
                String pwd = sc.next();

                UserDao userDao = new UserDao();
                ArrayList<HashMap<String, Object>> userList = userDao.login(username, pwd);
                if (userList.isEmpty()) {
                    System.out.println("账号、密码错误、或账号已停用，请重试！");
                    continue;
                }

                HashMap<String, Object> user = userList.get(0);
                loginId = (Integer) user.get("id");
                loginRole = (Integer) user.get("role");
                loginName = (String) user.get("name");
                System.out.println("登录成功！欢迎您：" + loginName);

                if (loginRole == 1) {
                    adminMainMenu();
                } else {
                    studentMainMenu();
                }
            }else {
                System.out.println("输入错误！请重新输入！");
            }
        }
    }

    public static void adminMainMenu() {
        while (true) {
            System.out.println("\n---------- 管理员功能菜单 ----------");
            System.out.println("1. 用户管理");
            System.out.println("2. 社团管理");
            System.out.println("3. 活动管理");
            System.out.println("4. 申请审批");
            System.out.println("5. 数据统计");
            System.out.println("6. 修改个人密码");
            System.out.println("0. 退出登录");
            System.out.print("请选择功能编号：");

            if (!sc.hasNextInt()) {
                System.out.println("只有6个功能，请重新输入数字！");
                sc.next();
                continue;
            }
            int choice = sc.nextInt();

            tool t = new tool();

            if (choice == 0) {
                System.out.println("已退出登录\n");
                break;
            } else if (choice == 1) {
                t.adminUserManage();
            } else if (choice == 2) {
                t.adminClubManage();
            } else if (choice == 3) {
                t.adminActivityManage();
            } else if (choice == 4) {
                t.adminApplyAudit();
            } else if (choice == 5) {
                t. adminDataCount();
            } else if (choice == 6) {
                t.updatePwd(loginName, loginId);
            } else {
                System.out.println("输入编号不存在，请重新选择！");
            }
        }
    }



    // ===================== 学生主菜单 =====================
    public static void studentMainMenu() {
        while (true) {
            System.out.println("\n---------- 学生功能菜单 ----------");
            System.out.println("1. 浏览全部社团");
            System.out.println("2. 提交入社申请");
            System.out.println("3. 查看我的社团 & 提交退社申请");
            System.out.println("4. 查看全部活动 & 报名/取消报名");
            System.out.println("5. 查看个人活动记录");
            System.out.println("6. 修改个人密码");
            System.out.println("0. 退出登录");
            System.out.print("请选择功能编号：");

            if (!sc.hasNextInt()) {
                System.out.println("只有6个功能，请重新输入数字！");
                sc.next();
                continue;
            }

            tool t = new tool();

            int choice = sc.nextInt();
            if (choice == 0) {
                System.out.println("已退出登录\n");
                break;
            } else if (choice == 1) {
                t.studentViewClub();
            } else if (choice == 2) {
                t.studentApplyJoin(loginId);
            } else if (choice == 3) {
                t.studentMyClubAndQuit(loginId);
            } else if (choice == 4) {
                t.studentActivitySign(loginId);
            } else if (choice == 5) {
                t.studentViewMyRecord(loginId);
            } else if (choice == 6) {
                t.updatePwd(loginName, loginId);
            } else {
                System.out.println("输入编号不存在！");
            }
        }
    }

}