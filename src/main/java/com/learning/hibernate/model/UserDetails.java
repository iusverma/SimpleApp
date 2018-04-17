package com.learning.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name="USER_DETAILS_BY_ID", query="from UserDetails where id = :id")
@NamedNativeQuery(name="USER_DETAILS_BY_NAME",
				  query="select * from UserDetails where name = :name",
				  resultClass=UserDetails.class)
@Table(name="USER_DETAILS")
public class UserDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;

	@Column(name="USER_NAME")

	private String userName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String toString(){
		return new String("{"+id+", "+userName+"}");
	}
}
