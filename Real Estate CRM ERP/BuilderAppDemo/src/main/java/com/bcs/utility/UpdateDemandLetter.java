package com.bcs.utility;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class UpdateDemandLetter {

	@SuppressWarnings("static-access")
	public static String processPdf(String src, String dest, String createdDate, String UpdatedDate) throws IOException, DocumentException {
		
		PdfReader pdfReader = new PdfReader(src);
		PdfDictionary distc = pdfReader.getPageN(1);
		PdfObject object = distc.getDirectObject(PdfName.CONTENTS);
		
		if (object instanceof PRStream) {
			PRStream prStream = (PRStream) object;
			byte[] data = pdfReader.getStreamBytes(prStream);
			String dd = new String(data);
			dd = dd.replace(createdDate, UpdatedDate);
			prStream.setData(dd.getBytes());
			pdfReader.close();
		}
		
		PdfStamper stamper = new PdfStamper(pdfReader, new FileOutputStream(dest));
		stamper.close();
		
		return dest;
	}
}
