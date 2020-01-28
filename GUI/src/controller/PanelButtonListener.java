package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class PanelButtonListener implements ActionListener {
	
	private JPanel main;
	private JPanel child;
	public PanelButtonListener(JPanel main,JPanel child) {
		this.main=main;
		this.child=child;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		main.removeAll();
        main.repaint();
        main.revalidate();
        
        main.add(child, BorderLayout.CENTER);
        main.repaint();
        main.revalidate();
		
	}

}
