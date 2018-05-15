package com.evgkit.core;

import com.evgkit.item.Item;
import com.evgkit.item.ItemRepository;
import com.evgkit.review.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
    private final ItemRepository items;

    @Autowired
    public DatabaseLoader(ItemRepository items) {
        this.items = items;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String[] templates = {
                "Up and Running with %s",
                "%s for Neckbeards",
                "Under the hood: %s"
        };
        String[] buzzwords = {
                "Scala",
                "Groovy",
                "Hibernate",
                "Java 9"
        };

        List<Item> bunchOfItems = new ArrayList<>();
        IntStream.range(0, 100)
                .forEach(i -> {
                    String template = templates[i % templates.length];
                    String buzzword = buzzwords[i % buzzwords.length];
                    String title = String.format(template, buzzword);
                    Item item = new Item(title, "https://www.example.com");
                    item.addReview(new Review(i % 5, String.format("More %s please", buzzword)));
                    bunchOfItems.add(item);
                });

        items.saveAll(bunchOfItems);
    }
}
