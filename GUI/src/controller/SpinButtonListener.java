package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import view.StatusBar;

public class SpinButtonListener implements ActionListener {
	private GameEngine gameEngine;
	private StatusBar statusBar;
	private JButton btnOpenWheelPage;
	public SpinButtonListener(GameEngine gameEngine,StatusBar statusBar,JButton btnOpenWheelPage) {
		this.gameEngine=gameEngine;
		this.statusBar=statusBar;
		this.btnOpenWheelPage=btnOpenWheelPage;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			btnOpenWheelPage.doClick();
			new Thread()
			{
				@Override
				public void run()
				{
					gameEngine.spin(1, 500, 25);
				}
			}.start();
			statusBar.wheelSpinning();
		}catch(Exception sE) {
			
			sE.printStackTrace();
			JOptionPane.showMessageDialog(null, sE.getMessage()+"All bets are not Placed", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
	

}
