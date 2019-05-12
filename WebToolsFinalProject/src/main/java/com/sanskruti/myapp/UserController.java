package com.sanskruti.myapp;

import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sanskruti.dao.ReaderDao;
import com.sanskruti.dao.UserDao;
import com.sanskruti.dao.WriterDao;
import com.sanskruti.pojo.Article;
import com.sanskruti.pojo.Reader;
import com.sanskruti.pojo.User;
import com.sanskruti.pojo.Writer;
import com.sanskruti.validations.UserValidator;

@Controller
public class UserController {

//@Autowired
//userValidation uservalidator;
	
@Autowired

UserValidator uservalidator;

@InitBinder("user")
protected void initBinder(WebDataBinder binder) {
binder.setValidator(uservalidator);
}

		
@RequestMapping(value = "/user/create.htm", method = RequestMethod.GET)
public String RegisterForm(ModelMap model,User user) {
	model.addAttribute("user", user);
	return "Register";
}


@RequestMapping(value = "/user/login.htm", method = RequestMethod.GET)
public ModelAndView LoginForm(HttpServletRequest request, UserDao userDao, ModelMap map) {
	HttpSession session =request.getSession();
	if(session!=null) {
//		System.out.println("Hello!");
		User usr=(User) session.getAttribute("loggeduser");
		try {
	        User u = userDao.Login(usr.getUserName(), usr.getPassword());

	        	if (u != null) {
	        	String role = u.getRole();
	        	
	        	if(role.equalsIgnoreCase("Writer")) {
	        		
	        	map.addAttribute("writer", new Writer());
	        	map.addAttribute("article", new Article());
	        	WriterDao wdo = new WriterDao();
	        	List<Writer> work = (List<Writer>)wdo.getWork(u.getFirstName());
	        	return new ModelAndView("Writer","work",work);
	        	}
	        	
	        	if(role.equalsIgnoreCase("Reader")) {
	        	String p = request.getParameter("param");
	        	
	        	map.addAttribute("reader", new Reader());
	        	ReaderDao rdao = new ReaderDao();
	        	Reader r=null;
	        	if(p.equalsIgnoreCase("ret")) {
	        		 r =rdao.getReader(u.getUserName(),"gibbrish");
	        	}
	        	else {
	        	 r =rdao.getReader(u.getUserName());
	        	 }
	  
	        	if(r!=null) {
	        	List<Writer> r1 = (List<Writer>)r.getFavouritereads();
	        	return new ModelAndView("Reader","favs",r1); 
	        	}
	        	return new ModelAndView("Reader");
	        	}
	            }
	        else {
	        	return new ModelAndView("Error");  
	             }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }			
	}
	return new ModelAndView("home");
}



@RequestMapping(value = "/user/create.htm", method = RequestMethod.POST)	
public String handelCreateForm(@Validated @ModelAttribute("user") User user,BindingResult bindingResult,HttpServletRequest request, UserDao userdao,ModelMap model) {
	model.addAttribute("user", user);
//	uservalidator.validate(user, bindingResult);
	if(bindingResult.hasErrors()) {
		
		return "Register";
}
	
	 HttpSession session = request.getSession();
	 try {
	 User u =userdao.Register(user);
	 sendEmail(request);
	 session.setAttribute("loggeduser", u);
 } 
 catch(Exception e) {
		 e.printStackTrace();
	 }
	return "Registration Successful";
}














//@RequestMapping(value = "/user/login.htm", method = RequestMethod.POST)
//public String handleLoginForm(HttpServletRequest request, UserDao userDao, ModelMap map) {
//	HttpSession session = request.getSession();
//	
//    String username = request.getParameter("username");
//    String password = request.getParameter("password");
//    try {
//        User u = userDao.Login(username, password);
//
//        if (u != null) {
//        	session.setAttribute("loggeduser", u);
//        	String role = u.getRole();
//        	if(role.equalsIgnoreCase("Writer"))
//        		
//            return "Writer";
//        	if(role.equalsIgnoreCase("Reader"))
//            return "Reader";     	
//        }
//        else {
//            return "Error";
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//    return null;
//}


@RequestMapping(value = "/user/login.htm", method = RequestMethod.POST)
public ModelAndView handleLoginForm(@ModelAttribute("usr") User usr,HttpServletRequest request, UserDao userDao, ModelMap map,Reader reader,ReaderDao rdao) {
	HttpSession session = request.getSession();
	ModelAndView model =null ;
	try {
        User u = userDao.Login(usr.getUserName(), usr.getPassword());

        	if (u != null) {
        	session.setAttribute("loggeduser", u);
        	
        	String role = u.getRole();
        	
        	if(role.equalsIgnoreCase("Writer")) {
        		
        	map.addAttribute("writer", new Writer());
        	map.addAttribute("article", new Article());
        	WriterDao wdo = new WriterDao();
        	List<Writer> work = (List<Writer>)wdo.getWork(u.getFirstName());
        	model = new ModelAndView("Writer","work",work);
        	}
        	
        	if(role.equalsIgnoreCase("Reader")) {
        	map.addAttribute("reader", reader);
        	Reader r =rdao.getReader(u.getUserName(),"thisone");
        	if(r!=null) {
        	List<Writer> r1 = (List<Writer>)r.getFavouritereads();
        	return new ModelAndView("Reader","favs",r1);
        	}
        	else {
        		return new ModelAndView("Reader");
        	}
        	}
            }
        else {
        	model = new ModelAndView("Error");  
             }
    } catch (Exception e) {
        e.printStackTrace();
    }	
	return model;
}


public String sendEmail(HttpServletRequest request){
	try{
		System.out.println("here !");
		HttpSession session = request.getSession();
		String from = "karkhanissanskruti29@gmail.com";
        String host = "smtp.gmail.com";
        String to = request.getParameter("userName");
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.starttls.enable","true");//starts TLS connection
        properties.put("mail.smtp.auth", "true");
        Authenticator auth = new SMPTAuth();
        Session sess = Session.getDefaultInstance(properties,auth);
        
        
        MimeMessage message = new MimeMessage(sess);

 
        message.setFrom(new InternetAddress(from));


        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        

        message.setSubject("Registration Confirmation");
        

        message.setText("You have been Successfully Registered !"
        		+ "Your userName is "+request.getParameter("userName")
        		+"Your Password is "+request.getParameter("password"));
        Transport.send(message);
        System.out.println("Sent message successfully");
	}
	catch(Exception e)
	{
		System.out.println("Could not send email "+e.getMessage());
	}
	return "Success";
	
}


@RequestMapping(value = "/user/logout.htm")
public String logout(HttpServletRequest request,HttpServletResponse response) {
	HttpSession session =request.getSession();
	session.invalidate();
	return "home";
}








}
