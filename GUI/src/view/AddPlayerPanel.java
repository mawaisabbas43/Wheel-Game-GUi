package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.AddPlayerButtonListener;
import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class AddPlayerPanel extends JPanel {

	private JTextField textFieldPlayerName;
	private JTextField textFieldInitialPoints;
	private GameEngine gameEngine;
	private JTextArea textArea;
	private StatusBar statusBar;
	
	public AddPlayerPanel(GameEngine gameEngine,JTextArea textArea,StatusBar statusBar) {
		this.gameEngine=gameEngine;
		this.textArea=textArea;
		this.statusBar=statusBar;
		textFieldPlayerName = new JTextField();
		textFieldPlayerName.setToolTipText("Enter player name here");
		textFieldPlayerName.setColumns(10);
		JLabel labelInitialPoint = new JLabel("Initial Points");
		labelInitialPoint.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textFieldInitialPoints = new JTextField();
		textFieldInitialPoints.setColumns(10);
		setLayout(new GridLayout(15, 0, 10, 10));
		
		JLabel lblPlayerName = new JLabel("Player Name");
		lblPlayerName.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblPlayerName);
		add(textFieldPlayerName);
		add(labelInitialPoint);
		add(textFieldInitialPoints);
		
		JButton btnAddPlayer = new JButton("Add Player");
		
		btnAddPlayer.addActionListener(new AddPlayerButtonListener(this.gameEngine,textFieldPlayerName,textFieldInitialPoints,this.textArea,this.statusBar));
		
		btnAddPlayer.setBackground(new Color(34, 139, 34));
		btnAddPlayer.setFont(new Font("Tahoma", Font.BOLD, 26));
		add(btnAddPlayer);
	}

}
