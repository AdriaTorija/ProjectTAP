package PART1;
import java.time.LocalDateTime;

/**
 * Class for messages and its operations
 */

public class Message {
    private String subject;
    private String text;
    private String from;
    private String to;
    private LocalDateTime dateTime;

    /**
     * Method that creates a message by a given subject,text,nick from and nick to. It also sets the time when it was created.
     * @param subject
     * @param text
     * @param from
     * @param to
     */
    public Message(String subject, String text, String from, String to) {
        this.subject = subject;
        this.text = text;
        this.from = from;
        this.to = to;
        this.dateTime = LocalDateTime.now() ;
    }

    /**
     * Method that creates a message by a given subject,text,nick from, nick to, and the date when it was created.
     * @param subject
     * @param text
     * @param from
     * @param to
     * @param date
     */
    public Message(String subject, String text, String from, String to, LocalDateTime date) {
        this.subject = subject;
        this.text = text;
        this.from = from;
        this.to = to;
        this.dateTime = date;
    }

    /**
     * Method that returns the string of the content of the messages.
     * @return
     */
    @Override
    public String toString() {
        return  subject +
                ";" + text +
                ";" + from +
                ";" + to +
                ";" + dateTime;
    }

    //Getters & Setters
    public String getSubject() {
        return subject;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}

