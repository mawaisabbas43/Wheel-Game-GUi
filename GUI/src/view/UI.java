package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextArea;
import controller.PanelButtonListener;
import controller.SpinButtonListener;
import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class UI extends JFrame {

	private JPanel contentPane;
	private GameEngine gameEngine;
	private JTextArea textArea;
	private StatusBar statusBar;
	private ImagePanel imagePanel;
	private JButton btnOpenWheelPage;


	public UI(GameEngine gameEngine) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 850);
		
		this.gameEngine=gameEngine;
		
//=============================Menu Bar===========================================		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenuItem mntmAddNewPlayer = new JMenuItem("Add New Player");
		mnOptions.add(mntmAddNewPlayer);
		
		JMenuItem mntmOpenWheel = new JMenuItem("Open Wheel Page");
		mnOptions.add(mntmOpenWheel);
		
		JMenuItem menuItemPlaceBet = new JMenuItem("Place Bet");
		mnOptions.add(menuItemPlaceBet);
//========================content Panel=======================================
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
//======================Tool Bar===============================================
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnSpin = new JButton("Spin");
		btnSpin.setFont(new Font("Monotype Corsiva", Font.BOLD, 16));
		toolBar.add(btnSpin);
//		btnSpin.addActionListener(new SpinButtonListener(gameEngine,statusBar,btnOpenWheelPage));//Implemented at line 159
		
		btnOpenWheelPage = new JButton("Open Wheel Page");
		btnOpenWheelPage.setBackground(new Color(128, 128, 128));
		btnOpenWheelPage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnOpenWheelPage.setToolTipText("Open Game Wheel main page");
		toolBar.add(btnOpenWheelPage);
		
		JButton btnAddNewPlayer = new JButton("Add New Player");
		btnAddNewPlayer.setToolTipText("Click Here to Enter Details of new Player");
		btnAddNewPlayer.setBackground(Color.PINK);
		btnAddNewPlayer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(btnAddNewPlayer);
		
		JButton buttonDeletePlayer = new JButton("Delete Player");
		buttonDeletePlayer.setToolTipText("Click Here to delete Player");
		buttonDeletePlayer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonDeletePlayer.setBackground(new Color(139, 69, 19));
		toolBar.add(buttonDeletePlayer);
		
		JButton buttonPlaceBet = new JButton("Place Bet");
		buttonPlaceBet.setToolTipText("Open page to place bet");
		buttonPlaceBet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonPlaceBet.setBackground(new Color(255, 0, 255));
		toolBar.add(buttonPlaceBet);
		
//=====================Text Area Settings=============================================================
		JScrollPane jScrollPane = new JScrollPane();
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setColumns(25);
		textArea.setText("");
		jScrollPane.setViewportView(textArea);
		contentPane.add(jScrollPane, BorderLayout.EAST);
//====================================================================================================

//=======================Panels Management==============================================
		JPanel jPanelMain = new JPanel();
		jPanelMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		jPanelMain.setLayout(new BorderLayout(0, 0));
		
		statusBar=new StatusBar();
		AddPlayerPanel panelAddPlayer = new AddPlayerPanel(this.gameEngine,textArea,statusBar);
		imagePanel=new ImagePanel();
		PlaceBetPanel panelPlaceBet = new PlaceBetPanel(this.gameEngine,textArea,statusBar,btnOpenWheelPage);
		contentPane.add(jPanelMain, BorderLayout.CENTER);
		contentPane.add(statusBar, BorderLayout.SOUTH);
		
		DeletePlayerPanel deletePlayerPanel = new DeletePlayerPanel(this.gameEngine,textArea,statusBar);
		jPanelMain.add(imagePanel, BorderLayout.CENTER);//Default Panel

//=====================Actions for MenuItem and Toll bar Buttons======================================
		btnAddNewPlayer.addActionListener(new PanelButtonListener(jPanelMain,panelAddPlayer));
		mntmAddNewPlayer.addActionListener(new PanelButtonListener(jPanelMain,panelAddPlayer));
		
		
		btnOpenWheelPage.addActionListener(new PanelButtonListener(jPanelMain,imagePanel));
		mntmOpenWheel.addActionListener(new PanelButtonListener(jPanelMain,imagePanel));
		
		buttonPlaceBet.addActionListener(new PanelButtonListener(jPanelMain,panelPlaceBet));
		menuItemPlaceBet.addActionListener(new PanelButtonListener(jPanelMain,panelPlaceBet));
		
		buttonDeletePlayer.addActionListener(new PanelButtonListener(jPanelMain,deletePlayerPanel));
		
//====================================================================================================
		btnSpin.addActionListener(new SpinButtonListener(gameEngine,statusBar,btnOpenWheelPage));
		
		this.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentResized(ComponentEvent e) {
				Component c = e.getComponent();
				if(c.getHeight()!=c.getWidth()-152) {
					c.setSize(c.getWidth(), c.getWidth()-152);
					repaint();
					revalidate();
				}
				
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public StatusBar getStatusBar() {
		return statusBar;
	}
	
	public ImagePanel getImagePanel() {
		return imagePanel;
	}
	
	public JTextArea getJTextArea() {
		return textArea;
	}
}
