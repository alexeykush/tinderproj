package dto;

import java.util.Timer;

public class UserInRegisterQueue {
    private User user;
    private Timer timer;

    public UserInRegisterQueue(User user, Timer timer) {
        this.user = user;
        this.timer = timer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
