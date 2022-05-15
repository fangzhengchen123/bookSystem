package service;

import domain.book;

import java.util.List;

public interface bookService {
    void getAllBookByid(Integer id);
    void save(Integer id);
    void getBookById(Integer bookid);
    void updateBook(Integer bookid);
    void deleteBook(Integer bookid);
}
