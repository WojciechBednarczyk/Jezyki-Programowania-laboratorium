package lab02;

import java.util.ArrayList;
import java.util.List;

public class Pizza {

	@Override
	public String toString() {
		if (customersList.size() > amountOfPizzas * 8)
			return amountOfPizzas + "; " + topingsList.get(0) + ", " + topingsList.get(1) + ", " + topingsList.get(2)
					+ "; " + customersList.subList(0, amountOfPizzas * 8);
		else
			return amountOfPizzas + "; " + topingsList.get(0) + ", " + topingsList.get(1) + ", " + topingsList.get(2)
					+ "; " + customersList;
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

	public void setTopingsList(List<Integer> topingsList) {
		this.topingsList = topingsList;
	}

	public void addCustomer(int number) {
		customersList.add(number);
	}

	public int getAmountOfSlices() {
		return amountOfSlices;
	}

	public void setAmountOfSlices(int amountOfSlices) {
		this.amountOfSlices = amountOfSlices;
	}

	public void addOrderID(int orderID) {
		orderIDList.add(orderID);
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getAmountOfPizzas() {
		return amountOfPizzas;
	}

	public void makePizza(int pizza) {
		this.amountOfPizzas = pizza;
	}

	public List<Integer> getOrderIDList() {
		return orderIDList;
	}

	private int number;
	private int amountOfSlices;
	private int orderID;
	private int amountOfPizzas = 0;
	private List<Integer> orderIDList = new ArrayList<>();
	private List<Integer> topingsList = new ArrayList<>();
	private List<Integer> customersList = new ArrayList<>();
}
