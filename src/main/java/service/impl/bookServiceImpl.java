package service.impl;

import dao.authorDao;
import dao.bookDao;
import dao.impl.authorDaoImpl;
import dao.impl.bookDaoImpl;
import domain.author;
import domain.book;
import service.bookService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class bookServiceImpl implements bookService {
    @Override
    public void getAllBookByid(Integer authorId) {
        bookDao bookdao = new bookDaoImpl();
        System.out.println("作品编号 \t 作品名称 \t 作品介绍 \t  ");
        List<book> list = new ArrayList<>();
        list = bookdao.ListBookByBusinessid(authorId);
        for (book b :
                list) {
            System.out.println(b.getId() + "\t" + b.getName() + "\t" + b.getIntroduction() + "\t");
        }
    }

    @Override
    public void save(Integer authorId) {
        book bk= new book();
        bookDao bookdao = new bookDaoImpl();
        Scanner input= new Scanner(System.in);
        bk.setAuthorId(authorId);
        System.out.println("请输入书的名称");
        bk.setName(input.next());
        System.out.println("请输入书本介绍");
        bk.setIntroduction(input.next());
        int res = bookdao.saveBook(bk);
        if(res>0)
            System.out.println("新增成功");
        else
            System.out.println("新增失败");
    }

    @Override
    public void getBookById(Integer bookid) {
//        Scanner input = new Scanner(System.in);
        bookDao bookdao = new bookDaoImpl();
//        System.out.println("请输入查找书籍的编号");
        book book = bookdao.getBookByid(bookid);
        if(book==null){
            System.out.println("查无此书");
            return;
        }

        System.out.println("作品编号 \t 作品名称 \t 作品介绍 \t  ");
        System.out.println(book.getId() + "\t" + book.getName() + "\t" + book.getIntroduction() + "\t");
    }

    @Override
    public void updateBook(Integer bookid) {
        Scanner input = new Scanner(System.in);
        bookDao bookDao = new bookDaoImpl();
        book bk = bookDao.getBookByid(bookid);
        if(bk!=null) {
            System.out.println(bk);
        }
        else {
            System.out.println("查无此书");
            return;
        }
        System.out.println("是否更新作品名称（y/n）");
        if(input.next().equals("y")){
            System.out.println("请输入作品名称");
            bk.setName(input.next());
        }
        System.out.println("是否更新作品介绍（y/n）");
        if(input.next().equals("y")){
            System.out.println("请输入作品介绍");
            bk.setIntroduction(input.next());
        }


        int result = bookDao.upDateMenu(bk);
        if(result>0)
            System.out.println("修改成功");
        else
            System.out.println("修改失败");

    }

    @Override
    public void deleteBook(Integer bookid) {
        Scanner input = new Scanner(System.in);
        bookDao bookDao = new bookDaoImpl();
//        System.out.println("请输入书本编号");
        int res = bookDao.removeBook(bookid);
        if(res>0)
            System.out.println("修改成功");
        else
            System.out.println("修改失败");

    }
}
