import java.io.IOException;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*; 
@SuppressWarnings("serial")
class ProjectClient extends JFrame implements ActionListener
{ 
  String emailMy="";
  JFrame f;
  JPanel panel;
  JPanel panel_1;
  JLabel lblNewLabel_2;
  JButton btnNewButton;
  JComboBox comboBox;
  JButton btnNewButton_1;
  
  
  private Socket socket;
  private DataOutputStream out;
  String line="";
  private JLabel lblNewLabel;
 
  public ProjectClient(String mi)
  {
	  emailMy=mi;
    try
        {
    	 	//creating socket to initiate communication
            socket = new Socket(InetAddress.getByName("127.0.0.1"),1021);
            System.out.println("Connected");
            //attaching output stream with socket
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
     
     f = new JFrame();
		f.getContentPane().setBackground(SystemColor.desktop);
		f.getContentPane().setLayout(null);
		f.setVisible(true);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(10, 10, 744, 473);
		f.getContentPane().add(panel);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(0, 0, 570, 473);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel_2 = new JLabel("Book Library");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(240, 24, 125, 25);
		panel_1.add(lblNewLabel_2);
		
		btnNewButton = new JButton("Exit");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(394, 375, 85, 21);
		btnNewButton.addActionListener(this);
		panel_1.add(btnNewButton);
		
		comboBox = new JComboBox();
		comboBox.setMaximumRowCount(10);
		comboBox.setBounds(45, 195, 265, 36);
		comboBox.addActionListener(this);
		for(int i=1;i<=15;i++)
			comboBox.addItem("Book"+i+".txt");
		panel_1.add(comboBox);
		
		
		btnNewButton_1 = new JButton("DOWNLOAD");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBounds(370, 187, 137, 51);
		btnNewButton_1.addActionListener(this);
		panel_1.add(btnNewButton_1);
		//ImageIcon icon = new ImageIcon(this.getClass().getResource("/Shanmugha_Arts,_Science,_Technology_&_Research_Academy_logo.png"));
		
		f.setBounds(100, 100, 778, 530);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  public void actionPerformed(ActionEvent ae) 
  {
    if(ae.getActionCommand().equals("DOWNLOAD"))
    {
    	line=emailMy;
    	String bookName = (String)comboBox.getSelectedItem();
    		 line=line+" "+comboBox.getSelectedItem();//obj[i].toStr();
    		 System.out.print(line);
    
    	try
    	 {
    	       //sending details to client                        
    	       out.writeUTF(line);
    	       
    	       InputStream is = socket.getInputStream();
    	      
   // File file = new File("C:\\Users\\user\\eclipse-workspace\\124003054_CN_Project_\\Client\\src\\"+bookName);
    	  File file = new File("C:\\Users\\user\\Downloads\\"+bookName);
 	     
    /*	    FileWriter fw = new FileWriter(bookName);*/
    	      FileOutputStream fout = new FileOutputStream(file);    	      
    	       byte []b = new byte[2000];
    	       is.read(b,0,b.length);
    	       String sks = new String(b);

    	       System.out.println(sks);
    	       fout.write(b);
    	       
    	       JOptionPane.showMessageDialog(this,"Book Details have been sent to your mail\nThank you!! ");  
    	 }
    	 catch(IOException e)
    	 {
    	       System.out.println(e);
    	 }
    }if(ae.getActionCommand().equals("Exit")) {
    	//f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
    	f.setVisible(false);
    }
  }
}