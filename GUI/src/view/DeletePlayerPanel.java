package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.DeletePlayerButtonListener;
import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class DeletePlayerPanel extends JPanel {
	private JTextField textFieldPlayerId;
	private GameEngine gameEngine;
	private JTextArea textArea;
	private StatusBar statusBar;
	public DeletePlayerPanel(GameEngine gameEngine,JTextArea textArea,StatusBar statusBar) {
		this.gameEngine=gameEngine;
		this.textArea=textArea;
		this.statusBar=statusBar;
		JLabel labelPlayerId = new JLabel("Player Id");
		labelPlayerId.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(labelPlayerId);
		
		textFieldPlayerId = new JTextField();
		textFieldPlayerId.setToolTipText("Enter player id here");
		textFieldPlayerId.setColumns(10);
		add(textFieldPlayerId);
		
		JButton buttonDeletePlayer = new JButton("Delete Player");

		buttonDeletePlayer.addActionListener(new DeletePlayerButtonListener(this.gameEngine,textFieldPlayerId,this.textArea,this.statusBar));
		
		buttonDeletePlayer.setFont(new Font("Tahoma", Font.BOLD, 26));
		buttonDeletePlayer.setBackground(new Color(34, 139, 34));
		add(buttonDeletePlayer);
		
	}

}
