package service;

import domain.author;

import java.text.ParseException;

public interface authorService {
    void  getAllAuthorList();
    void  save();
    void  delete();
    author authorLogin();
    void  getAuthorByid();
    void  updateAuthor(Integer id);
    void  updateAuthorPassword(Integer id);
}
