package servlets;

import dto.User;
import services.EmailService;
import utils.Freemarker;
import utils.ParameterFromRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RegistrationServlet extends HttpServlet {
    private final Freemarker f = new Freemarker();
    private EmailService emailService;

    public RegistrationServlet(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        List<String> fields = new ArrayList<>();

        fields.add("Name");
        fields.add("Surname");
        fields.add("Image");
        fields.add("Email");

        data.put("fields", fields);
        data.put("message", "Please sign up");
        data.put("rout", "/reg");
        f.render("form.ftl", data, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);

        String name = pfr.getStr("Name");
        String surname = pfr.getStr("Surname");
        String image = pfr.getStr("Image");
        String login = pfr.getStr("Email");
        String password = pfr.getStr("Password");

        User user = new User(login,password,name,surname,image);
        emailService.sendEmail(user);
        HashMap<String, Object> data = new HashMap<>();
        data.put("message","Check your email to confirm registration");
        data.put("rout","/reg");
        f.render("msg.ftl", data, resp);
    }
}
