package services;

import dao.DaoUsersSql;
import dto.User;

public class UsersService {
    private DaoUsersSql userDao;

    public UsersService(DaoUsersSql userDao) {
        this.userDao = userDao;
    }

    public int getUserId(User user){
        return userDao.getByLogin(user).getId();
    }

    public boolean checkUserByLogin(User user){
        return userDao.getByLogin(user) != null;
    }

    public boolean checkUser(User user){
        User res = userDao.getByLogin(user);
        return res != null && res.getPassword().equals(user.getPassword());
    }

    public void add(User item) {
        userDao.add(item);
    }

}
