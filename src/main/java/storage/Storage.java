package storage;

import dto.User;
import dto.UserInRegisterQueue;

import java.util.*;

public class Storage {
    private HashMap<String, UserInRegisterQueue> uids = new HashMap<>();
    private static final int MINUTES_TO_CONFIRM_EMAIL = 10;

    public void put(String uid, User user){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                uids.remove(uid);
            }
        };
        timer.schedule(task, MINUTES_TO_CONFIRM_EMAIL * 60 * 1000);
        UserInRegisterQueue userInRegisterQueue = new UserInRegisterQueue(user,timer);
        uids.put(uid,userInRegisterQueue);
    }

    public void remove(String uid){
        Timer timer = uids.get(uid).getTimer();
        timer.cancel();
        timer.purge();
        uids.remove(uid);
    }

    public UserInRegisterQueue get(String uid){
        return uids.get(uid);
    }


}
