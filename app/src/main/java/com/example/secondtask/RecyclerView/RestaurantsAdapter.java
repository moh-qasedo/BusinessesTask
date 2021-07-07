package com.example.secondtask.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.secondtask.Model.Restaurant;
import com.example.secondtask.R;

import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.ViewHolder> {

    private List<Restaurant> mRestaurantsData;
    private Context mContext;

    public RestaurantsAdapter(Context context, List<Restaurant> restaurantsData) {
        this.mRestaurantsData = restaurantsData;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RestaurantsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsAdapter.ViewHolder holder, int position) {
        Restaurant currentRestaurant = mRestaurantsData.get(position);
        holder.bindTo(currentRestaurant);
    }

    @Override
    public int getItemCount() {
        return mRestaurantsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mRestaurantImage;
        private TextView mName;
        private TextView mStars;
        private TextView mReviewCount;

        ViewHolder(View itemView) {
            super(itemView);
            mRestaurantImage = itemView.findViewById(R.id.restaurantImage);
            mName = itemView.findViewById(R.id.name);
            mStars = itemView.findViewById(R.id.stars);
            mReviewCount = itemView.findViewById(R.id.reviewCount);
        }

        void bindTo(Restaurant currentRestaurant) {
            mName.setText(currentRestaurant.getName());
            mStars.setText("Stars: " + currentRestaurant.getRating());
            mReviewCount.setText("Reviews: " + currentRestaurant.getReview_count());
            Glide.with(mContext).load(
                    currentRestaurant.getImage_url()).into(mRestaurantImage);
        }
    }
}