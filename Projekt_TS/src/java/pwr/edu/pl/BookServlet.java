/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwr.edu.pl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pwr.edu.pl.model.Book;

/**
 *
 * @author asus
 */
@WebServlet("/books")
public class BookServlet extends HttpServlet {
    
List<Book> bookList = null;
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String param = request.getParameter("get");
       
       if("show".equals(param)){
           try {
                bookList = getBooks();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("bookList", bookList);
            request.getRequestDispatcher("bookList.jsp").forward(request, response); 
                }else{
            response.sendError(403);
         }
       
    }

  
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String command = (String) request.getParameter("command");
        String idString = request.getParameter("id");
        String title = (String) request.getParameter("title");
        String author = (String) request.getParameter("author");
        String isbn = (String) request.getParameter("isbn");
        String description = (String) request.getParameter("description");
        boolean status = Boolean.getBoolean(request.getParameter("status")) ;
        
        if("dodaj".equals(command)){
             if(!("".equals(idString) || "".equals(title) || "".equals(author) || "".equals(isbn) || "".equals(description))){
               int id = Integer.parseInt(idString);
            Book book=new Book(id,title,author,isbn, description,status);
            bookList.add(book);
            //CREATE zapis do bazy danych !!!
            //to żadanie htpp POST
             
    }
        }else if("usun".equals(command)){
            if(!("".equals(idString))){
                int id = Integer.parseInt(idString);
            
            for(Book book : bookList){
                if(book.getId()==id){
                    bookList.remove(book);
                    //DELETE to żadanie http:delete 
                }
            }
            }
        }else if("edytuj".equals(command)){
            if(!("".equals(idString) || "".equals(title) || "".equals(author) || "".equals(isbn) || "".equals(description))){
                int id=Integer.parseInt(idString);
                
             Book book1 = new Book(id,title,author,isbn, description,status);
             Book b2 = null;
            for(Book book : bookList){
                if(book.getId()==id){
                    b2 = book;
                }
            }
            if(b2!=null){
                bookList.remove(b2);
                bookList.add(b2);
                //UPDATE to  żadanie http: PUT
            }
            }
        }
        response.sendRedirect("first?get=show");
    }

    
    
    
     private List<Book> getBooks() throws ClassNotFoundException, SQLException{
        final String driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);
        final String dbPath = "jdbc:mysql://localhost:3306/books?:useSSL=false&serverTimezone=UTC";
        final String sqlQuery = "SELECT * FROM books.books";
        
         Connection conn = DriverManager.getConnection(dbPath,"root","beverly90210");
           
                Statement stat = conn.createStatement();
                ResultSet result = stat.executeQuery(sqlQuery);
                
                while(result.next()){
                    int id = result.getInt("id");
                    String title = result.getString("title");
                    String author = result.getString("author");
                    String isbn = result.getString("isbn");
                    String description = result.getString("description");
                    boolean status = result.getBoolean("status");
                    Book book = new Book(id, title, author, isbn, description, status);
                    System.out.println(book);
                    bookList.add(book);
                }
          
            return bookList;
    }
   
}
