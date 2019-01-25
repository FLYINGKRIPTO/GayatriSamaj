package com.example.dell.jaapactivity.Swadhaya;

public class SwadhyayData {
    int id;
    Long time;

    public SwadhyayData(){

    }
    public SwadhyayData(int id,Long time){
        this.id = id;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
