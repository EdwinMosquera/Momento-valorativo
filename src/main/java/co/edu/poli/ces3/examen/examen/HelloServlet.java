package co.edu.poli.ces3.examen.examen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    private GsonBuilder gsonBuilder;

    private Gson gson;

    public void init() {
        message = "Hello World!";

        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}