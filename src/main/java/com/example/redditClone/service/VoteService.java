package com.example.redditClone.service;

import com.example.redditClone.dto.VoteDto;
import com.example.redditClone.exceptions.PostNotFoundException;
import com.example.redditClone.exceptions.SpringRedditException;
import com.example.redditClone.model.Post;
import com.example.redditClone.model.Vote;
import com.example.redditClone.repository.PostRepository;
import com.example.redditClone.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.redditClone.model.VoteType.UPVOTE;
@Service
@AllArgsConstructor


public class VoteService {
    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostID())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostID()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already "
                    + voteDto.getVoteType() + "'d for this post");
        }
        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
