package com.example.itube;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.itube.db.AppDatabase;
import com.example.itube.db.PlaylistItem;
import com.example.itube.db.PlaylistDao;

public class HomeActivity extends AppCompatActivity {
    EditText etYoutubeUrl;
    Button btnPlay, btnAddToPlaylist, btnViewPlaylist;
    AppDatabase db;
    PlaylistDao playlistDao;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ITube);
        setContentView(R.layout.activity_home);

        etYoutubeUrl = findViewById(R.id.etYoutubeUrl);
        btnPlay = findViewById(R.id.btnPlay);
        btnAddToPlaylist = findViewById(R.id.btnAddToPlaylist);
        btnViewPlaylist = findViewById(R.id.btnViewPlaylist);

        db = AppDatabase.getInstance(this);
        playlistDao = db.playlistDao();
        userId = getIntent().getIntExtra("USER_ID", -1);

        btnPlay.setOnClickListener(v -> {
            String url = etYoutubeUrl.getText().toString();
            if (url.isEmpty()) {
                Toast.makeText(this, "Enter a YouTube URL", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this, PlayerActivity.class);
            intent.putExtra("VIDEO_URL", url);
            startActivity(intent);
        });

        btnAddToPlaylist.setOnClickListener(v -> {
            String url = etYoutubeUrl.getText().toString();
            if (url.isEmpty()) {
                Toast.makeText(this, "Enter a YouTube URL", Toast.LENGTH_SHORT).show();
                return;
            }
            new Thread(() -> {
                PlaylistItem item = new PlaylistItem();
                item.userId = userId;
                item.videoUrl = url;
                playlistDao.insert(item);
                runOnUiThread(() -> Toast.makeText(this, "Added to playlist", Toast.LENGTH_SHORT).show());
            }).start();
        });

        btnViewPlaylist.setOnClickListener(v -> {
            Intent intent = new Intent(this, PlaylistActivity.class);
            intent.putExtra("USER_ID", userId);
            startActivity(intent);
        });
    }
} 