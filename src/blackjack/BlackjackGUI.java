package blackjack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BlackjackGUI implements MouseMotionListener {
	// window for the GUI
	JFrame frame = new JFrame("Blackjack");
	JPanel panel = new JPanel();
	JTextArea enterBet = new JTextArea();
	JButton enterBetBtn = new JButton("Enter Bet");
	JButton hitBtn = new JButton("Hit");
	JButton standBtn = new JButton("Stand");
	Font font = new Font("Georgia", Font.PLAIN, 24);
	JLabel background = new JLabel();
	Image backgroundImg = Toolkit.getDefaultToolkit().getImage(BlackjackGUI.class.getResource("/background.png"));
	
	public BlackjackGUI() {
		initialize();
		frame.setVisible(true);
	}
	
	private void initialize() {
		frame.setBounds(150, 30, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel.setBounds(0, 0, 1200, 800);
		frame.getContentPane().add(panel);
		panel.addMouseMotionListener(this);
		panel.setLayout(null);
		
		
		setBet();
		setHitBtn();
		setStandBtn();
		setBackground();
	}
	
	private void setBackground() {
		backgroundImg = backgroundImg.getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
		background.setIcon(new ImageIcon(backgroundImg));
		background.setBounds(0, 0, 1200, 800);
		panel.add(background);
	}
	
	private void setHitBtn() {
		hitBtn.setBackground(Color.LIGHT_GRAY);
		hitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		hitBtn.addMouseMotionListener(this);
		hitBtn.setBounds(357, 643, 142, 51);
		panel.add(hitBtn);
	}
	
	private void setStandBtn() {
		standBtn.setBackground(Color.LIGHT_GRAY);
		standBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		standBtn.addMouseMotionListener(this);
		standBtn.setBounds(559, 643, 142, 51);
		panel.add(standBtn);
	}
	
	private void setBet() {
		enterBet.setEditable(true);
		enterBet.setWrapStyleWord(true);
		enterBet.setBounds(61, 534, 152, 51);
		panel.add(enterBet);
		enterBet.setLineWrap(true);
		enterBet.setFont(font);
		
		enterBetBtn.setBackground(Color.LIGHT_GRAY);
		enterBetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		enterBetBtn.addMouseMotionListener(this);
		enterBetBtn.setBounds(61, 614, 152, 51);
		panel.add(enterBetBtn);
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		
	}
	
	public static void main(String[] args) {
		try {
			BlackjackGUI game = new BlackjackGUI();
		}
		catch (Exception ex) {;}
	}

}
