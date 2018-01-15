package ru.testAssignment.voting.service;

import ru.testAssignment.voting.model.User;
import ru.testAssignment.voting.model.Vote;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface VoteService {

    Vote update(Vote vote, int userId, LocalTime time);

    Vote create(Vote vote, int userId, LocalTime time);

    Vote get(int id, int userId);

    List<Vote> getAll(int userId);

    void delete(int id, int userId);

    List<Vote> getByDate(LocalDate date);

    List<User> getVoted(LocalDate date) ;

    Integer voteId(LocalDate date, int userId);
}