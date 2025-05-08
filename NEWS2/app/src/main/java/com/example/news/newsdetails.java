package com.example.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bumptech.glide.Glide;
import com.example.news.databinding.FragmentNewsDetailBinding;
import java.util.ArrayList;
import java.util.List;

public class newsdetails extends Fragment implements NewsAdapter.OnItemClickListener {

    private FragmentNewsDetailBinding binding;

    private NewsAdapter relatedNewsAdapter;
    private Item_news currentNewsItem;

    private static final String ARG_NEWS_ITEM = "news_item";

    public static newsdetails newInstance(Item_news newsItem) {
        newsdetails fragment = new newsdetails();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NEWS_ITEM, newsItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentNewsItem = getArguments().getParcelable(ARG_NEWS_ITEM);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentNewsDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (currentNewsItem != null) {
            binding.textTitle.setText(currentNewsItem.getTitle());
            binding.textDescription.setText(currentNewsItem.getDescription());
            
            if (currentNewsItem.hasLocalImage()) {
                binding.imageNews.setImageResource(currentNewsItem.getImageResourceId());
            } else {
                Glide.with(requireContext())
                        .load(currentNewsItem.getImageUrl())
                        .centerCrop()
                        .into(binding.imageNews);
            }

            relatedNewsAdapter = new NewsAdapter(getRelatedNews(), this);
            binding.recyclerRelatedNews.setLayoutManager(new LinearLayoutManager(requireContext()));
            binding.recyclerRelatedNews.setAdapter(relatedNewsAdapter);
        }
    }

    private List<Item_news> getRelatedNews() {
        List<Item_news> list = new ArrayList<>();
        list.add(new Item_news("Breaking News Update", 
            "Latest developments on major stories.", 
            R.drawable.img6));
        list.add(new Item_news("Live Studio Coverage", 
            "Continuous updates from our news desk.", 
            R.drawable.img2));
        list.add(new Item_news("International Developments", 
            "Key updates from the United Nations.", 
            R.drawable.download));
        return list;
    }

    @Override
    public void onItemClick(Item_news newsItem) {
        if (newsItem != null) {
            newsdetails fragment = newsdetails.newInstance(newsItem);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
