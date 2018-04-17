package com.learning.hibernate.service;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.model.UserDetails;

public class SimpleAppService {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		for(int i=1;i<11;i++){
			UserDetails user = new UserDetails();
			user.setUserName("User"+i);
			session.save(user);
		}
		session.getTransaction().commit();
		
		//session = sf.openSession();
		@SuppressWarnings("deprecation")
		Query query = session.createQuery("from UserDetails");
		query.setFirstResult(5);
		query.setMaxResults(3);

		List list = query.list();
		System.out.println("Loaded Users: "+list.size());
		System.out.println("Unmodified list: "+list);
		
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			System.out.println(((UserDetails)iter.next()).toString());
		}
		
		// getting user name column
		Query qUsrName = session.createQuery("select userName from UserDetails");
		List<String> listUserNames = (List<String>)qUsrName.list();
		System.out.println("User's Names: "+listUserNames);
		
		// select specific user using '?'
		Query query1 = session.createQuery("select userName from UserDetails where id > ? and userName = ?");
		query1.setInteger(0, Integer.parseInt("5"));
		query1.setString(1, "User7");	
		List<String> list1 = (List<String>)query1.list();
		System.out.println("User's Names: "+list1);
		
		// select specific user using :
		Query query2 = session.createQuery("select userName from UserDetails where id > :id and userName = :userName" );
		query2.setInteger("id", Integer.parseInt("3"));
		query2.setString("userName", "User5");	
		List<String> list2 = (List<String>)query2.list();
		System.out.println("User's Names: "+list2);

		// Using named HQL query
		Query q3 = session.getNamedQuery("USER_DETAILS_BY_ID");
		q3.setInteger("id", Integer.parseInt("3"));
		List<String> l3 = (List<String>)q3.list();
		System.out.println("User from named HQL query: "+l3);
		
		// Using named native query
		Query q4 = session.getNamedQuery("USER_DETAILS_BY_NAME");
		q4.setString("name", "User7");
		List<String> l4 = (List<String>)q3.list();
		System.out.println("User from named native query: "+l4);
		
		// finally closing session
		session.close();
	}
}
