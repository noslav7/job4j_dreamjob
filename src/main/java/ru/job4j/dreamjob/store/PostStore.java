package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Post;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {
    private static final PostStore INST = new PostStore();
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private static AtomicInteger store = new AtomicInteger(2);

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "Boring job", new Date()));
        posts.put(2, new Post(2, "Middle Java Job", "Nice job", new Date()));
        posts.put(3, new Post(3, "Senior Java Job", "Really serious job", new Date()));
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {
        post.setId(store.incrementAndGet());
        posts.put(post.getId(), post);
    }

    public void create(Post post) {
    new Post(post.getId(), post.getName(), post.getDescription(), post.getCreated());
    }

    public void update(Post post) {
    posts.replace(post.getId(), post);
    }
}
