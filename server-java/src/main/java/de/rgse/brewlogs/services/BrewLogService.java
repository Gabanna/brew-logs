package de.rgse.brewlogs.services;

import com.querydsl.jpa.impl.JPAQuery;
import de.rgse.brewlogs.domain.BrewLog;
import de.rgse.brewlogs.domain.QBrewLog;
import de.rgse.brewlogs.domain.QUser;
import de.rgse.brewlogs.domain.User;
import org.camunda.bpm.engine.RuntimeService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class BrewLogService {

    private static final QBrewLog BREW_LOG = QBrewLog.brewLog;
    private static final QUser USER = QUser.user;

    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;

    public BrewLog persist(BrewLog brewLog, long userId) {
        User user = new JPAQuery<User>(entityManager).from(USER).where(USER.id.eq(userId)).fetchOne();
        if(user != null) {
            brewLog.setUser(user);
            entityManager.persist(brewLog);
            return brewLog;

        } else {
            throw new IllegalStateException(String.format("user with id %s is unknown", userId));
        }
    }

    public List<BrewLog> findByUserName(String username) {
        return new JPAQuery<BrewLog>(entityManager).from(BREW_LOG).where(BREW_LOG.user.username.eq(username)).orderBy(BREW_LOG.id.asc()).fetch();
    }

    public void removeById(long brewLogId) {
        BrewLog log = entityManager.find(BrewLog.class, brewLogId);
        entityManager.remove(log);
    }
}
