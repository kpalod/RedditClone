package com.example.redditClone.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.time.Instant;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String postName;
    @Nullable
    private String url;
    @Nullable
    @Lob
    private String description;
    private Integer voteCount = 0;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    private Instant createdDate;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Subreddit subreddit;
}
