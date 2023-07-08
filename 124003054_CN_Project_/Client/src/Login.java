import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Login {

	private JFrame frame;
	private JTextField mailid;
	String mi;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Login() {
		initialize();
	}

	
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 640, 416);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		panel_1.setBounds(10, 10, 607, 359);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(357, 0, 249, 359);
		panel_1.add(panel);
		panel.setLayout(null);
		
		mailid = new JTextField();
		mailid.setFont(new Font("Tahoma", Font.PLAIN, 13));
		mailid.setBounds(10, 144, 229, 19);
		panel.add(mailid);
		mailid.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 113, 54, 13);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mi=mailid.getText();
				
					new ProjectClient(mi);
			}
		});
		btnNewButton.setBounds(79, 191, 85, 21);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Login Page");
		lblNewLabel_1.setBounds(61, 35, 87, 21);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 5));
		lblNewLabel_2.setBounds(0, 0, 606, 359);
		panel_1.add(lblNewLabel_2);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Shanmugha_Arts,_Science,_Technology_&_Research_Academy_logo.png"));
		lblNewLabel_2.setIcon(icon);
		
		
	}
}
