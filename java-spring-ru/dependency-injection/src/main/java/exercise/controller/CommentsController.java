package exercise.controller;

import exercise.model.Comment;
import exercise.model.Post;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment get(@PathVariable Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment update(@PathVariable Long id, @RequestBody Comment comment) {
        Comment existedComment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment with id "+id+" not found"));
        existedComment.setBody(comment.getBody());
        return commentRepository.save(existedComment);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        if (commentRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Comment with id " + id + " not found");
        }
        commentRepository.deleteById(id);
    }
}
