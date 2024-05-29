package qlks.utils;

import java.sql.*;

public class JdbcHelper {
    public static final String ConnectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=DBQLKS;"
            + "user=sa;password=123;encrypt = false";
    public static Connection getDB(){
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver "+ex.getMessage());
        }

        try {
            con = DriverManager.getConnection(ConnectionUrl);
            System.out.println("ok");
            return con;
        } catch (SQLException ex) {
            System.out.println("Lỗi DataBase "+ex.getMessage());
        }

        return null;
    }


    public static PreparedStatement getStmt(String sql, Object...args) throws SQLException{
        Connection conn = DriverManager.getConnection(ConnectionUrl);
        PreparedStatement pstmt = null;
        if(sql.trim().startsWith("{")){
            pstmt = conn.prepareCall(sql);
        }else{
            pstmt = conn.prepareStatement(sql);
        }
        for(int i = 0; i < args.length; i++){
            pstmt.setObject(i+1, args[i]);
        }

        return pstmt;
    }

    public static int update(String sql, Object...args){
        try {
            PreparedStatement stmt = getStmt(sql, args);
            try {
                return stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ResultSet query(String sql, Object...args){
        try {
            PreparedStatement stmt = getStmt(sql, args);
            return stmt.executeQuery();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Object value(String sql, Object...args){
        try {
            ResultSet rs = query(sql, args);
            if(rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
}
