package com.mit.Hibernate_21april;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App 
{
    public static void main( String[] args )
    {
       student s =new student();
       s.setRoll(1);
       s.setName("Akhilesh");
       s.setCity("Pune");
       Configuration con=new Configuration().configure().addAnnotatedClass(student.class);
       SessionFactory sf=con.buildSessionFactory();
       Session session=sf.openSession(); 
       Transaction tx = session.beginTransaction();
       session.persist(s);
       tx.commit();
       session.close();
       sf.close();
    }
}
