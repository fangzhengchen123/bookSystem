package service.impl;

import dao.authorDao;
import dao.impl.authorDaoImpl;
import domain.author;
import service.authorService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class authorServiceImpl implements authorService {
    @Override
    public void getAllAuthorList() {
        authorDao authorDao = new authorDaoImpl();
        System.out.println("作家编号 \t 作家名称 \t 作家介绍 \t 出生日期 ");
        List<author> list = new ArrayList<>();
        list = authorDao.listAuthor();
        for (author a:
                list) {
            System.out.println(a.getId()+"\t"+a.getName()+"\t"+a.getIntroduction()+"\t"+a.getDate_of_birth()+"\t");
        }
    }

    @Override
    public void save() {
        authorDao authordao = new authorDaoImpl();
        Scanner input = new Scanner(System.in);
        System.out.println("请输入作家名称");
        String name = input.next();
        int number = authordao.saveAuthor(name);
        if (number!=0){
            System.out.println("添加成功");
        }
        else
            System.out.println("添加失败");

    }

    @Override
    public void delete() {
        Scanner input = new Scanner(System.in);
        authorDao authorDao = new authorDaoImpl();
        System.out.println("请输入需要删除的作家编号");
        int res = authorDao.removeAuthor(input.nextInt());
        if(res>0)
            System.out.println("删除成功");
        else
            System.out.println("删除失败");
    }

    @Override
    public author authorLogin() {
        authorDao authordao = new authorDaoImpl();
        Scanner input = new Scanner(System.in);
        System.out.println("请输入账号");
        int id = input.nextInt();
        System.out.println("请输入密码");
        String name = input.next();
        author au = authordao.getAuthorByidBypassword(id, name);
        if(au != null){
            System.out.println("登录成功");
            return au;
        }
        else
            System.out.println("账号或密码错误");
        return au;
    }

    @Override
    public void getAuthorByid() {
        Scanner input   =   new Scanner(System.in);
        authorDao authordao = new authorDaoImpl();
        System.out.println("请输入编号");
        author au = authordao.getAuthorByid(input.nextInt());
        if(au== null){
            System.out.println("查无此人");
            return;
        }
        System.out.println("作家编号 \t 作家名称 \t 作家介绍 \t 出生日期 ");
        System.out.println(au.getId()+"\t"+au.getName()+"\t"+au.getIntroduction()+"\t"+au.getDate_of_birth()+"\t");
    }

    @Override
    public void updateAuthor(Integer id)  {
        Scanner input = new Scanner(System.in);
        authorDao authordao = new authorDaoImpl();
        author author = authordao.getAuthorByid(id);
        System.out.println(author);

        System.out.println("是否更新作家名称（y/n）");
        if(input.next().equals("y")){
            System.out.println("请输入作家名称");
            author.setName(input.next());
        }
        System.out.println("是否更新作家介绍（y/n）");
        if(input.next().equals("y")){
            System.out.println("请输入作家介绍");
            author.setIntroduction(input.next());
        }
        System.out.println("是否更新出生日期（y/n）");
        if(input.next().equals("y")){
            System.out.println("请输入出生日期");
            String da = input.next();
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(da);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            author.setDate_of_birth(date);
        }

        int result = authordao.updateAuthor(author);
        if(result>0)
            System.out.println("修改成功");
        else
            System.out.println("修改失败");
    }

    @Override
    public void updateAuthorPassword(Integer id) {
        Scanner input= new Scanner(System.in);
        authorDao authordao = new authorDaoImpl();
        System.out.println("请输入新密码");
        String oldPass = input.next();
        System.out.println("请再次输入密码");
        String newPass =input.next();
        if(oldPass.equals(newPass)){
            int res = authordao.upDateAuthorBypassword(id, newPass);
            if(res>0)
                System.out.println("修改成功");
            else
                System.out.println("修改失败");
        }
        else
            System.out.println("两次密码不相同");
    }
}
