package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controller.PlaceBetButtonListener;
import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class PlaceBetPanel extends JPanel {
	private JTextField textFieldPlayerId;
	private JTextField textFieldBetAmounts;
	private JList<String> list;
	private GameEngine gameEngine;
	private JTextArea textArea;
	private StatusBar statusBar;
	public PlaceBetPanel(GameEngine gameEngine,JTextArea textArea,StatusBar statusBar,JButton btnOpenWheelPage) {
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
		
		JLabel labelBetAmount = new JLabel("Bet Amount");
		labelBetAmount.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(labelBetAmount);
		
		textFieldBetAmounts = new JTextField();
		textFieldBetAmounts.setColumns(10);
		add(textFieldBetAmounts);
		
		JLabel labelBetType = new JLabel("Select Bet Type");
		labelBetType.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(labelBetType);
		
		list = new JList<String>();
		list.setBorder(new LineBorder(Color.GRAY, 1, true));
		list.setModel(new AbstractListModel<String>() {
			String[] values = new String[] {"RED", "Black","Zeros"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		add(list);
		
		JButton buttonPlaceBetForm = new JButton("Place Bet");

		buttonPlaceBetForm.addActionListener(new PlaceBetButtonListener(this.gameEngine,textFieldPlayerId,textFieldBetAmounts,list,this.textArea,this.statusBar,btnOpenWheelPage));
		
		buttonPlaceBetForm.setFont(new Font("Tahoma", Font.BOLD, 26));
		buttonPlaceBetForm.setBackground(new Color(34, 139, 34));
		add(buttonPlaceBetForm);
		
	}

}
