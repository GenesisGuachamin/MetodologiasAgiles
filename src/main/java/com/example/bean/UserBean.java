package com.example.bean;

import com.example.model.User;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@ManagedBean
@ViewScoped
public class UserBean {
    private List<User> users;

    @PostConstruct
    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        users = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    public List<User> getUsers() {
        return users;
    }
}