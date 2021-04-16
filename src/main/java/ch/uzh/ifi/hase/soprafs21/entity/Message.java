package ch.uzh.ifi.hase.soprafs21.entity;

import ch.uzh.ifi.hase.soprafs21.helpers.SpringContext;
import ch.uzh.ifi.hase.soprafs21.service.UserService;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Internal Message representation
 * This class composes the internal representation of Messages and
 * defines how they are stored in the database.
 */

@Entity
@Table(name="MESSAGE")
public class Message implements Serializable, Comparable<Message> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column
    private Long messageId;

    @Column(nullable = false)
    private Long chatId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long timestamp;

    @Column(nullable = false)
    private String text;


    public Long getMessageId() {
        return messageId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long senderId) {
        this.userId = senderId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * gets the username of this Message's sender.
     * @return the sender's username if existent, null otherwise.
     */
    public String getUsername() {
        UserService userService = SpringContext.getBean(UserService.class);     // hacky steal the userService
        User user = userService.getUserByUserId(userId);
        return (user == null)? null : user.getUsername();
    }

    @Override
    public int compareTo(Message o) {
        return (int) (this.timestamp - o.timestamp);
    }
}
