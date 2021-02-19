package hiber.dao;


import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;


@SuppressWarnings("UnnecessaryLocalVariable")
@Repository

public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserCar(String mod, int ser) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(" FROM User where  userCar.model = :model and userCar.series = :series");
        query.setParameter("model", mod).setParameter("series", ser);
        if (query.getSingleResult()!=null){ return query.getSingleResult();
        }
        else {throw  new NoResultException();
        }
    }

}
