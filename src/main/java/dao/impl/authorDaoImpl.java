package dao.impl;

import dao.authorDao;
import domain.admin;
import domain.author;
import utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class authorDaoImpl implements authorDao {
    @Override
    public List<author> listAuthor() {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;

        List<author> list = new ArrayList<>();
        String sql="select * from author";


        try {
            con= DBUtil.getConnection();
            pst=con.prepareStatement(sql);
            rs= pst.executeQuery();
            while (rs.next()) {
              author au = author.builder()
                      .id(rs.getInt("id"))
                      .name(rs.getString("name"))
                      .password(rs.getString("password"))
                      .introduction(rs.getString("introduction"))
                      .date_of_birth(rs.getDate("date_of_birth"))
                      .build();

              list.add(au);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBUtil.close(rs,pst,con);
        return list;
    }

    @Override
    public int saveAuthor(String name) {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;

        int authorId = 0;
        String sql = "insert into author(Name,password) values (?,'123')";
        try {
            con = DBUtil.getConnection();
            //返回自增长列值
            pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, name);
            pst.executeUpdate();
            //获取自增长列值（一行一列）
            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                authorId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pst, con);
        }
        return authorId;
    }

    @Override
    public int removeAuthor(Integer authorId) {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        int result = 0;
        String delMenusql = "delete from author where id=?";
        String delBusinesssql = "delete from book where author_id=?";
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);
            pst = con.prepareStatement(delMenusql);
            pst.setInt(1, authorId);
            pst.executeUpdate();

            pst = con.prepareStatement(delBusinesssql);
            pst.setInt(1, authorId);
            result = pst.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            result = 0;
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }

    @Override
    public author getAuthorByidBypassword(Integer id, String password) {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        author au = null;
        String sql="select * from author where id=? and password=?";
        try {
            con= DBUtil.getConnection();
            pst=con.prepareStatement(sql);
            pst.setInt(1,id);
            pst.setString(2,password);

            rs= pst.executeQuery();
            while (rs.next()) {
                au = au.builder()
                        .name(rs.getString("name"))
                        .id(rs.getInt("id"))
                        .password(rs.getString("password"))
                        .introduction(rs.getString("introduction"))
                        .date_of_birth(rs.getDate("date_of_birth"))
                        .build();
//                business = new Business();
//                business.setId(rs.getInt("businessid"));
//                business.setName(rs.getString("businessName"));
//                business.setAddress(rs.getString("businessAddress"));
//                business.setExptain(rs.getString("businessExptain"));
//                business.setStarPrice(rs.getDouble("starPrice"));
//                business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return au;
    }

    @Override
    public author getAuthorByid(Integer id) {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;

        author au = null;
        String sql="select * from author where id=?";
        try {
            con= DBUtil.getConnection();
            pst=con.prepareStatement(sql);
            pst.setInt(1,id);
            rs= pst.executeQuery();
            while (rs.next()) {
                au = au.builder()
                        .name(rs.getString("name"))
                        .id(rs.getInt("id"))
                        .password(rs.getString("password"))
                        .introduction(rs.getString("introduction"))
                        .date_of_birth(rs.getDate("date_of_birth"))
                        .build();
//                business = new Business();
//                business.setId(rs.getInt("businessid"));
//                business.setName(rs.getString("businessName"));
//                business.setAddress(rs.getString("businessAddress"));
//                business.setExptain(rs.getString("businessExptain"));
//                business.setStarPrice(rs.getDouble("starPrice"));
//                business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
//                business.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return au;
    }

    @Override
    public int updateAuthor(author au) {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;

        int result = 0;
        String sql = "update author set name=?,password=?,introduction=?,date_of_birth=? where id=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, au.getName());
            pst.setString(2,au.getPassword());
            pst.setString(3,au.getIntroduction());
            pst.setDate(4, (Date) au.getDate_of_birth());

            pst.setInt(5,au.getId());
            result = pst.executeUpdate();



        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }

    @Override
    public int upDateAuthorBypassword(Integer id, String password) {
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;

        int result = 0;
        String sql = "update author set password=? where id=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1,password );
            pst.setInt(2,id);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBUtil.close(null, pst, con);
        }
        return result;
    }
    }
