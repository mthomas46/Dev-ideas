import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;     // Used for date formatting.

public class test {
	static Socket smtpSocket;
	static DataOutputStream os;
	static DataInputStream is;
	static String hostName;
	static int hostIPort;
	static String SMPTUserName;
	static String SMPTPass;
	static Date dDate;
	static DateFormat dFormat;
	
	test(){
		 smtpSocket = null;
		 os = null;
		 is = null;
		 hostName="smtp.mandrillapp.com";
		 hostIPort=587;
		 SMPTUserName="mthomas46@gatech.edu";
		 SMPTPass="f232b687-da89-434d-a60b-54229ba564d5";
		 dDate= new Date();
		 dFormat=DateFormat.getDateInstance(DateFormat.FULL,Locale.US);
	}
	
	public void runTest(){
	
	try
	{
		 // Open port to server
		  smtpSocket = new Socket(hostName, hostIPort);
		  os = new DataOutputStream(smtpSocket.getOutputStream());
		  is = new DataInputStream(smtpSocket.getInputStream());
		  
		  System.out.println(smtpSocket.toString());
		  System.out.println(hostName+" "+hostIPort);
		            
		  if(smtpSocket != null && os != null && is != null)
		  {
			  // Connection was made.  Socket is ready for use.

			  try
			  {
			  os.writeBytes("HELO\r\n");
			  System.out.println("HELO\r\n");
			  
			  // You will add the email address that the server 
			  // you are using know you as.
			  os.writeBytes("MAIL From: <mthomas46@gatech.edu>\r\n");
			  System.out.println("MAIL From: <mthomas46@gatech.edu>\r\n");       
			  
			  // Who the email is going to.
			  os.writeBytes("RCPT To: <mthomas46@gatech.edu>\r\n");
			  System.out.println("RCPT To: <mthomas46@gatech.edu>\r\n");
			  
			  // Now we are ready to add the message and the 
			  // header of the email to be sent out.                
			  os.writeBytes("DATA\r\n");
			  System.out.println("DATA\r\n");
			  
			  os.writeBytes("X-Mailer: Via Java\r\n");
			  System.out.println("X-Mailer: Via Java\r\n");
			  os.writeBytes("DATE: " + dFormat.format(dDate) + "\r\n");
			  System.out.println("DATE: " + dFormat.format(dDate) + "\r\n");
			    
			  os.writeBytes("From: Me<mthomas46@gatech.edu>\r\n");
			  os.writeBytes("To:  YOU<mthomas46@gatech.edu>\r\n");
			    
			  String sMessage = "this is just a test";

			  os.writeBytes("Subject: Your subjectline here\r\n");
			  os.writeBytes(sMessage + "\r\n");
			  os.writeBytes("\r\n.\r\n");
			  os.writeBytes("QUIT\r\n");
			    
			  // Now send the email off and check the server reply.  
			  // Was an OK is reached you are complete.
			  @SuppressWarnings("deprecation")
			String responseline=is.readLine();
			  while(responseline!=null)
			  {  System.out.println(responseline);
			        if(responseline.indexOf("Ok") != -1)
			            break;
			  }
			  }
			  catch(Exception e)
			  {  
				  System.out.println("Cannot send email as an error occurred.");
			  }  
		  }
		  }
	catch(Exception e)
	{
		System.out.println("Host " + hostName + "unknown");
	}
	}

	public static void main(String[]args){
		test jack=new test();
		jack.runTest();
	}
}

