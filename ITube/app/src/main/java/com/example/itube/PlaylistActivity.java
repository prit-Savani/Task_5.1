package com.example.itube;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.itube.db.AppDatabase;
import com.example.itube.db.PlaylistDao;
import com.example.itube.db.PlaylistItem;
import java.util.ArrayList;
import java.util.List;

public class PlaylistActivity extends AppCompatActivity {
    ListView lvPlaylist;
    AppDatabase db;
    PlaylistDao playlistDao;
    int userId;
    List<PlaylistItem> playlistItems = new ArrayList<>();
    List<String> videoUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ITube);
        setContentView(R.layout.activity_playlist);

        lvPlaylist = findViewById(R.id.lvPlaylist);
        db = AppDatabase.getInstance(this);
        playlistDao = db.playlistDao();
        userId = getIntent().getIntExtra("USER_ID", -1);

        new Thread(() -> {
            playlistItems = playlistDao.getPlaylist(userId);
            videoUrls.clear();
            for (PlaylistItem item : playlistItems) {
                videoUrls.add(item.videoUrl);
            }
            runOnUiThread(() -> {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, videoUrls);
                lvPlaylist.setAdapter(adapter);
            });
        }).start();

        lvPlaylist.setOnItemClickListener((AdapterView<?> parent, android.view.View view, int position, long id) -> {
            String url = videoUrls.get(position);
            Intent intent = new Intent(this, PlayerActivity.class);
            intent.putExtra("VIDEO_URL", url);
            startActivity(intent);
        });
    }
} 