package com.sanskruti.myapp;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {
    ArrayList<String> genreList;
    public AjaxController(){
    	genreList = new ArrayList<String>();
    	genreList.add("romantic");
    	genreList.add("chicklit");
    	genreList.add("Drama");
    	genreList.add("Romcom");
    	genreList.add("Thriller");
    	genreList.add("Thriller-Drama");
    	genreList.add("Murder-Mystry");
    	genreList.add("Food");
    	genreList.add("journal");
    	
    }
    
    @RequestMapping(value = "/ajaxservice.htm", method = RequestMethod.POST)
    @ResponseBody
    public String ajaxService(HttpServletRequest request)
    {
        String queryString = request.getParameter("course");
        String result = "";
        for(int i =0;i<genreList.size();i++){
            if(genreList.get(i).toLowerCase().contains(queryString.toLowerCase())){
                result +=genreList.get(i)+",";
            }
        }
        
        return result;
    }

}
