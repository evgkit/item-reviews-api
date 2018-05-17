package com.evgkit.item;

import com.evgkit.core.BaseEntity;
import com.evgkit.review.Review;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item extends BaseEntity {
    @NotNull
    @Size(min = 1, max = 140)
    private String title;
    private String url;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Review> reviews;

    protected Item() {
        super();
        reviews = new ArrayList<>();
    }

    public Item(String title, String url) {
        this();
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        review.setItem(this);
        reviews.add(review);
    }
}
