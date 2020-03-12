/**
 * @author Ryan Lin
 */
public class Square {
	private int value; //Value the square holds
	private boolean isInitial; //Whether or not the Square is locked/changeable
	
	//Constructor
	public Square() {
		this.value = 0;
	}
	
	//Accessors
	/**
	 * @return value of the Square
	 */
	public int getValue() {
		return this.value;
	}
	/**
	 * @return if the Square's value can be changed
	 */
	public boolean getIsInitial() {
		return this.isInitial;
	}
	
	//Mutators
	/**
	 * Sets the value of the Square
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	/**
	 * Sets whether or not the Square can be changed
	 * @param isInitial
	 */
	public void setIsInitial(boolean isInitial) {
		this.isInitial = isInitial;
	}
}
