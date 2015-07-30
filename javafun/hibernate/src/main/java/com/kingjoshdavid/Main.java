package com.kingjoshdavid;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

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
            Gallery gallery = new Gallery();
            gallery.setCurtain("dingy but distinguished");
            final Peanut a = new Peanut();
            final Peanut b = new Peanut();
            final Peanut c = new Peanut();
            a.setRoast(10);
            b.setRoast(2);
            c.setRoast(-7);
            gallery.setMembers(new HashSet<Peanut>(Arrays.asList(a, b, c)));

            Peanut d = new Peanut();
            Peanut e = new Peanut();
            d.setRoast(11);
            e.setRoast(37);

            final Session session = HibernateUtil.getSessionFactory().openSession();
            final Transaction transaction = session.beginTransaction();

            session.save(d);
            session.save(e);
            session.save(gallery);



            gallery.setVips(new HashSet<Peanut>(Arrays.asList(d, e)));

            transaction.commit();
            session.close();
        }


        {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria galleries = session.createCriteria(Gallery.class);
            System.out.println(galleries.list());

            Criteria peanuts = session.createCriteria(Peanut.class);
            System.out.println(peanuts.list());

            session.close();
        }


        HibernateUtil.shutdown();
    }
}
