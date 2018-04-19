package com.learning.hibernate.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.learning.hibernate.model.UserDetails;

public class CriteriaQueryAppService {
	public static void main(String [] args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		// Opening session
		Session session = sf.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
	
		CriteriaQuery<UserDetails> cQuery = cb.createQuery(UserDetails.class);
		Root<UserDetails> root = cQuery.from(UserDetails.class);
		cQuery.select(root).where(cb.equal(root.get("id"), 5));

		Query <UserDetails> query = session.createQuery(cQuery);
	
		List<UserDetails> list1 = (List<UserDetails>)query.getResultList();
		System.out.println("User with id 5: "+list1);
	
		// Closing Session
		session.close();
	}
}
