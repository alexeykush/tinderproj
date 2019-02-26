package dto;

import java.util.Date;

public class Message implements Identifiable {
    private int id;
    private int senderId;
    private int receiverId;
    private java.lang.String text;
    private Date time;
    private String status;

    public Message(int id, int senderId, int receiverId, String text, Date time) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
        this.time = time;
    }

    public Message(int id, int senderId, int receiverId, String text, String status, Date time) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
        this.status = status;
        this.time = time;
    }

    public Message(int senderId, int receiverId, String text) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public java.lang.String toString() {
        return "Message{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", text='" + text + '\'' +
                ", time=" + time +
                ", status=" + status +
                '}';
    }
}
