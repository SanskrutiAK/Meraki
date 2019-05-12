package com.sanskruti.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.sanskruti.pojo.User;

public class UserDao extends DAO {

	public User Register(User user) {
		try {
			begin();
			getSession().save(user);
			commit();
		}catch (HibernateException e) {
			rollback();
		}
		return user;
	}
	
	public User Login(String username, String password)throws Exception {
		try {
		begin();
        Query q = getSession().createQuery("from User where userName = :username and password = :password");
        q.setString("username", username);
        q.setString("password", password);
        User user = (User) q.uniqueResult();
        return user;
    	} catch (HibernateException e) {
        rollback();
        throw new Exception("Could not get user " + username);
    }
	}
	
	
	
	
	
	
	
	
	
}
