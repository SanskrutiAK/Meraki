package com.sanskruti.dao;

import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.sanskruti.pojo.User;





public class PDFView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
			response.setHeader("Content-disposition","attachment; filename=\"user_list.pdf\" ");
			
			@SuppressWarnings("unchecked")
			List<User> userList =(List<User>) model.get("userlist");
			Table table = new Table(5);
			table.addCell("userid");
			table.addCell("firstName");
			table.addCell("lastName");
			table.addCell("userName");
			table.addCell("role");
			
			
			for(User u:userList) {
				table.addCell(String.valueOf(u.getUserid()));
				table.addCell(u.getFirstName());
				table.addCell(u.getLastName());
				table.addCell(u.getUserName());
				
			}
			
		document.add(table);
	}

}
