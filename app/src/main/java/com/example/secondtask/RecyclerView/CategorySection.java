package com.example.secondtask.RecyclerView;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondtask.Model.Restaurant;
import com.example.secondtask.R;

import java.util.List;

public class CategorySection extends LinearLayout {

    private TextView mTitle;
    private RecyclerView mRecyclerView;
    private RestaurantsAdapter mAdapter;

    public CategorySection(Context context) {
        super(context);
        inflate(getContext(), R.layout.section_item, this);
        mTitle = (TextView) findViewById(R.id.section_title);
        mRecyclerView = (RecyclerView) findViewById(R.id.section_list);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    public CategorySection(Context context, String title, List<Restaurant> restaurants) {
        super(context);
        inflate(getContext(), R.layout.section_item, this);
        mTitle = (TextView) findViewById(R.id.section_title);
        mRecyclerView = (RecyclerView) findViewById(R.id.section_list);

        mTitle.setText(title);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new RestaurantsAdapter(getContext(), restaurants);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void notifyAdapterForDataSetChanges() {
        mAdapter.notifyDataSetChanged();
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setList(List<Restaurant> restaurants) {
        mAdapter = new RestaurantsAdapter(getContext(), restaurants);
        mRecyclerView.setAdapter(mAdapter);
    }
}
