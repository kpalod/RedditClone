package com.example.redditClone.service;

import com.example.redditClone.dto.SubredditDto;
import com.example.redditClone.exceptions.SpringRedditException;
import com.example.redditClone.mapper.SubredditMapper;
import com.example.redditClone.model.Subreddit;
import com.example.redditClone.repository.SubredditRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j

public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;
    @Transactional
    public SubredditDto save(SubredditDto subredditDto){
        Subreddit save = subredditRepository.save(subredditMapper.mapDtotoSubreddit(subredditDto));
        subredditDto.setId(save.getId());
        return subredditDto;
    }
    @Transactional
    public List<SubredditDto> getAll(){
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(Collectors.toList());
    }
    public SubredditDto getSubreddit(Long id){
        Subreddit subreddit = subredditRepository.findById(id).orElseThrow(() -> new SpringRedditException("No subreddit found"));
        return subredditMapper.mapSubredditToDto(subreddit);
    }




}
