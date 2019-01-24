package com.example.dell.jaapactivity;

import java.net.URL;

public class JapData {
    int id;
    String type;
    Long time;
    boolean hasVideo;
    URL videoURl;

    public JapData(){

    }
    public JapData(int id, String type, Long time, boolean hasVideo, URL videoURl){
        this.id = id;
        this.type = type;
        this.time = time;
        this.hasVideo = hasVideo;
        this.videoURl = videoURl;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public boolean isHasVideo() {
        return hasVideo;
    }

    public void setHasVideo(boolean hasVideo) {
        this.hasVideo = hasVideo;
    }

    public URL getVideoURl() {
        return videoURl;
    }

    public void setVideoURl(URL videoURl) {
        this.videoURl = videoURl;
    }



}
