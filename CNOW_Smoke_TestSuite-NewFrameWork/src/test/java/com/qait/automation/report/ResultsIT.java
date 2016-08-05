package com.qait.automation.report;

import static com.qait.automation.utils.DataReadWrite.getProperty;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.qait.automation.utils.YamlReader;




public class ResultsIT extends ReformatTestFile{

	String today = new Date().toString();
	String resultOfRun = null;
	String host = "smtp.gmail.com";
	String from = "cnow.automation.qa@gmail.com";
	String password = "qainfotech";
	int port = 465;
	String failureResults = "";
	String skippedResults = "";
	String passedResults = "";
	String totaltest = "";
	String passedResult = "";
	boolean sendResults = false;
    final String projectName = "CNOW Smoke"; 
	public static int count=0;

	@BeforeClass
	void setupMailConfig() {
		YamlReader.setYamlFilePath();
	}

	
	 @Test
     public void changeReportColor() throws IOException  {
         String html = readLargerTextFile("target/surefire-reports/emailable-report.html");

         html = replacealltimestamp(html);
         writeLargerTextFile("target/surefire-reports/emailable-report.html", html);
     }
	
	@Test
	public void sendResultsMail() throws MessagingException, IOException {
		
			if (true) { // send email is true *************************
				Message message = new MimeMessage(getSession());
				message.addFrom(new InternetAddress[] { (new InternetAddress(from)) });
				setMailRecipient(message);
				message.setContent(setAttachment());
				message.setSubject(setMailSubject());
				
				
				try{
					message.setFrom(new InternetAddress(from,"Automation QA - CNOWv7"));
				}catch(Exception e){System.out.println(e);}
				
				Session session = getSession();
				Transport transport = session.getTransport("smtps");
				transport.connect(host, from, password);
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();
			}
			System.out.println("Reports emailed");
		
		
	}

	private Session getSession() {
		Authenticator authenticator = new Authenticator(from, password);
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtps");
		properties.put("mail.smtps.auth", "true");
		properties.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", String.valueOf(port));
     	properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		return Session.getInstance(properties, authenticator);
	}

	private String setBodyText() throws IOException {
	/*	List<String> failedResultsList= printFailedTestInformation();
		String[] failedResultArray= new String[failedResultsList.size()];
		for(int i=0;i<failedResultArray.length;i++){
			failedResultArray[i]=failedResultsList.get(i);
		}*/
		String mailtext = "";
		mailtext = "Hi All,<br><br>";
		mailtext = mailtext
				+ "</br>Core Smoke test suite was executed for Title <b>\""+YamlReader.getYamlValue("book_name")+"\"</b> on build <b>\""+YamlReader.getYamlValue("cnow_build_version")+"\"</b>. Please find below summary of the test results:- <br>";
		mailtext = mailtext
				+ "<br><b>Application URL: </b>"
				+ YamlReader.getYamlValue("switch_url");
		mailtext = mailtext
				+ "<br><b>Browser: </b>"
				+ getProperty("./Config.properties", "browser");
		mailtext = mailtext
				+ "<br><b>Instructor Account: </b>"
				+ YamlReader.getYamlValue("users.instructor.username");
		mailtext = mailtext
				+ "<br><b>Student Account: </b>"
				+ YamlReader.getYamlValue("users.student.username");		
		mailtext = mailtext
				+ "<br><br>Please find detailed test results in the attached <i>emailable-report.html</i>";
		mailtext = mailtext + "<br><br>--<br>Best Regards";
		mailtext = mailtext + "<br>"+"Automation QA - CNOWv7";

		return mailtext;
	}

	private String setMailSubject() {
		String server=YamlReader.getYamlValue("switch_url").split(".ilrn")[0].split(":")[1].substring(2).toUpperCase();
		return  "CNOWv7 ["+server+"]: Core Smoke Automated Test Results - "+ today;
			
		
	}

	private void setMailRecipient(Message message) throws AddressException,	MessagingException, IOException {
					
	if(YamlReader.getYamlValue("team").equalsIgnoreCase("Auto")){
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(YamlReader.getYamlValue("email.recipient6")));
		message.addRecipient(Message.RecipientType.BCC, new InternetAddress(YamlReader.getYamlValue("email.recipient7")));
		message.addRecipient(Message.RecipientType.BCC, new InternetAddress(YamlReader.getYamlValue("email.recipient8")));
	
	}
	
