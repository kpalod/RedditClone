package com.example.redditClone.dto;

import com.example.redditClone.model.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class VoteDto {
    private VoteType voteType;
    private Long postID;

}
