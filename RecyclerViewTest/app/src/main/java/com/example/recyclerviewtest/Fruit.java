package com.example.recyclerviewtest;

/**
 * @program: Fruit
 * @description
 * @author: Cao Jingwei
 * @create: 2022-04-19 14:35
 **/
public class Fruit {
    private String name;
    private int imageId;

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }
    public int getImageId() {
        return imageId;
    }

}
