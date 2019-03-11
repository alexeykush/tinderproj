package servlets;

import dao.DaoUsersSql;
import dto.User;
import dto.UserInRegisterQueue;
import services.CookiesService;
import services.UsersService;
import storage.Storage;
import utils.Freemarker;
import utils.ParameterFromRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

public class VerificationServlet extends HttpServlet {
    private CookiesService cookiesService;
    private UsersService usersService;
    private final Connection connection;
    private final Storage storage;
    private final Freemarker f = new Freemarker();

    public VerificationServlet(Connection connection,Storage storage){
        this.connection = connection;
        this.usersService = new UsersService(new DaoUsersSql(connection));
        this.storage =  storage;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        cookiesService = new CookiesService(req,resp);

        String userUid = pfr.getStr("user");
        UserInRegisterQueue userInRegisterQueue = storage.get(userUid);
        if(userInRegisterQueue != null){
            User user = userInRegisterQueue.getUser();
            usersService.add(user);
            cookiesService.addCookie(usersService.getUserId(user));
            storage.remove(userUid);
            resp.sendRedirect("/users");
        } else {
            HashMap<String, Object> data = new HashMap<>();
            data.put("message","Your link isn't available.Try once more");
            data.put("rout","/reg");
            f.render("msg.ftl", data, resp);
        }

    }
}
