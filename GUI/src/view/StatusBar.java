package view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


@SuppressWarnings("serial")
public class StatusBar extends JPanel
{
	private JLabel status1;
	private JLabel status2;
	private JLabel status3;
	private int betCount;
	public StatusBar()
	{
		status1 = new JLabel("Not Spinning", SwingConstants.LEFT);
		status2 = new JLabel("Total Players: 0", SwingConstants.CENTER);
		status3 = new JLabel("Bet Placed: 0", SwingConstants.RIGHT);
		Border border = BorderFactory.createRaisedBevelBorder();
		status1.setBorder(border);
		status2.setBorder(border);
		status3.setBorder(border);

		setLayout(new GridLayout(1, 3));
		// add three labels
		add(status1);
		add(status2);
		add(status3);

	}

	public void wheelSpinning()
	{
		// isEDT can be used to check whether the current thread is the EDT
		// System.out.println(SwingUtilities.isEventDispatchThread());

		// updates to the GUI !!MUST!! be done on the EDT
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				setSpinningStatus();
			}
		});
	}

	private void setSpinningStatus()
	{
		status1.setText( "Wheel Spinning...");
	}
	
	public void wheelStopped() {
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				setStoppedSpinningStatus();
			}
		});
	}
	
	private void setStoppedSpinningStatus() {
		status1.setText( "Wheel Stopped!");
	}

	public void setPlayersCount(int count)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				setPlayerStatus(count);
			}
		});
	}
	
	private void setPlayerStatus(int count) {
		status2.setText("Total Players: "+count);
	}
	
	public void setBetCount(int count)
	{
		betCount=count;
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				setBetStatus(count);
			}
		});
	}
	
	private void setBetStatus(int count) {
		status3.setText("Bet Placed: "+count);
	}
	
	public int getBetCount() {
		return betCount;
	}

}
