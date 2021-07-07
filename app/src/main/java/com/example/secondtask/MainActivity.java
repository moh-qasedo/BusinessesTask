package com.example.secondtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.secondtask.Api.YeldApi;
import com.example.secondtask.Model.Restaurant;
import com.example.secondtask.Model.YeldResponse;
import com.example.secondtask.RecyclerView.CategorySection;
import com.example.secondtask.RecyclerView.RestaurantsAdapter;
import com.example.secondtask.Retrofit.RetrofitClient;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "FTUTIFLBvlsMwbNXgY0dh5t3C1suIuGPtvHSohflpMUKrvaAVpNILKSvvaXb_nbWF6NiTxhXPEyNYGt5yBq2x6bBWCqnlJp_O2L0B9kKXTGDOcCcBFXoJbMr7yjcYHYx";

    private YeldApi yeldApi;

    private List<Restaurant> mCostEffective;
    private List<Restaurant> mBitPriceir;
    private List<Restaurant> mBigSpender;

    private LinearLayout mLinearLayout;
    private ScrollView mScrollView;
    private CircularProgressIndicator mIndicator;

    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCostEffective = new ArrayList<>();
        mBitPriceir = new ArrayList<>();
        mBigSpender = new ArrayList<>();

        mLinearLayout = findViewById(R.id.linear_layout);
        mScrollView = findViewById(R.id.scrollView);
        mIndicator = findViewById(R.id.indicator);

        Retrofit retrofit = RetrofitClient.getInstance();
        yeldApi = retrofit.create(YeldApi.class);
        fetchDataFromApi();
    }

    private void fetchDataFromApi() {
        disposables.add(yeldApi.getRestaurants("Bearer " + API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<YeldResponse, List<Restaurant>>() {
                    @Override
                    public List<Restaurant> apply(@NonNull YeldResponse yeldResponse) throws Exception {
                        return yeldResponse.getBusinesses();
                    }
                })
                .subscribe(new Consumer<List<Restaurant>>() {
                    @Override
                    public void accept(List<Restaurant> restaurants) throws Exception {
                        divideToCategories(restaurants);
                    }
                }));
    }

    private void divideToCategories(List<Restaurant> restaurants) {
        String currentPrice;
        Restaurant currentRestaurant;
        for (int i = 0; i < restaurants.size(); i++) {
            currentRestaurant = restaurants.get(i);
            currentPrice = currentRestaurant.getPrice();
            switch (currentPrice) {
                case "$":
                    mCostEffective.add(currentRestaurant);
                    break;
                case "$$":
                    mBitPriceir.add(currentRestaurant);
                    break;
                case "$$$":
                    mBigSpender.add(currentRestaurant);
                    break;
                default:
                    break;
            }
        }
        mLinearLayout.addView(new CategorySection(this, "Cost Effective", mCostEffective));
        mLinearLayout.addView(new CategorySection(this, "Bit Pricier", mBitPriceir));
        mLinearLayout.addView(new CategorySection(this, "Big Spender", mBigSpender));
        mIndicator.setVisibility(View.GONE);
        mScrollView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }

}