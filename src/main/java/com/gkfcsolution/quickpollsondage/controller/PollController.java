package com.gkfcsolution.quickpollsondage.controller;

import com.gkfcsolution.quickpollsondage.exception.ResourceNotFoundException;
import com.gkfcsolution.quickpollsondage.model.Poll;
import com.gkfcsolution.quickpollsondage.repository.PollRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@RestController
public class PollController {

    @Autowired
    private PollRepository pollRepository;

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        pollRepository.findById(pollId).orElseThrow( () -> new ResourceNotFoundException("Poll with id " + pollId + " not found"));
    }

    @RequestMapping(value = "/polls", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/polls", method = RequestMethod.POST)
    public ResponseEntity<Poll> createPoll(@Valid @RequestBody Poll poll) {
        Poll savedPoll = pollRepository.save(poll);

        URI newPollUriLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPoll.getId())
                .toUri();
        return  ResponseEntity.created(newPollUriLocation).body(savedPoll);
//        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.GET)
    @Operation(summary = "Obtenir un sondage par ID", description = "Retourne un sondage à partir de son identifiant.")
    public Poll getPoll(@PathVariable Long pollId) {
        //verifyPoll(pollId);
        return pollRepository.findById(pollId)
                .orElseThrow(() -> new ResourceNotFoundException("Poll not found with ID: " + pollId));
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.PUT)
    public ResponseEntity<Poll> updatePoll(@PathVariable Long pollId, @Valid @RequestBody Poll poll) {
        if (!pollRepository.existsById(pollId)) {
            throw new ResourceNotFoundException("Poll not found with ID: " + pollId);
        }
        poll.setId(pollId);
        Poll savedPoll = pollRepository.save(poll);
        return new ResponseEntity<>(savedPoll, HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        if (!pollRepository.existsById(pollId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Poll not found", "pollId", pollId));
        }
        pollRepository.deleteById(pollId);
        return ResponseEntity.noContent().build(); // 204 No Content pour une suppression réussie
//        return new ResponseEntity<>(HttpStatus.OK);
    }
}
