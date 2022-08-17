package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.Requests;
import com.revature.pojos.User;
import com.revature.services.RequestsService;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class RequestsServlet extends HttpServlet {
    private RequestsService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        System.out.println("Task servlet initializing...");
        this.service = new RequestsService();
        this.mapper = new ObjectMapper();
        System.out.println("Task servlet initializing");
    }
    @Override
    public void destroy() {


    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("requestId");
        System.out.println("here");

        if(param == null) {
            List<Requests> userList = service.getAllRequests();
            String json = mapper.writeValueAsString(userList);
            resp.getWriter().println(json);
        } else {
            Integer requestId = Integer.parseInt(req.getParameter("requestsId"));
            Requests requests = service.getRequests(requestId);
            String json = mapper.writeValueAsString(requests);
            resp.getWriter().println(json);
        }
        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while(buffer.ready()) {
            builder.append(buffer.readLine());
        }
        String json = builder.toString();
        Requests newRequests = mapper.readValue(json, Requests.class);
        service.saveRequests(newRequests);

    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("requestsId");
        if(param == null) {
            resp.getWriter().println("Request ID not found");
        } else {
            Integer requestsId = Integer.parseInt(req.getParameter("requestsId"));
            service.deleteRequests(requestsId);
            resp.getWriter().println("Request deleted");
        }
        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while(buffer.ready()) {
            builder.append(buffer.readLine());
        }
        String json = builder.toString();

        Requests updatedRequests = mapper.readValue(json, Requests.class);
        service.updateRequests(updatedRequests);
    }
}

