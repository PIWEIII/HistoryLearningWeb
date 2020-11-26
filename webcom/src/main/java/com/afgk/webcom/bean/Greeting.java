package com.afgk.webcom.bean;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Piweiii
 * @Date: 2020/11/10/15:56
 * @Description:问候 实例
 */
public class Greeting {
    private long id;
    private String content;

    public Greeting(){

    }

    public Greeting(long new_id,String new_content){
        this.id = new_id;
        this.content = new_content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
