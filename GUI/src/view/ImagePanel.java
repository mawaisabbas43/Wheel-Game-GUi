package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

	int x=0,y=10,valx=0,valy=10,x2=0;
	int i=0;
	
	public ImagePanel() {
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage img=loadImage("Basic_roulette_wheel_1024x1024.png");
		Graphics2D g2d=(Graphics2D)g;
		double r;
		double theta=Math.toRadians((9.473*i)+3.5);
		double a=getWidth()/2.0,b=getHeight()/2.0;
		g2d.drawImage(img,0,0,getHeight(),getWidth(),null);
		r=(getHeight()/2.0)-13;
		x=(int) ((a)+ (r*Math.cos(theta) ));
		y=(int) (b+ (r*Math.sin(theta) ));
		Ellipse2D.Double circle=new Ellipse2D.Double(x, y, 10, 10);
		
		g2d.setColor(new Color(255,255,0));
		g2d.fill(circle);
	}
	
	private BufferedImage loadImage(String filename) {
		BufferedImage img=null;
		try {
			img=ImageIO.read(new File(filename));
			
		}catch(Exception e) {
			System.out.println("Catch!");
			JOptionPane.showMessageDialog( null,e, "Error", JOptionPane.ERROR_MESSAGE);
		}
		return img;
	}
	
	public void move(int i){
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				rotate(i);
			}
		});
	}
	
	private void rotate(int i) {
		this.i=i-10;
		repaint();
		revalidate();
	}

}
