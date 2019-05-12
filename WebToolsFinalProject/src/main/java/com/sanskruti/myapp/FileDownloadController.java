package com.sanskruti.myapp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping(value="/")
public class FileDownloadController {
	@RequestMapping(value="/report.htm", method=RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		System.out.println("Hello");
		File file = new File("C:\\Users\\Sanskruti\\OneDrive\\"+request.getParameter("article"));
		InputStream inputstream = new BufferedInputStream(new FileInputStream(file));
		String mimeType =URLConnection.guessContentTypeFromStream(inputstream);
		
		if(mimeType==null) {
			mimeType="application/octet-stream";
		}
		
		response.setContentType(mimeType);
		response.setContentLength((int)file.length());
		response.setHeader("Content-disposition",String.format("attachment; filename=\"Article.pdf\"", file.getName()) );
		
		FileCopyUtils.copy(inputstream, response.getOutputStream());
	}

}
