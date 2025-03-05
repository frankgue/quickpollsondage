package com.gkfcsolution.quickpollsondage.controller;

import com.gkfcsolution.quickpollsondage.model.Vote;
import com.gkfcsolution.quickpollsondage.repository.VoteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class VoteController {

    @Autowired
    private VoteRepository voteRepository;


    @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.POST)
    public ResponseEntity<?> createVote(@PathVariable("pollId") Long pollId, @Valid @RequestBody Vote vote) {

        Vote newVote = voteRepository.save(vote);

        URI newVoteUriLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(vote.getId())
                .toUri();
        return ResponseEntity.created(newVoteUriLocation).body(newVote);
    }

    @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.GET)
    public Iterable<Vote> getAllVotes(@PathVariable("pollId") Long pollId) {
        return voteRepository.findByPoll(pollId);
    }

}
