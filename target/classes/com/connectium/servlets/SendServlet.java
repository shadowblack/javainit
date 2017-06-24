package com.connectium.servlets;

import com.connectium.controllers.Email;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by lmarin on 12/6/2017.
 */
@WebServlet(urlPatterns = "/send_mail",name = "Send")
public class SendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String string_email = request.getParameter("email");
        String message = request.getParameter("message");

        String newMenssage = "Has recibido un mensaje del usuario :"
                +"<br />Nombre:"+name
                +"<br />Email:"+string_email
                +"<br />Mensaje:"+message
                ;

        Email email = new Email();
        email.sendMail(newMenssage);

        PrintWriter out = response.getWriter();

        out.print("{\"status\":1}");
    }
}
