package com.kingjoshdavid;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        final Main main = new Main();
        main.run();
    }

    private void run() {
        {
            Peanut peanut = new Peanut();
            peanut.setRoast(3);

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(peanut);

            transaction.commit();
            session.close();
        }


        {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Peanut.class);
            List list = criteria.list();
            System.out.println(list);
            session.close();
        }
    }
}
