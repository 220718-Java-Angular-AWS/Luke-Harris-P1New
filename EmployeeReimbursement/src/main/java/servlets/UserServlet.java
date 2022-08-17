package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.User;
import com.revature.services.UserService;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;


public class UserServlet extends HttpServlet {
    UserService service;
    ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        System.out.println("User servlet initializing...");
        this.mapper = new ObjectMapper();
        this.service = new UserService();
        System.out.println("User servlet initialized!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("user_id");
        String email = req.getParameter("email");
        String password = req.getParameter("userPass");

        if(id == null && email == null && password == null) {
            List<User> userList = service.getAllUsers();
            String json = mapper.writeValueAsString(userList);
            resp.getWriter().println(json);
        }
        else if (id != null) {
            Integer userId = Integer.parseInt(req.getParameter("user_id"));

            User user = service.getUser(userId);
            String json = mapper.writeValueAsString(user);
            resp.getWriter().println(json);
        } else {
            User user = service.logIN(email, password);
            String json = mapper.writeValueAsString(user);
            resp.getWriter().println(json);
        }


        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("working");
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        System.out.println("big buffed");
        while(buffer.ready()) {
            builder.append(buffer.readLine());
        }
        System.out.println("whiled");
        String json = builder.toString();

        User newUser = mapper.readValue(json, User.class);
        System.out.println("mapped");
        service.saveUser(newUser);

        System.out.println("user created");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while(buffer.ready()) {
            builder.append(buffer.readLine());
        }
        String json = builder.toString();

        User upatedUser = mapper.readValue(json, User.class);
        service.updateUser(upatedUser);

        System.out.println("User Updated");

    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("userId");
        if(param == null) {
            resp.getWriter().println("User ID not found ");
        } else {
            Integer userId = Integer.parseInt(req.getParameter("userId"));
            service.deleteUser(userId);
            resp.getWriter().println("User Deleted");
        }

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }
}
