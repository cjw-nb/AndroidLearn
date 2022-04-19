package com.example.uipractice;

/**
 * @program: Msg
 * @description
 * @author: Cao Jingwei
 * @create: 2022-04-19 17:25
 **/
public class Msg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;
    private String content;
    private int type;

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
