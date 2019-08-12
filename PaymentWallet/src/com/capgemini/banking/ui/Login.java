package com.capgemini.banking.ui;

import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Login extends JFrame {

	private JPanel loginPanel;
	private JLabel AccNumJLbl;
	private JTextField AccNumJTF;
	
	private JLabel PinJLbl;
	private JTextField PinJTF;
	
	private JButton LoginJBtn;
	private JButton CreateAccJBtn;
	
	
	public Login(){
		createLogin();
	}
	
	private void createLogin(){
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		loginPanel = new JPanel();
		loginPanel.setBounds(15, 15, 350,200 );
		
		TitledBorder centerBorder = BorderFactory.createTitledBorder("Login");
	    centerBorder.setTitleJustification(TitledBorder.CENTER);
		
		loginPanel.setBorder(centerBorder);
		loginPanel.setLayout(null);
		contentPane.add(loginPanel);
		contentPane.setVisible(true);
		
		AccNumJLbl = new JLabel();
		AccNumJLbl.setText("Account Number :");
		AccNumJLbl.setBounds(40,40,150,20);
		loginPanel.add(AccNumJLbl);
		
		AccNumJTF = new JTextField();
		AccNumJTF.setBounds(160,40,150,20);
		loginPanel.add(AccNumJTF);
		
		setTitle("Wallet");
		setSize(400,280);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Login();
	}
	
}
