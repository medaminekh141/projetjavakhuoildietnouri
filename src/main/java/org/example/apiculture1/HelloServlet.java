package org.example.apiculture1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Passer le message à la JSP
        request.setAttribute("message", message);

        // Rediriger vers la vue JSP
        request.getRequestDispatcher("/WEB-INF/views/form.jsp").forward(request, response);
    }

    public void destroy() {
    }
}


