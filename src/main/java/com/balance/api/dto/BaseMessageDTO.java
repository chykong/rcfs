package com.balance.api.dto;

/**
 * @autho 孔垂云
 * @date 2017/6/23.
 */
public class BaseMessageDTO {

    private int id;//消息id
    private String title;//标题
    private String content;//内容
    private int messageStatus;//状态

    @Override
    public String toString() {
        return "BaseMessageDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", messageStatus=" + messageStatus +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(int messageStatus) {
        this.messageStatus = messageStatus;
    }
}
