/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwr.edu.pl.session;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author asus
 */
@WebServlet("/user")
public class user_s1 extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //tutaj wypluj wszystkie ksiazki
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body");
        writer.println("<h2> user </h2>");
        writer.println();
        writer.println("</html>");
        writer.println("</body");

    }
 

}
