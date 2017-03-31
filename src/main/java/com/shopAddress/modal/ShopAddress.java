package com.shopAddress.modal;

public class ShopAddress {
	/**
	 * Modal Class for Shop Address
	 */
	public ShopAddress() {

	}

	public ShopAddress(String shopName, int shopAdrressNumber, int shopAddressPostalCode, double shopLongitude,
			double shopLatitude) {
		this.shopName = shopName;
		this.shopAddressNumber = shopAdrressNumber;
		this.shopAddressPostalCode = shopAddressPostalCode;
		this.shopLongitude = shopLongitude;
		this.shopLatitude = shopLatitude;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public int getShopAddressNumber() {
		return shopAddressNumber;
	}

	public void setShopAddressNumber(int shopAddressNumber) {
		this.shopAddressNumber = shopAddressNumber;
	}

	public int getShopAddressPostalCode() {
		return shopAddressPostalCode;
	}

	public void setShopAddressPostalCode(int shopAddressPostalCode) {
		this.shopAddressPostalCode = shopAddressPostalCode;
	}

	public double getShopLongitude() {
		return shopLongitude;
	}

	public void setShopLongitude(double shopLongitude) {
		this.shopLongitude = shopLongitude;
	}

	public double getShopLatitude() {
		return shopLatitude;
	}

	public void setShopLatitude(double shopLatitude) {
		this.shopLatitude = shopLatitude;
	}

	private String shopName;
	private int shopAddressNumber;
	private int shopAddressPostalCode;
	private double shopLongitude;
	private double shopLatitude;

}
