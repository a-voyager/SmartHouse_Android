package top.wuhaojie.smarthouse.entities;

/**
 * Created by wuhaojie on 2016/7/7 21:32.
 */
public class MessageEntity {

    private int id;
    private String text;

    public MessageEntity(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new MessageEntity(id, text);
    }
}
