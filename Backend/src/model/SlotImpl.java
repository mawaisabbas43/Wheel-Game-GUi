package model;

import model.enumeration.Color;
import model.interfaces.Slot;

public class SlotImpl implements Slot {
	private int position;
	private Color color;
	private int number;

	public SlotImpl(int position, Color color, int number) {
		this.position = position;
		this.color = color;
		this.number = number;
	}

	@Override
	public int getPosition() {
		return position;
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public Color getColor() {
		return color;
	}

	/**
	 * @return
	 * 
	 *         <pre>
	 *  A human readable String that lists the values of this WheelSlot instance (see OutputTrace.txt) 
	 * 
	 * <b>NOTE:</b> Must use "proper naming" case i.e. First letter capitalised       
	 * e.g. "Position: 0, Color: Green00, Number: 0" for "00" slot at top of wheel
	 *         </pre>
	 */
	@Override
	public  String toString() {
		return "Position: "+position+", Color: "+color.name()+", Number: "+number;
	}

	@Override
	public boolean equals(Slot slot) {
		if(position==slot.getPosition() && color.equals(slot.getColor()) && number==slot.getNumber())
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean equals(Object slot) {
		return equals( (Slot)slot );//call above equals method after type casting of object to Slot
	}

}
