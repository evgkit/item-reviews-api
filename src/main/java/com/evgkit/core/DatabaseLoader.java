package com.evgkit.core;

import com.evgkit.item.Item;
import com.evgkit.item.ItemRepository;
import com.evgkit.review.Review;
import com.evgkit.user.User;
import com.evgkit.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
    private final ItemRepository items;
    private final UserRepository users;

    @Autowired
    public DatabaseLoader(ItemRepository items, UserRepository users) {
        this.items = items;
        this.users = users;
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
                "Java 9",
                "Spring HATEOAS"
        };
        List<User> userList = Arrays.asList(
                new User("jacobproffer", "Jacob", "Proffer", "password", new String[]{"ROLE_USER"}),
                new User("mlnorman", "Mike", "Norman", "password", new String[]{"ROLE_USER"}),
                new User("k_freemansmith", "Karen", "Freeman-Smith", "password", new String[]{"ROLE_USER"}),
                new User("seth_lk", "Seth", "Kroger", "password", new String[]{"ROLE_USER"}),
                new User("mrstreetgrid", "Java", "Vince", "password", new String[]{"ROLE_USER"}),
                new User("anthonymikhail", "Tony", "Mikhail", "password", new String[]{"ROLE_USER"}),
                new User("boog690", "AJ", "Teacher", "password", new String[]{"ROLE_USER"}),
                new User("faelor", "Erik", "Faelor Shafer", "password", new String[]{"ROLE_USER"}),
                new User("christophernowack", "Christopher", "Nowack", "password", new String[]{"ROLE_USER"}),
                new User("calebkleveter", "Caleb", "Kleveter", "password", new String[]{"ROLE_USER"}),
                new User("richdonellan", "Rich", "Donnellan", "password", new String[]{"ROLE_USER"}),
                new User("albertqerimi", "Albert", "Qerimi", "password", new String[]{"ROLE_USER"}),
                new User("dude", "Awesome", "Dude", "12345", new String[]{"ROLE_USER", "ROLE_ADMIN"})
        );
        users.saveAll(userList);

        List<Item> bunchOfItems = new ArrayList<>();
        IntStream.range(0, 100)
                .forEach(i -> {
                    String template = templates[i % templates.length];
                    String buzzword = buzzwords[i % buzzwords.length];
                    String title = String.format(template, buzzword);
                    Item item = new Item(title, "https://www.example.com");
                    Review review = new Review(i % 5, String.format("More %s please", buzzword));
                    review.setReviewer(userList.get(i % userList.size()));
                    item.addReview(review);
                    bunchOfItems.add(item);
                });

        items.saveAll(bunchOfItems);
    }
}
