import java.util.*;
import java.io.*;
import java.net.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

class ProjectServer
{
	private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;
    public ProjectServer() throws IOException
    {
    	//establishes connection and waits for the client
            server = new ServerSocket(1021);
            while(true)
            {
            	System.out.println("Server started");
            	System.out.println("Waiting for a client ...");
            	try
            	{
            		socket = server.accept();
            		System.out.println("Client accepted");
            		//attaching input stream with socket
            		in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            		//creating a thread
            		Thread t = new ClientHandler(socket, in);
            		// Invoking the start() method
            		t.start();
            	}
            	catch(IOException i)
            	{
            		System.out.println(i);
            	}
            }
        }
 
    public static void main(String args[]) throws IOException
    {
        new ProjectServer();
    }
}

class ClientHandler extends Thread
{
	final DataInputStream dis;
    final Socket s;
    public ClientHandler(Socket s, DataInputStream dis) 
    {
    	this.s = s;
    	this.dis = dis;
   
    }
    public void run() 
	{
		String line = "";
        try
        {
        	//reading the message received from client
            line += dis.readUTF();
            System.out.println(line);
        }
        catch   (Exception i) 
        {
            System.out.println(i);
        }
        System.out.println("Closing connection");
    	sendMessage(line);
 
	}
    private void sendMessage(String line)
    { 
	     String arr[] = new String[2];
		 arr[0]="";
		 arr[1]="";
		 int j=0;
		 for(int i=0;i<line.length();i++)
		 {
			 if(line.charAt(i)!=' ')
			 {
				 arr[j]=arr[j]+line.charAt(i);
			 }
			 else
			 {
				 j=j+1;
			 }
			 
		 }
		 System.out.println(arr[1]);
		 String recipient = arr[0];
		 String message1 = "C:\\Users\\user\\eclipse-workspace\\124003054_CN_Project_\\Server\\src\\" + arr[1];
		 String BookName=arr[1];
		 try {
	     File file = new File(message1);
		 FileInputStream fi = new FileInputStream(file);
		 byte []b = new byte[(int)file.length()];
		 fi.read(b,0,b.length);
		 OutputStream os = s.getOutputStream();
		 os.write(b,0,b.length);
		 fi.close();}catch(Exception e) {System.out.println("\nException : "+e+"\n");}
		 
		 new ServerGUI(recipient,message1,BookName);		
	}

}
class ServerGUI
{
	
	String sender="";
	String password="";
	String message1="";
	String recipient="";
	String BookName="";
	ServerGUI(String x, String y,String z)
	{
		message1=y;
		recipient=x;
		BookName=z.substring(0, z.length()-4);
		
		String MailBody="Good morning,\n"
				+ "\tYou received the book "+"\""+BookName +"\""+" from Book Library 😊\n\n"+
				"Thank you🤗,\nVisit again🙏";
		
	    	sender="124003054@sastra.ac.in";
	    	password="bhaja@1667";
	    	
	    	// Getting system properties
			 Properties properties = new Properties();
			 properties.put("mail.smtp.auth", "true");
			 properties.put("mail.smtp.starttls.enable", "true");
			 properties.put("mail.smtp.host","smtp.gmail.com");
			 properties.put("mail.smtp.port", "587");
			 
			  // Setting up mail server
			 // creating session object to get properties
			  Session session = Session.getInstance(properties,new Authenticator() {
		  	  @Override
		  	  protected PasswordAuthentication getPasswordAuthentication() {
		  	  return new PasswordAuthentication(sender,password);
		  	  }
			 });
		
		      try
		      {
			      MimeMessage message = new MimeMessage(session);
			      // Set From Field: adding senders email to from field.
			      message.setFrom(new InternetAddress(sender));
			      // Set To Field: adding recipient's email to from field.
			      message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			      // Set Subject: subject of the email
			      message.setSubject("BOOKS LIBRARY");
			      // set body of the email.
			      message.setText(MailBody);
			      // Send email.
			      Transport.send(message);
			      System.out.println("Mail successfully sent");
		      }
		    catch (MessagingException mex)
		    {
		    	mex.printStackTrace();
		    }	
	}
}