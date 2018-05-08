package de.rgse.brewlogs.services;

import com.querydsl.jpa.impl.JPAQuery;
import de.rgse.brewlogs.api.vo.RegisterVo;
import de.rgse.brewlogs.domain.QUser;
import de.rgse.brewlogs.domain.User;
import de.rgse.brewlogs.exceptions.UserExistsException;
import de.rgse.brewlogs.exceptions.UsernameEmailTakenException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserService {

    private static final QUser USER = QUser.user;

    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;

    public User registerUser(RegisterVo register) throws UserExistsException, UsernameEmailTakenException {
        User result = null;

        long count = new JPAQuery<User>().from(USER).where(USER.username.equalsIgnoreCase(register.getUsername()).and(USER.email.equalsIgnoreCase(register.getEmail()))).fetchCount();

        if(count == 0) {
            count = new JPAQuery<User>(entityManager)
                    .from(USER)
                    .where(USER.username.equalsIgnoreCase(register.getUsername())
                            .or(USER.email.equalsIgnoreCase(register.getEmail())))
                    .fetchCount();

            if (count == 0) {
                result = new User(register.getUsername(), register.getEmail(), register.getPassword());
                entityManager.persist(result);
                return result;

            } else {
                throw new UsernameEmailTakenException(register.getUsername(), register.getEmail());
            }

        } else {
            throw new UserExistsException(register.getUsername());
        }
    }
}
