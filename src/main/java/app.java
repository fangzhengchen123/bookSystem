
import work.adminWork;
import work.authorWork;

import java.text.ParseException;
import java.util.Scanner;

public class app {
    public static void main(String[] args) throws ParseException {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("|\t\t\t\t 图书后台管理系统 \t\t\t\t|");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("请选择你的身份：");
        System.out.println("1.管理员");
        System.out.println("2.作者");
        Scanner input = new Scanner(System.in);
        int choose = input.nextInt();
        if(choose==1) {
            adminWork aw = new adminWork();
            aw.admin();
        }
        else {
            authorWork au = new authorWork();
            au.author();
        }
    }
}
