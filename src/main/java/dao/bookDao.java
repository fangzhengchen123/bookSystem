package dao;


import domain.book;


import java.util.List;

public interface bookDao {
     List<book> ListBookByBusinessid(Integer id);
     int saveBook(book book );
     book getBookByid(Integer bookid);
     int  upDateMenu(book book);
     int  removeBook(Integer bookid);
}
