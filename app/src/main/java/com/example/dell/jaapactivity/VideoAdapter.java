package com.example.dell.jaapactivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    List<YouTubeVideos> youTubeVideoList;
    TextView timerTextView;
    public  VideoAdapter(){

    }
    public VideoAdapter(List<YouTubeVideos> youTubeVideosList){
        this.youTubeVideoList= youTubeVideosList;
    }
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( viewGroup.getContext()).inflate(R.layout.video_view,viewGroup, false);

        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.videoWeb.setVideoPath(youTubeVideoList.get(position).getVideoUrl());
        //holder.videoWeb.loadData(youTubeVideoList.get(position).getVideoUrl(), "text/html" , "utf-8" );
      //  holder.videoWeb.loadData(youTubeVideoList.get(position).getVideoLength().toString(),"long","utf-8");
    }

    @Override
    public int getItemCount() {
        return youTubeVideoList.size();
    }
    public class VideoViewHolder extends RecyclerView.ViewHolder {

        VideoView videoWeb;


        public VideoViewHolder(View itemView) {
            super(itemView);



        }

    };}