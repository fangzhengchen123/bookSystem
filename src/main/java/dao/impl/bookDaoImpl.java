package dao.impl;

import dao.bookDao;
import domain.book;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class bookDaoImpl implements bookDao {
    @Override
    public List<book> ListBookByBusinessid(Integer id) {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;

        List<book> list = new ArrayList<>();
        String sql = "select * from book where author_id=?";
        try {
            book book = null;
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            while (rs.next()) {
                book = book.builder()
                        .id(rs.getInt("id"))
                        .authorId(rs.getInt("author_id"))
                        .introduction(rs.getString("introduction"))
                        .name(rs.getString("name"))
                        .build();
//                menu menu=new menu();
//                menu.setId(rs.getInt("foodid"));
//                menu.setName(rs.getString("foodName"));
//                menu.setExplain(rs.getString("foodExplain"));
//                menu.setPrice(rs.getDouble("foodPrice"));
//                menu.setBusinessid(rs.getInt("businessid"));
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return list;
    }

    @Override
    public int saveBook(book book) {

        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;

        int bookId = 0;
        String sql = "insert into book(name,introduction,author_id) values (?,?,?)";
        try {
            con = DBUtil.getConnection();
            //返回自增长列值
            pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, book.getName());
            pst.setString(2, book.getIntroduction());
            pst.setInt(3, book.getAuthorId());
            pst.executeUpdate();
            //获取自增长列值（一行一列）
            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                bookId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return bookId;
    }

    @Override
    public book getBookByid(Integer bookid) {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        String sql = "select * from book where id=?";
        book book = null;
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, bookid);
            rs = pst.executeQuery();
            while (rs.next()) {
                book = book.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .authorId(rs.getInt("author_id"))
                        .introduction(rs.getString("introduction"))
                        .build();
//                menu=new menu();
//                menu.setId(rs.getInt("foodid"));
//                menu.setName(rs.getString("foodName"));
//                menu.setExplain(rs.getString("foodExplain"));
//                menu.setPrice(rs.getDouble("foodPrice"));
//                menu.setBusinessid(rs.getInt("businessid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return book;
    }

    @Override
    public int upDateMenu(book book) {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;

        int result =0;
        String sql = "update book set name=?,introduction=? where id=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1,book.getName());
            pst.setString(2,book.getIntroduction());
            pst.setInt(3, book.getId());


            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }

    @Override
    public int removeBook(Integer bookid) {

        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        int result =0;
        String sql = "delete from book where id=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1,bookid);

            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }
}
