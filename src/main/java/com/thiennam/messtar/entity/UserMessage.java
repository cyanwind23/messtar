package com.thiennam.messtar.entity;

import javax.persistence.*;

@Table(name = "MESSTAR_USER_MESSAGE")
@Entity(name = "messtar_UserMessage")
public class UserMessage extends StandardEntity {
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "MESSAGE_ID", nullable = false)
    private Message message;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REPLY_TO")
    private Message replyTo;

    @Column(name = "STATUS")
    private String status;

    public UserMessageStatusEnum getStatus() {
        return UserMessageStatusEnum.fromId(status);
    }

    public void setStatus(UserMessageStatusEnum status) {
        this.status = status == null ? null : status.getId();
    }

    public Message getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Message replyTo) {
        this.replyTo = replyTo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}