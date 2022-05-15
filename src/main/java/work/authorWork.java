package work;

import domain.author;
import service.adminService;
import service.authorService;
import service.impl.adminServiceImpl;
import service.impl.authorServiceImpl;
import service.impl.bookServiceImpl;

import java.util.Scanner;

public class authorWork {
    public  void  author(){
        //登录
        authorService as = new authorServiceImpl();
        Scanner input = new Scanner(System.in);

        bookServiceImpl bsi =new bookServiceImpl() ;
        author au  = as.authorLogin();

        while (true){
            if(au!=null){
                System.out.println("==========1.所有作品 2.搜索作品 3.删除作品 4.新建作品 5.修改作品 6.修改密码 7.修改个人信息 8.退出==========");
                System.out.println("请输入你的选择:");
                int choose=input.nextInt();
                switch (choose) {
                    case 1:
                        bsi.getAllBookByid(au.getId());
                        //打印作品目录
                        break;
                    case 2:
                        System.out.println("请输入书的编号");
                        bsi.getBookById(input.nextInt());
                        //查找作品
                        break;
                    case 3:
                        System.out.println("请输入删除书的编号");
                        bsi.deleteBook(input.nextInt());
                        //删除作品
                        break;
                    case 4:
                        bsi.save(au.getId());
                        //新增作品
                        break;
                    case 5:
                        System.out.println("请输入要修改的编号");
                        bsi.updateBook(input.nextInt());
                        break;
                    case 6:
                        as.updateAuthorPassword(au.getId());
                        break;
                        //修改密码
                    case 7:
                        as.updateAuthor(au.getId());
                        break;
                        //修改作者信息
                    case 8:
                        return;
                }
            }
            else
                au  = as.authorLogin();
        }
    }
}
