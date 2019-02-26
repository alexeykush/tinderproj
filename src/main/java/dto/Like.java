package dto;

import java.util.List;

public class Like {
    private int userId;
    private int likedUserId;

    public Like(int likedUserId) {
        this.likedUserId = likedUserId;
    }

    public Like(int userId, int likedUserId) {
        this.userId = userId;
        this.likedUserId = likedUserId;
    }

    @Override
    public String toString() {
        return "Like{" +
                "userId=" + userId +
                ", likedUserId=" + likedUserId +
                '}';
    }

    public int getLikedUserId() {
        return likedUserId;
    }

    public void setLikedUserId(int likedUserId) {
        this.likedUserId = likedUserId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
