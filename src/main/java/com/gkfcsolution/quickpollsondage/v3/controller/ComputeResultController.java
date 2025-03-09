package com.gkfcsolution.quickpollsondage.v2.controller;

import com.gkfcsolution.quickpollsondage.dto.OptionCount;
import com.gkfcsolution.quickpollsondage.dto.VoteResult;
import com.gkfcsolution.quickpollsondage.model.Vote;
import com.gkfcsolution.quickpollsondage.repository.VoteRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("computeResultControllerV2")
@RequestMapping("/v2/")
@Tag(name = "computeResult Controller")
public class ComputeResultController {

    @Autowired
    private VoteRepository voteRepository;

    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    private ResponseEntity<?> computerResult(@RequestParam Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);

        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>();
        for (Vote vote : allVotes) {
            totalVotes++;


            OptionCount optionCount = tempMap.computeIfAbsent(
                    vote.getOption().getId(), key -> {
                        OptionCount newOptionCount = new OptionCount();
                        newOptionCount.setOptionId(key);
                        return newOptionCount;
                    }
            );
            optionCount.setCount(optionCount.getCount() + 1);
        }

        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(tempMap.values());

        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }

}
