package com.example.itube;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class PlayerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ITube);
        setContentView(R.layout.activity_player);

        String videoUrl = getIntent().getStringExtra("VIDEO_URL");
        String videoId = extractVideoIdFromUrl(videoUrl);

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
    }

    private String extractVideoIdFromUrl(String url) {
        if (url == null) return "";
        // Handle standard YouTube URLs
        String pattern = "v=";
        int index = url.indexOf(pattern);
        if (index != -1) {
            String id = url.substring(index + 2);
            int ampIndex = id.indexOf('&');
            if (ampIndex != -1) {
                id = id.substring(0, ampIndex);
            }
            return id;
        }
        // Handle youtu.be short links
        if (url.contains("youtu.be/")) {
            int start = url.indexOf("youtu.be/") + 9;
            int end = url.indexOf('?', start);
            if (end == -1) end = url.length();
            return url.substring(start, end);
        }
        // If already a video ID
        if (url.length() == 11 && url.matches("[a-zA-Z0-9_-]{11}")) {
            return url;
        }
        return "";
    }
} 