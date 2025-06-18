package bxnd.sori.controller;

import bxnd.sori.dto.comment.CommentRequest;
import bxnd.sori.dto.comment.CommentResponse;
import bxnd.sori.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {


    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping("/add")
    @PostMapping
    public CommentResponse addComment(@Valid @RequestBody CommentRequest commentRequest) {
        return commentService.addComment(commentRequest);
    }
}
