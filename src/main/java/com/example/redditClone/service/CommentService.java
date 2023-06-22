package com.example.redditClone.service;

import com.example.redditClone.dto.CommentsDto;
import com.example.redditClone.exceptions.PostNotFoundException;
import com.example.redditClone.exceptions.SpringRedditException;
import com.example.redditClone.mapper.CommentMapper;
import com.example.redditClone.model.Comment;
import com.example.redditClone.model.NotificationEmail;
import com.example.redditClone.model.Post;
import com.example.redditClone.model.User;
import com.example.redditClone.repository.CommentRepository;
import com.example.redditClone.repository.PostRepository;
import com.example.redditClone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class CommentService {
    private static final String POST_URL = "";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailService mailService;
    private final MailContentBuilder mailContentBuilder;
    public void save(CommentsDto commentsDto){
    Post post =    postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
    User user = authService.getCurrentUser();
    Comment  comment = commentMapper.map(commentsDto, post, user);
    commentRepository.save(comment);
        String message = mailContentBuilder.build(user.getUsername() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());

    }
    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(authService.getCurrentUser().getUsername() + " Commented on your post", user.getEmail(), message));
    }
    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }
    public List<CommentsDto> getAllCommentsForUser(String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException(username+" not found"));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

}
