package com.bway.bwayproject.utils;

import java.util.List;
import java.util.Map;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.bway.bwayproject.model.Student;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class StudentExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(
			Map<String, Object> model, 
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response) 
					throws Exception {

		//1. define your own excel file name
		response.addHeader("Content-Disposition", "attachment;filename=student.xls");
		
		//2. read data given by Controller
		@SuppressWarnings("unchecked")
		List<Student> list = (List<Student>) model.get("sList");
		
		//3. create one sheet
		Sheet sheet = workbook.createSheet("STUDENT");
		
		//4. create row#0 as header
		setHead(sheet);
		
		//5. create row#1 onwards from List<T> 
		setBody(sheet,list);
	}


	private void setHead(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("NAME");
		row.createCell(2).setCellValue("EMAIL");
		row.createCell(3).setCellValue("PHONE");
		row.createCell(4).setCellValue("ROLL");
		row.createCell(5).setCellValue("Age");
	}
	
	private void setBody(Sheet sheet, List<Student> list) {
		int rowNum = 1;
		for(Student spec : list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(spec.getId());
			row.createCell(1).setCellValue(spec.getName());
			row.createCell(2).setCellValue(spec.getEmail());
			row.createCell(3).setCellValue(spec.getPhone());
			row.createCell(4).setCellValue(spec.getRoll());
			row.createCell(5).setCellValue(spec.getAge());
		}
	}

}