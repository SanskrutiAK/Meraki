package com.sanskruti.myapp;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.Session;
import com.sanskruti.dao.ReaderDao;
import com.sanskruti.dao.UserDao;
import com.sanskruti.dao.WriterDao;
import com.sanskruti.pojo.Article;
import com.sanskruti.pojo.Favourites;
import com.sanskruti.pojo.Reader;
import com.sanskruti.pojo.User;
import com.sanskruti.pojo.Writer;

import javassist.expr.NewArray;

@Controller

public class WriterController {
	
	@RequestMapping(value = "/user/writerdb.htm", method = RequestMethod.POST)	
	public String handelwritercontent(@ModelAttribute("writer") Writer writer,HttpServletRequest request, WriterDao writerdao) { 
		 String locaLpath="C:\\Users\\Sanskruti\\OneDrive\\";
		 
		 String workNewname= generateFileName(writer.getPdf());
		 Article a= new Article();
		 a.setArticle(workNewname);
		 writer.getArticle().add(a);
		 
		 
		 HttpSession session =request.getSession();
		 User u =(User)session.getAttribute("loggeduser");
		 
		 writer.setName(u.getFirstName());
	 
		 try {
				File file = new File(locaLpath+workNewname);
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(writer.getPdf().getBytes());
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		 		 
		 try {
			 Writer w1 =writerdao.addWork(writer,a);
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		return "Success";
		}
	
	    private String generateFileName(MultipartFile multiPart) {
		return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	    }
	    
	    public String uploadFile(MultipartFile multipartFile) throws Exception {
			String fileName = generateFileName(multipartFile);
			String uploadDir = "uploads/";
			String filePath = uploadDir + fileName;
			try {
				File file = new File(filePath);
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(multipartFile.getBytes());
				fos.close();
			} catch (Exception e) {
				throw e;
			}

			return filePath;

		}
	    
	    
	    @RequestMapping(value = "/user/readerdb.htm", method = RequestMethod.POST)	
		public ModelAndView handelwritercontent(@ModelAttribute("reader") Reader reader,HttpServletRequest request, ReaderDao readerdao) { 
	    	ModelAndView model =null ;
	    	try {
	    	String genre = request.getParameter("genre");
	    	ReaderDao rdo = new ReaderDao();
	    	reader.setLastSearch(genre);
	    	HttpSession session =request.getSession();
	    	User u =(User)session.getAttribute("loggeduser");
	    	Reader r =readerdao.getReader(u.getUserName());
	    	if(r==null) {
	    	reader.setName(u.getUserName());
	    	rdo.addvals(reader);
	    	}
        	List<Writer> work = (List<Writer>)rdo.getArticles(genre);
        	model = new ModelAndView("ReaderResult","work",work);
        	return model;
            }catch (Exception e) {
            e.printStackTrace();
        }	
	    	return null;    
}
	    
	    @RequestMapping(value = "/user/writerupdate.htm", method = RequestMethod.POST)	
	    public ModelAndView updateWrittenWork(@ModelAttribute("article") Article article, HttpServletRequest request, WriterDao writerdao) {
	    	
	    	long wid =Integer.parseInt((request.getParameter("wid")));
	    	String workNewname= generateFileName(article.getPdf());
			Article a= new Article();
			a.setArticle(workNewname);
			Writer w = writerdao.get(wid);
			w.getArticle().add(a);
			String locaLpath="C:\\Users\\Sanskruti\\OneDrive\\";
			try {
				File file = new File(locaLpath+workNewname);
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(article.getPdf().getBytes());
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				 Article w1 =writerdao.addarticle(a);
			 }
			 catch(Exception e) {
				 e.printStackTrace();
			 }
	    	return new ModelAndView("SuccessUpdate");
	    	}
	    
	    
	    @RequestMapping(value ="/user/readerfav.htm", method = RequestMethod.POST)	
	    public ModelAndView addToFavs(@ModelAttribute("reader") Reader reader, HttpServletRequest request, ReaderDao readerdao, WriterDao writerdao) {
	    	ModelAndView mv=null;
	    	long rfid =Integer.parseInt((request.getParameter("fave")));
//	    	Reader r = reader;
	    	
	    	HttpSession session =request.getSession();
	    	User u =(User)session.getAttribute("loggeduser");
	    	Reader r=readerdao.getReader(u.getUserName());
	    	if(r==null) {
	    		
	    	}
	    	if(r!=null) {
	    	System.out.println("Here !");	
	    	Writer fw =writerdao.get(rfid);
	    	r.getFavouritereads().add(fw);
	    	fw.getFavs().add(r);
	    	try {
				writerdao.addvals(fw);
				readerdao.addvals(r);
			 }
			 catch(Exception e) {
				 e.printStackTrace();
			 }
	    	}
	    	return new ModelAndView("SuccessFav");
	    }
//	    @RequestMapping(value ="/user/delete.htm", method = RequestMethod.GET)
//	    public ModelAndView removefav(HttpServletRequest request,ReaderDao readerdao, WriterDao writerdao) {
//	    	
//	    	int title =Integer.parseInt(request.getParameter("title"));
//	    	HttpSession session =request.getSession();
//	    	User u =(User)session.getAttribute("loggeduser");
//	    	Reader r=readerdao.deletefav(title);
//	    	return null;
//	    }

}
