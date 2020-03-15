package com.gojek.Model;

public class Car {
	String registrationNo;
	String colorOfTheCar;
	int slotNumber;

	public Car(String registrationNo, String colorOfTheCar, int slotNumber) {
		super();
		this.registrationNo = registrationNo;
		this.colorOfTheCar = colorOfTheCar;
		this.slotNumber = slotNumber;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getColorOfTheCar() {
		return colorOfTheCar;
	}

	public void setColorOfTheCar(String colorOfTheCar) {
		this.colorOfTheCar = colorOfTheCar;
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	@Override
	public String toString() {
		return "Car [registrationNo=" + registrationNo + ", colorOfTheCar=" + colorOfTheCar + ", slotNumber="
				+ slotNumber + "]";
	}

}
