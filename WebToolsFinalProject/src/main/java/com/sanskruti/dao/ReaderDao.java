package com.sanskruti.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.sanskruti.pojo.Reader;
import com.sanskruti.pojo.User;
import com.sanskruti.pojo.Writer;

public class ReaderDao extends DAO {
	
	public List<Writer> getArticles(String genre) {
		try {
//		begin();
			System.out.println(genre);
		Query q = getSession().createQuery("from Writer where genre=:genre");
		q.setString("genre",genre);
		List<Writer> res= q.list();
        System.out.println(res.size());
		return res;
	}catch(Exception e){
        System.out.println(e.getMessage());
    }
	return null;
	}
	
	
	
	public void addvals(Reader r) {
		try {
			begin();
			getSession().save(r);		
			commit();
		}catch (HibernateException e) {
			rollback();
		}
	}
	
	public Reader getReader(String Name) {
		try {
			begin();
			System.out.println(Name);
			Query q = getSession().createQuery("from Reader where name=:name");
			q.setString("name",Name);
			Reader rd = (Reader)q.uniqueResult();
			commit();
			return rd;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public Reader getReader(String Name,String gibberish) {
		try {
			System.out.println(Name);
			Query q = getSession().createQuery("from Reader where name=:name");
			q.setString("name",Name);
			Reader rd = (Reader)q.uniqueResult();
			commit();
			return rd;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
		
	
	
}
