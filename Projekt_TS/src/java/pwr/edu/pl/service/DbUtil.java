/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwr.edu.pl.service;



import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author asus
 */
public class DbUtil {
    private static DbUtil dbUtil;
    private static BasicDataSource ds;

    public DbUtil() {
        ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/books?useSSL=false&serverTimezone=UTC");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("beverly90210");
    }
    
    public static Connection getConnection()throws SQLException{
        return ds.getConnection();
    }
    
    public void close() throws SQLException{
        ds.close();
    }
    
    public static DbUtil getInstance(){
        if(dbUtil == null){
            dbUtil = new DbUtil();
        }
        return dbUtil;
    }
    
}
