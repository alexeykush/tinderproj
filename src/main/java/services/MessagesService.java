package services;

import dao.Dao;
import dao.DaoLikesSql;
import dao.DaoMessagesSql;
import dao.DaoUsersSql;
import dto.Like;
import dto.Message;
import dto.User;
import utils.Freemarker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MessagesService {
    private final int userId;
    private final int counterpartId;
    private Connection connection;
    private Dao<Message> messageDao;
    private Dao<User> userDao;
    private Dao<Like> likeDao;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Freemarker freemarker = new Freemarker();

    public MessagesService(int userId, int counterpartId, Connection connection, HttpServletRequest request, HttpServletResponse response) {
        this.userId = userId;
        this.counterpartId = counterpartId;
        this.connection = connection;
        this.messageDao = new DaoMessagesSql(connection, userId);
        this.userDao = new DaoUsersSql(connection);
        this.likeDao = new DaoLikesSql(userId, connection);
        this.request = request;
        this.response = response;
    }

    public void sendMessage(String text) {
        messageDao.add(new Message(userId, counterpartId, text));
    }


    public void generateLikedPage() {
        Map<String, Object> input = new HashMap<>();
        input.put("messages", 1);
        input.put("counterpart", userDao.get(counterpartId));
        input.put("messageList", getFilteredMessages());
        input.put("users", getLikedUsersList(likeDao.getAll()));
        freemarker.render("people-list.ftl", input, response);
    }


    private List<Message> getFilteredMessages() {
        return messageDao.getAll().stream().filter(e -> e.getSenderId() == counterpartId || e.getReceiverId() == counterpartId).collect(Collectors.toList());
    }

    private List<User> getLikedUsersList(List<Like> likes) {
        return likes.stream().map(e -> userDao.get(e.getLikedUserId())).collect(Collectors.toList());
    }
}
