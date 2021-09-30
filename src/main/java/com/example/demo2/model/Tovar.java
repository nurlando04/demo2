package com.example.demo2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class Tovar {

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setPresence(Boolean presence) {
        this.presence = presence;
    }

    private UUID id;
    private String name;
    private int weight;
    private int len;
    private int width;
    private int[] shape={0,0,0};
    private int height;
    private Boolean presence;



    private String pathToImage;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() { return weight; }

    public int[] getShape() {
        return shape;//new int[]{Character.getNumericValue(shape.charAt(0)),Character.getNumericValue(shape.charAt(1)),Character.getNumericValue(shape.charAt(2))};
    }

    public String shapeString(){
        String str= Arrays.toString(shape);
        return str;
    }

    public Boolean isPresence() {
        return presence;
    }

    public String getPathToImage() {
        return pathToImage;
    }




    public Tovar(@JsonProperty(value = "id") UUID id,
                   @JsonProperty(value = "name") String name,
                   @JsonProperty(value = "weight") int weight,
                   @JsonProperty(value = "shape") int[] shape,
                   @JsonProperty("presence") Boolean presence,
                   @JsonProperty("pathtoimage") String pathToImage) {
        this.id=id;
        if (name==null) throw new IllegalStateException("Name must be filled");
        this.name=name;
        this.weight = weight;
        if (shape!=null) {
            this.shape = shape;
            this.len = shape[0];
            this.width = shape[1];
            this.height = shape[2];
        }
        else{
            this.shape = new int[]{0,0,0};
            this.len = 0;
            this.width = 0;
            this.height = 0;
        }
        this.presence = presence;
        this.pathToImage = Objects.requireNonNullElse(pathToImage, "images/defaultdog.jpg");
    }

/*    public Tovar(@JsonProperty("id") UUID id,
                 @JsonProperty("name") String name,
                 @JsonProperty("weight") int weight,
                 @JsonProperty("presence") Boolean presence,
                 @JsonProperty("pathtoimage") String pathToImage) {
        new Tovar(id,name,weight,new int[]{0, 0, 0},presence,pathToImage);
    }
    public Tovar(@JsonProperty("id") UUID id,
                 @JsonProperty("name") String name,
                 @JsonProperty("weight") int weight,
                 @JsonProperty("shape") int[] shape,
                 @JsonProperty("presence") Boolean presence) {
        new Tovar(id,name,weight,shape,presence,"images/defaultdog.jpg");
    }*/
}
