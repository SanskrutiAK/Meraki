package com.sanskruti.dao;

import java.io.Reader;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import com.sanskruti.pojo.Article;
import com.sanskruti.pojo.Writer;

public class WriterDao extends DAO {
	
	public Writer addWork(Writer writer,Article article) {
		try {
			begin();
			getSession().save(writer);
			getSession().save(article);			
			commit();
		}catch (HibernateException e) {
			rollback();
		}
		return writer;
	}
	
	public List<Writer> getWork(String name){
		try {
			
            System.out.println("Hello");
            Query q = getSession().createQuery("from Writer where name=:name");
            
            q.setString("name",name);
            List<Writer> res= q.list();
            System.out.println(res.size());
            //System.out.println(artwork.size());
            //return artwork;
            return res;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
		return null;
	}

	public Writer get(long userId){
		try {
			begin();
			Query q = getSession().createQuery("from Writer where wid = :userId");
			q.setLong("userId", userId);
			Writer writer = (Writer) q.uniqueResult();
			commit();
			return writer;
		} catch (HibernateException e) {
			rollback();
		}
		return null;
	}

public Article addarticle(Article article) {
	try {
		begin();
		getSession().save(article);			
		commit();
	}catch (HibernateException e) {
		rollback();
	}
	return article;
}

public void addvals(Writer w) {
	try {
		begin();
		getSession().save(w);		
		commit();
	}catch (HibernateException e) {
		rollback();
	}
}






}