<%-- 
    Document   : bookList
    Created on : 2019-04-11, 17:50:11
    Author     : asus
--%>

<%@page import="pwr.edu.pl.model.Book"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KSIAZKI:</title>
    </head>
    <body>
        <h1>Hello World!</h1>
         <h1>Lista ksiazek</h1>
        <table style="width: 50%" border="1">
            <tr>
                <th>id</th>
                <th>tytul</th>
                <th>autor</th>
                <th>isbn</th>
                <th>description</th>
                <th>status</th>
            </tr>
            <tr>
                <%
                    List<Book> bookList = (List<Book>) request.getAttribute("bookList");
                    if(bookList != null){
                        for(Book s: bookList){
                         %>
                            <tr>
                                <td><%= s.getId()%></td>
                                <td><%= s.getTitle() %></td>
                                <td><%= s.getAuthor() %></td>
                                 <td><%= s.getIsbn() %></td>
                                <td><%= s.getDescription()%></td>
                                 <td><%= s.isStatus() %></td>
                            </tr>
                     <%
                        }
                    }
                    %>
        </tr>
        </table>
        
        <form method="post"  action="first" >
                        Wpisz id<br />
                        <input type="text" name ="id" /><br />
                        Wpisz autora:<br />
                        <input type="text" name="author"> <br />
                        Wpisz tytuł:<br />
                        <input type="text" name="title" /> <br />
                        Wpisz ISBN:<br />
                        <input type ="text" name="isbn" /> <br />
                        Wpisz opis:<br />
                        <input type="text" name="description" /> <br />
                        Wpisz status:<br />
                        <input type="text" name="status" /> <br />
                        
                        <input type="radio" name="command" value="dodaj"/> Dodaj
                        <input type="radio" name="command" value="usun"/> Usuń
                        <input type="radio" name="command" value="edytuj"/> Edytuj
                        <input type="submit" value="Zatwierdź">
                     </form>      
    </body>
</html>
