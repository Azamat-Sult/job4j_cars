package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Init {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Candidate junior = Candidate.of("Junior", "No exp", 80000);
            Candidate middle = Candidate.of("Middle", "2 year exp", 150000);
            Candidate senior = Candidate.of("Senior", "5 year exp", 250000);

            session.save(junior);
            session.save(middle);
            session.save(senior);

            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
