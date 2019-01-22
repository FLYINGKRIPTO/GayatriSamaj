package com.example.dell.jaapactivity;

public class YouTubeVideos {
    String videoUrl;
    Long videoLength;
    public YouTubeVideos(){

    }
    public YouTubeVideos(String videoUrl,Long videoLength) {
        this.videoUrl = videoUrl;
        this.videoLength = videoLength;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
    public Long getVideoLength() {
        return videoLength;
    }
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
