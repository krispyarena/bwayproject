package com.bway.bwayproject.utils;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bway.bwayproject.model.Student;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class StudentPdfView extends AbstractPdfView {
	
	@Override
	protected void buildPdfMetadata(
			Map<String, Object> model, 
			Document document, HttpServletRequest request)
	{
		HeaderFooter header = new HeaderFooter(new Phrase("STUDENT PDF VIEW"), false);
		header.setAlignment(Element.ALIGN_CENTER);
		document.setHeader(header);
		
		HeaderFooter footer = new HeaderFooter(new Phrase(new Date()+" (C) bway, Page # "), true);
		footer.setAlignment(Element.ALIGN_CENTER);
		document.setFooter(footer);
	}

	@Override
	protected void buildPdfDocument(
			Map<String, Object> model, 
			Document document, 
			PdfWriter writer,
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		
		//download PDF with a given filename
		response.addHeader("Content-Disposition", "attachment;filename=employee.pdf");

		//read data from controller
		@SuppressWarnings("unchecked")
		List<Student> list = (List<Student>) model.get("sList");
		
		//create element
		//Font (Family, Size, Style, Color)
		Font titleFont = new Font(Font.TIMES_ROMAN, 10, Font.BOLD, Color.BLUE);
		Paragraph title = new Paragraph("EMPLOYEE DATA",titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingBefore(20.0f);
		title.setSpacingAfter(25.0f);
		//add to document
		document.add(title);
		
		Font tableHead = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL, Color.MAGENTA);
		PdfPTable table = new PdfPTable(6);// no.of columns
		table.addCell(new Phrase("ID",tableHead));
		table.addCell(new Phrase("Name",tableHead));
		table.addCell(new Phrase("Email",tableHead));
		table.addCell(new Phrase("Phone",tableHead));
		table.addCell(new Phrase("Date of Birth", tableHead));
		table.addCell(new Phrase("Roll",tableHead));
		
		for(Student spec : list ) {
			table.addCell(String.valueOf(spec.getId()));
			table.addCell(spec.getName());
			table.addCell(spec.getEmail());
			table.addCell(spec.getPhone());
			table.addCell(String.valueOf(spec.getDob()));
			table.addCell(String.valueOf(spec.getRoll()));
		}
		//add to document
		document.add(table);
	}
}
