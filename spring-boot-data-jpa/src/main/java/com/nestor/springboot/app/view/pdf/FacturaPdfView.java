package com.nestor.springboot.app.view.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.nestor.springboot.app.models.entity.Factura;

@Component("factura/ver")
public class FacturaPdfView extends AbstractPdfView {


	@Override
	protected void buildPdfDocument(Map<String, Object> model, com.lowagie.text.Document document,
			com.lowagie.text.pdf.PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}