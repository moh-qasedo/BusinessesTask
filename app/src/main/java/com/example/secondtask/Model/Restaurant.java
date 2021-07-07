package com.example.secondtask.Model;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant {
    private String id;
    private String name;
    private String image_url;
    private String price;
    private float rating;
    private int review_count;

    @JsonProperty(value = "id")
    public String getId() {
        return id;
    }

    @JsonProperty(value = "id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty(value = "name")
    public String getName() {
        return name;
    }

    @JsonProperty(value = "name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty(value = "image_url")
    public String getImage_url() {
        return image_url;
    }

    @JsonProperty(value = "image_url")
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @JsonProperty(value = "price")
    public String getPrice() {
        return price;
    }

    @JsonProperty(value = "price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty(value = "rating")
    public float getRating() {
        return rating;
    }

    @JsonProperty(value = "rating")
    public void setRating(float rating) {
        this.rating = rating;
    }

    @JsonProperty(value = "review_count")
    public int getReview_count() {
        return review_count;
    }

    @JsonProperty(value = "review_count")
    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }

    @NonNull
    @Override
    public String toString() {
        return "{" + id + ", " + name + ", " + image_url + ", " + price + ", " + rating + ", " + review_count + "}";
    }
}
