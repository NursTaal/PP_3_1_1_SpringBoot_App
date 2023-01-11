package Spring1.Springboot2.DAO;


import Spring1.Springboot2.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUserById(int id, User user) {
         User user1 = entityManager.find(User.class,id);
         user1.setName(user.getName());
         user1.setSurName(user.getSurName());
        entityManager.merge(user1);
    }

    @Override
    public void deleteUserById(int id) {
        User user = entityManager.find(User.class,id);
        entityManager.remove(user);
    }

}
