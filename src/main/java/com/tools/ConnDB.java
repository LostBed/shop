package com.tools;

import java.sql.*;

public class ConnDB {
    public Connection conn = null;
    public Statement stmt = null;
    public ResultSet rs = null;

    private static String dbClassName = "com.mysql.jdbc.Driver";
    private static String dbUrl = "jdbc:mysql://localhost:3306/db_shop?useSSL=false";
    private static String dbUser = "root";
    private static String dbPwd = "root";


    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(dbClassName).newInstance();
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (conn == null) {
            System.err.println("DbConnectionManager.getConnection():" +
                    dbClassName + "\r\n:" + dbUrl + "\r\n" + dbUser + "/" + dbPwd);
        }
        return conn;
    }

    public int executeUpdate(String sql){
        int result=0;
        try {
            conn=getConnection();
            stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            result=stmt.executeUpdate(sql);
        }catch (SQLException e){
            result=0;
            e.printStackTrace();
        }
        try {
            stmt.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }

    public ResultSet executeQuery(String sql){
        conn=getConnection();
        try {
            stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs=stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void close(){
        try {
            if (rs!=null){
                rs.close();
            }
            if (stmt!=null){
                stmt.close();
            }
            if (conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
