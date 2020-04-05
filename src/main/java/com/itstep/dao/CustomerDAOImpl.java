package com.itstep.dao;

import com.itstep.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    // инжектирую session factory
    @Autowired
    private SessionFactory sessionFactory;


    public List<Customer> getCustomers() {

        // получаю текущую hibernate сессию
        Session currentSession = sessionFactory.getCurrentSession();

        // создаю запрос и сортирую по last name
        Query<Customer> theQuery =
                currentSession.createQuery("from Customer order by lastName",
                        Customer.class);

        // выполняю запрос и получаю result list
        List<Customer> customers = theQuery.getResultList();

        // возвращаю список клиентов
        return customers;
    }


    public void saveCustomer(Customer theCustomer) {

        // получаю текущую hibernate сессию
        Session currentSession = sessionFactory.getCurrentSession();

        // сохраняю/обновляю информацию по клиенту
        currentSession.saveOrUpdate(theCustomer);

    }


    public Customer getCustomer(int theId) {

        // получаю текущую hibernate сессию
        Session currentSession = sessionFactory.getCurrentSession();

        // читаю с базы данных информацию по клиенту используя его primary key
        Customer theCustomer = currentSession.get(Customer.class, theId);

        return theCustomer;
    }


    public void deleteCustomer(int theId) {

        // получаю текущую hibernate сессию
        Session currentSession = sessionFactory.getCurrentSession();

        // удаляю запись по id
        Query theQuery =
                currentSession.createQuery("delete from Customer where id=:customerId");
        theQuery.setParameter("customerId", theId);

        theQuery.executeUpdate();
    }

}











