package lab02;

import java.util.ArrayList;
import java.util.List;

class Order {
	@Override
	public String toString() {
		return number + "; " + topingsList.get(0) + ", " + topingsList.get(1) + ", " + topingsList.get(2);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Integer> getTopingsList() {
		return topingsList;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	private int number;
	private int orderID;
	private List<Integer> topingsList = new ArrayList<Integer>();

}
