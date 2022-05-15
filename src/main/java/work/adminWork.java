package work;

import service.adminService;
import service.authorService;
import service.impl.adminServiceImpl;
import service.impl.authorServiceImpl;

import java.util.Scanner;

public class adminWork {
    public  void  admin(){
    //登录
    authorService as = new authorServiceImpl();
    Scanner input = new Scanner(System.in);

    adminService ads = new adminServiceImpl();
    Boolean login  = ads.adminLogin();

    while (true){
    if(login==true){
        System.out.println("==========1.所有作家 2.搜索作家 3.删除作家 4.新建作家 5.修改作家 6.退出==========");
        System.out.println("请输入你的选择:");
        int choose=input.nextInt();
        switch (choose) {
            case 1:
                as.getAllAuthorList();
                //打印作家目录
                break;
            case 2:
                as.getAuthorByid();
                //查找作家
                break;
            case 3:
                as.delete();
                //删除作家
                break;
            case 4:
                as.save();
                //新增作家
                break;
            case 5:
                System.out.println("请输入作家编号");
                as.updateAuthor(input.nextInt());
                break;
            case 6:
                return;

        }
    }
    else
         login  =  ads.adminLogin();;
    }
}
}
