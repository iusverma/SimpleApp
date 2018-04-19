package com.learning.hibernate.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.model.UserDetails;

public class FirstLevelCacheService {

	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		// Opening session
		Session session = sf.openSession();
		session.beginTransaction();

		UserDetails user1 = (UserDetails)session.get(UserDetails.class,1);
		System.out.println(user1);
		user1.setUserName("Update User1");
		System.out.println(user1);

		// Ensure that following query will not result as a second select
		// as user is already in cache 
		UserDetails user2 = (UserDetails)session.get(UserDetails.class,1);
		System.out.println(user2);

		// session.getTransaction().commit();
		// Closing session
		session.close();
	}

}
