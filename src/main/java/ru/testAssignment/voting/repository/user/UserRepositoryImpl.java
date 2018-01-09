package ru.testAssignment.voting.repository.user;

import org.hibernate.jpa.QueryHints;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.testAssignment.voting.model.User;
import ru.testAssignment.voting.model.Vote;
import ru.testAssignment.voting.repository.user.UserRepository;
import ru.testAssignment.voting.repository.vote.VoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    private VoteRepository voteRepository;

    @Override
    public User save(User user, int userId) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Override
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public User get(int id)  {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }

    @Override
    public User getByEmail(String email) {
        List<User> users = em.createNamedQuery(User.BY_EMAIL, User.class)
                .setParameter(1, email)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getNotVoted() {
        List<User> votedUsers = getAll();
        List<Vote> votes = voteRepository.getByDate(LocalDate.now());

        votes.forEach(vote->{
            User user = vote.getUser();
            if (votedUsers.contains(user)) votedUsers.remove(user);
        });

        return votedUsers;
    }
}