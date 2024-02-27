package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/posts")
    public List<Post> getPosts(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit) {
        return posts.stream().skip(page).limit(limit).toList();
    }
    
    @GetMapping("/posts/{id}")
    public Optional<Post> getPost(@PathVariable String id) {
        return posts.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @PostMapping ("/posts")
    public Post postPosts(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/posts/{id}")
    public Post putPosts(@PathVariable String id, @RequestBody Post post) {
        Optional<Post> existedPostOptional = posts.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (existedPostOptional.isPresent()) {
            Post existedPost = existedPostOptional.get();
            existedPost.setBody(post.getBody());
            existedPost.setTitle(post.getTitle());
        }
        return post;
    }

    @DeleteMapping("/posts/{id}")
    public void deletePosts(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
