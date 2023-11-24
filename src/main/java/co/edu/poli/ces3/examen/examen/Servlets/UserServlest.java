package co.edu.poli.ces3.examen.examen.Servlets;

import co.edu.poli.ces3.examen.examen.Controller.CtrUser;
import co.edu.poli.ces3.examen.examen.dto.DtoUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "userServlet", value = "/user")
public class UserServlest extends MyServlet {
    private String message;
    private GsonBuilder gsonBuilder;
    private Gson gson;
    private ArrayList<DtoUser> user = new ArrayList<>();

    public void init() {

        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        DtoUser user1 = new DtoUser();
        user1.id = 5;
        user1.setName("Edwin");
        user1.setMail("edwin@mail");
        user1.setPass("98765");

        DtoUser user2 = new DtoUser();
        user1.id = 11;
        user1.setName("Kaiser");
        user1.setMail("kaiser@mail");
        user1.setPass("12345");

        user.add(user1);
        user.add(user2);

        for (int i=0; i<user.size(); i++){

            System.out.println(user.get(i));
        }

        message = "student";
    }


    public UserServlest(){
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        CtrUser ctr = new CtrUser();

        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int userId = Integer.parseInt(id);
            DtoUser user = ctr.getUserById(userId);
            out.print(gson.toJson(user));
        } else {
            ArrayList<DtoUser> users = ctr.getAllUsers();
            out.print(gson.toJson(users));
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");

        JsonObject body = this.getParamsFromPost(req);
        CtrUser ctr = new CtrUser();
        DtoUser std = new DtoUser(
                body.get("mail").getAsString(),
                body.get("name").getAsString(),
                body.get("pass").getAsString()
        );

        DtoUser newUser = ctr.addUser(std);
        if (newUser != null){
            out.print(gson.toJson(newUser));
        }else {
            out.print("El usuario ya existe");
        }

        out.flush();
    }

    /*@Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        JsonObject body = this.getParamsFromPost(req);
        CtrUser ctr = new CtrUser();

        if (body != null){
            DtoUser students = new DtoUser(
                    body.get("id").getAsInt(),
                    body.get("documento").getAsString(),
                    body.get("nombre").getAsString()
            );
            DtoUser updateStudent = ctr.updateStudents(students);
            out.println(gson.toJson(updateStudent));
        }else {
            out.println(gson.toJson(student));
        }

    }*/

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        JsonObject body = this.getParamsFromPost(req);
        CtrUser ctr = new CtrUser();

        if (body != null){
            int delete = ctr.deleteUser(body.get("id").getAsInt());
            out.println(gson.toJson(delete));
            out.print(gson.toJson("Eliminado"));
        }
    }

    public void destroy() {
    }
}