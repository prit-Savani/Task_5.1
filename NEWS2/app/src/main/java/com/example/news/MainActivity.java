package com.example.news;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.news.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsAdapter.OnItemClickListener{

    private ActivityMainBinding binding;
    private NewsAdapter topStoriesAdapter;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Setup top stories recycler view
        topStoriesAdapter = new NewsAdapter(getTopStories(), this);
        binding.recyclerTopStories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerTopStories.setAdapter(topStoriesAdapter);

        // Setup news recycler view
        newsAdapter = new NewsAdapter(getNews(), this);
        binding.recyclerNews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerNews.setAdapter(newsAdapter);

    }

    private List<Item_news> getTopStories() {
        List<Item_news> list = new ArrayList<>();
        list.add(new Item_news("Traditional Ceremony Marks Climate Summit", 
            "Indigenous leaders and officials participate in a traditional smoking ceremony at the climate summit.", 
            R.drawable.images));
        list.add(new Item_news("Live: Global News Coverage", 
            "Professional news anchor delivers the latest updates from our state-of-the-art studio.", 
            R.drawable.img2));
        list.add(new Item_news("Breaking News", 
            "Stay tuned for the latest updates on developing stories.", 
            R.drawable.img3));
        return list;
    }

    private List<Item_news> getNews() {
        List<Item_news> list = new ArrayList<>();
        list.add(new Item_news("Crisis in Conflict Zone", 
            "Civilians affected by ongoing conflict receive emergency assistance.", 
            R.drawable.img4));
        list.add(new Item_news("Unity After Tragedy", 
            "Community comes together in solidarity following tragic events.", 
            R.drawable.img5));
        list.add(new Item_news("UN General Assembly", 
            "Prime Minister addresses key issues at the United Nations General Assembly.", 
            R.drawable.download));
        return list;
    }

    @Override
    public void onItemClick(Item_news newsItem) {
        newsdetails fragment = newsdetails.newInstance(newsItem);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(binding.fragmentContainer.getId(), fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}