	if(YamlReader.getYamlValue("team").equalsIgnoreCase("Client")){
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(YamlReader.getYamlValue("email.recipient1")));
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(YamlReader.getYamlValue("email.recipient2")));
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(YamlReader.getYamlValue("email.recipient3")));
			message.addRecipient(Message.RecipientType.BCC, new InternetAddress(YamlReader.getYamlValue("email.recipient6")));
			message.addRecipient(Message.RecipientType.BCC, new InternetAddress(YamlReader.getYamlValue("email.recipient7")));
			message.addRecipient(Message.RecipientType.BCC, new InternetAddress(YamlReader.getYamlValue("email.recipient8")));
			message.addRecipient(Message.RecipientType.BCC, new InternetAddress(YamlReader.getYamlValue("email.recipient8")));

	}
	if(YamlReader.getYamlValue("team").equalsIgnoreCase("Release")){
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(YamlReader.getYamlValue("email.recipient4")));
		message.addRecipient(Message.RecipientType.CC, new InternetAddress(YamlReader.getYamlValue("email.recipient5")));
		message.addRecipient(Message.RecipientType.CC, new InternetAddress(YamlReader.getYamlValue("email.recipient7")));
		message.addRecipient(Message.RecipientType.CC, new InternetAddress(YamlReader.getYamlValue("email.recipient8")));

		}
     }

	private Multipart setAttachment() throws MessagingException, IOException {
		// Create the message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();

		// Fill the message
		messageBodyPart.setContent(setBodyText(), "text/html");

		MimeMultipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// Part two is attachment
		messageBodyPart = new MimeBodyPart();
		String selectReport = getTestName();
		if (selectReport.contains("TestNg.xml") || selectReport.contains("Runner")) {
			addAttachment(multipart, messageBodyPart,
					"./target/surefire-reports/emailable-report.html");
		} else {
			addAttachment(multipart, messageBodyPart,
					"./target/surefire-reports/emailable-report.html");
		}
		return multipart;
	}

	private static void addAttachment(Multipart multipart,
			MimeBodyPart messageBodyPart, String filename)
					throws MessagingException {
		messageBodyPart = new MimeBodyPart();
		File f = new File(filename);
		DataSource source = new FileDataSource(f);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(f.getName());
		multipart.addBodyPart(messageBodyPart);
	}

	private String getTestName() {
		String test = System.getProperty("test", "null");
		String testsuite = System.getProperty("testsuite", "null");
		String testName;
		if (test != "null") {
			testName = test + " was executed";
			return testName;
		} else if (testsuite != "null") {
			testName = testsuite + "were executed";
			return testName;
		} else {
			testName = "CNOW Smoke Test Suite";
			return testName;
		}
	}

	@SuppressWarnings("unused")
	private String getTextFile() {
		String textFile = "";
		File folder = new File("./target/surefire-reports/");
		String[] fileNames = folder.list();
		int total = 0;
		for (int i = 0; i < fileNames.length; i++) {
			if (fileNames[i].contains(".txt")) {
				total++;
				assert total == 1;
				textFile = fileNames[i];
				System.out.println("Text File Path: "+textFile);
				return textFile;
			}
		}
		return textFile;
	}


	@SuppressWarnings("unused")
	private String checkFilePresent() {
		File folder = new File("./target/surefire-reports");
		String[] fileNames = folder.list();
		for (int i = 0; i < fileNames.length; i++) {
			if (fileNames[i].contains("TEST-TestSuite")) {
				return "./target/surefire-reports/" + fileNames[i];
			} else if (fileNames[i].contains("TEST-com")) {
				return "./target/surefire-reports/" + fileNames[i];
			}
		}
		return "";
	}

	private List<String> printFailedTestInformation(){
		String filepath = "./target/surefire-reports/testng-results.xml";
		File file=new File(filepath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document dom = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			dom = dBuilder.parse(file);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> list= identifyTagsAndTraverseThroguhElements(dom);
		System.out.println("Number of Failed Test Cases:- "+count);
		return list;
	}



	private List<String> identifyTagsAndTraverseThroguhElements(Document dom){

		List<String> list=new ArrayList<String>();

		NodeList nodes = dom.getElementsByTagName("test-method");
		try{
			NodeList nodesMessage= dom.getElementsByTagName("full-stacktrace");
			for(int i=0,j=0;i<nodes.getLength() && j<nodesMessage.getLength();i++){

				Element ele1 = (Element) nodes.item(i);
				Element ele2 = (Element) nodesMessage.item(j);

				if(ele1.getAttribute("status").equalsIgnoreCase("FAIL")){
					count++;
					String[] testMethodResonOfFailure= getNameTestReason(ele1, ele2);
					list.add(testMethodResonOfFailure[0]);
					list.add(testMethodResonOfFailure[1]);
					list.add(testMethodResonOfFailure[2]);

					j++;
				}
			}
		}catch(Exception e){
			System.out.println("No Failures");
			Reporter.log("No Failures!!");
		}
		return list;

	}

	private String[] getNameTestReason(Element el1, Element el2){
		String[] returnNameTestReason= new String[3];
		NamedNodeMap name= el1.getParentNode().getParentNode().getAttributes();

		returnNameTestReason[0]=name.getNamedItem("name").toString().replaceAll("name=","");
		returnNameTestReason[1]=el1.getAttribute("name");
		returnNameTestReason[2]=el2.getTextContent();
		return returnNameTestReason;

	}
}