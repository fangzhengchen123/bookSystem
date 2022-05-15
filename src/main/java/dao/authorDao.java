package dao;

import domain.author;

import java.util.List;

public interface authorDao {
    List<author> listAuthor();
    int saveAuthor(String name);
    int removeAuthor(Integer authorId);
    author getAuthorByidBypassword(Integer id , String password);
    author getAuthorByid(Integer id);
    int updateAuthor(author au);
    int upDateAuthorBypassword(Integer id,String password);



}
