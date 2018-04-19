package com.learning.hibernate.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.model.PersonDetails;

public class SecondLevelCacheService {

	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		for(int i=1;i<11;i++){
			PersonDetails person = new PersonDetails();
			person.setPersonName("Person"+i);
			session.save(person);
		}
		session.getTransaction().commit();
		
		PersonDetails person1 = (PersonDetails)session.get(PersonDetails.class, 3);
		System.out.println(person1);
		session.close();

		//opening new session
		session = sf.openSession();
		PersonDetails person2 = (PersonDetails)session.get(PersonDetails.class, 3);
		System.out.println(person2);
	}
}
