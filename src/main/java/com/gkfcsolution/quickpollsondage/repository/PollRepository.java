package com.gkfcsolution.quickpollsondage.repository;

import com.gkfcsolution.quickpollsondage.model.Poll;
import org.springframework.data.repository.CrudRepository;

public interface PollRepository extends CrudRepository<Poll, Long> {
}
