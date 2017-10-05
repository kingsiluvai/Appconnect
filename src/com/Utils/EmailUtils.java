package com.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

import com.Resource.InputData;

public class EmailUtils {

	Multipart multipart = null;

	public void SendMail(ArrayList<String> fileNameList , ArrayList<String> PDFFileName,String xlsxFilePath) throws IOException, NoSuchProviderException {

		final String user = "apptestautomate@gmail.com";
		final String password = "896745231";
		String PathFolder = new SimpleDateFormat("ddMMMYYY/").format(new Date());
		Properties pro = new Properties();
		pro.put("mail.smtp.host", "smtp.gmail.com");
		pro.put("mail.smtp.socketFactory.port", "465");
		pro.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		pro.put("mail.smtp.auth", "true");
		pro.put("mail.smtp.port", "465");
		
		Session session = Session.getDefaultInstance(pro,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, password);
					}
				});
		try {
			String failurePDFFile = null;
			String successPDFFile = null;
			multipart = new MimeMultipart();
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(InputData.TO));
			message.addRecipients(Message.RecipientType.CC, InputData.CC);
			message.setSubject("AppConnect Automation Test Results ");
			MimeBodyPart messageBodyContent = new MimeBodyPart();
			messageBodyContent
					.setContent("Dear Team ,\n\n\t\t Automation Test has been completed successfully and we have also attached results document below ","text/html");
			System.out.println(fileNameList);
			File LogFile = new File(InputData.LogFile);
			if(LogFile.exists()){
				MimeBodyPart messageAttachement = new MimeBodyPart();
				messageAttachement.attachFile(InputData.LogFile);
				messageAttachement.setFileName("Complete Log File.log");
				multipart.addBodyPart(messageAttachement);
				}
			File xlsxFile = new File(xlsxFilePath);
			if(xlsxFile.exists()){
				MimeBodyPart messageAttachement = new MimeBodyPart();
				messageAttachement.attachFile(xlsxFile);
				messageAttachement.setFileName("XlsxReportFile.xlsx");
				multipart.addBodyPart(messageAttachement);
				
			}
			for(String xlsFileName:fileNameList){
			for(String fileName:PDFFileName){
				if(fileName.contains("Success")){
					MimeBodyPart messageAttachement = new MimeBodyPart();
					successPDFFile = InputData.PDFSuccessFilePath+PathFolder+xlsFileName+"_"+fileName+".pdf";
					File isSuccessPDFFile = new File(successPDFFile);
					if(isSuccessPDFFile.exists()){
					messageAttachement.attachFile(successPDFFile);
					messageAttachement.setFileName("Success Records - "+xlsFileName+".pdf");
					multipart.addBodyPart(messageAttachement);
					System.out.println(successPDFFile+" ************************************** added");
					}
				}else if (fileName.contains("Failure")){
					MimeBodyPart messageAttachement = new MimeBodyPart();
					failurePDFFile = InputData.PDFFailureFilePath+PathFolder+xlsFileName+"_"+fileName+".pdf";
					File isFailureFile = new File(failurePDFFile);
					if(isFailureFile.exists()){
					messageAttachement.attachFile(failurePDFFile);
					messageAttachement.setFileName("Failure Records - "+xlsFileName+".pdf");
					multipart.addBodyPart(messageAttachement);
					System.out.println(failurePDFFile+" ************************************** added");
					}
				}
			}
			multipart.addBodyPart(messageBodyContent);
			message.setContent(multipart);
			}
			Transport.send(message);
			System.out.println("message sent....");
		} catch (MessagingException ex) {
			System.out.println("Unable to senf Email due to :\n\n ");
			ex.printStackTrace();
		}
	}

}
