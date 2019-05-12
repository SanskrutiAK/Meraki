package com.sanskruti.myapp;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

class SMPTAuth extends Authenticator
{
public PasswordAuthentication getPasswordAuthentication()
{
	    String username = "karkhanissanskruti29@gmail.com";
	    String password = "sanskruti29";
	    return new PasswordAuthentication(username, password);
}
}
	
	
