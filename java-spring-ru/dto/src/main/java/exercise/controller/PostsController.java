package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

@RestController
@RequestMapping(path = "/posts")
public class PostsController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<PostDTO> getPostsAll() {
        return postRepository.findAll().stream().map(p -> {
            PostDTO postDTO = new PostDTO();
            postDTO.setId(p.getId());
            postDTO.setTitle(p.getTitle());
            postDTO.setBody(p.getBody());
            postDTO.setComments(commentRepository.findByPostId(p.getId()).stream().map(c -> {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setId(c.getId());
                commentDTO.setBody(c.getBody());
                return commentDTO;
            }).toList());
            return postDTO;
        }).toList();
    }

    @GetMapping(path = "/{id}")
    public PostDTO getPostById(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setBody(post.getBody());
        postDTO.setComments(commentRepository.findByPostId(post.getId()).stream().map(c -> {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(c.getId());
            commentDTO.setBody(c.getBody());
            return commentDTO;
        }).toList());
        return postDTO;
    }

}
