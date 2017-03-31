package com.shopAddress.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.LocationType;
import com.shopAddress.constants.Constants;
import com.shopAddress.exception.InvalidShopRequestException;
import com.shopAddress.exception.LatLngNotFoundException;
import com.shopAddress.exception.ShopDetailsNotFoundException;
import com.shopAddress.modal.ShopAddress;

@RestController
/**
 * Retail Manager Controller
 * 
 * @author grover_n
 *
 */
public class RetailManagerController {

	static ArrayList<ShopAddress> shopAddressList = new ArrayList<ShopAddress>();
	final static Logger logger = Logger.getLogger(RetailManagerController.class);

	/**
	 * Service Mthod to get the Nearest Shop based on Shop Latitude and
	 * Longitude provided
	 * 
	 * @param shopLatitude
	 * @param shopLongitude
	 * @param httpResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/nearestShopAddress", method = RequestMethod.GET)
	public ShopAddress getNearestShopAddress(@RequestParam(value = "shopLatitude") String shopLatitude,
			@RequestParam(value = "shopLongitude") String shopLongitude, HttpServletResponse httpResponse)
			throws Exception {
		logger.debug("Starting Method getNearestShopAddress");
		GeoApiContext context = new GeoApiContext().setApiKey(Constants.API_KEY);
		if ("" == shopLatitude || "" == shopLongitude) {
			throw new LatLngNotFoundException();
		}
		double shopLatitudeVal = new Double(shopLatitude).doubleValue();
		double shopLongitutudeVal = new Double(shopLongitude).doubleValue();
		//Call Geocode API to get the Nearest Shop Address
		GeocodingResult[] results = GeocodingApi.newRequest(context)
				.latlng(new LatLng(shopLatitudeVal, shopLongitutudeVal)).locationType(LocationType.ROOFTOP)
				.resultType(AddressType.STREET_ADDRESS).await();

		if (null == results[0].formattedAddress) { //If Formatted Address is Null
			throw new InvalidShopRequestException();
		}

		String[] addressTokens = results[0].formattedAddress.split("\\s");
		String shopName = "";
		if(null == addressTokens || addressTokens != null && addressTokens.length<3){
			throw new InvalidShopRequestException();
		}
		
		//Tokenizing the Formatted Address to get Shop Number, Postal Code and Shop Name
		for (int x = 0; x < addressTokens.length; x++) {
			if (x != 0 && (x < addressTokens.length - 2)) {
				shopName = shopName + addressTokens[x];
			}

		}
		logger.debug("Ending Method getNearestShopAddress");
		return new ShopAddress(shopName, new Integer(addressTokens[0]).intValue(),
				new Integer(addressTokens[addressTokens.length - 2].substring(0,
						addressTokens[addressTokens.length - 2].length() - 1)).intValue(),
				shopLatitudeVal, shopLongitutudeVal);

	}

	/**
	 * Save the Shop Address Details in an In Memory Shop Address List
	 * 
	 * @param shopAddress
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveShopAddress", method = RequestMethod.POST)
	public ShopAddress saveShopAddress(@RequestBody ShopAddress shopAddress) throws Exception {
		logger.debug("Starting Method saveShopAddress");
		String shopName = shopAddress.getShopName();
		String shopAddressNumber = new Integer(shopAddress.getShopAddressNumber()).toString();
		String shopAddressPostalCode = new Integer(shopAddress.getShopAddressPostalCode()).toString();
		String fullAddress = null;
		double shopLatitude;
		double shopLongitude;
		// Check if any of the Shop Address Inputs are null
		if ((org.springframework.util.StringUtils.hasLength(shopAddressNumber)) != true
				|| org.springframework.util.StringUtils.hasLength(shopName) != true
				|| org.springframework.util.StringUtils.hasLength(shopAddressPostalCode) != true) {
			throw new ShopDetailsNotFoundException(); //Throw Shop Details Not Found Exception
		} else {
			fullAddress = shopAddressNumber + " " + shopName + " " + shopAddressPostalCode;
		}

		GeoApiContext context = new GeoApiContext().setApiKey(Constants.API_KEY);
		GeocodingResult[] results = GeocodingApi.geocode(context, fullAddress).await(); //Call Geocoding API to get Latitude and Longitude
		if (results[0] == null) {
			throw new InvalidShopRequestException();
		} else {
			shopLatitude = results[0].geometry.location.lat;
			shopLongitude = results[0].geometry.location.lng;
		}

		ShopAddress shopAddressResponse = new ShopAddress(shopName, new Integer(shopAddressNumber).intValue(),
				new Integer(shopAddressPostalCode).intValue(), shopLatitude, shopLongitude);
		shopAddressList.add(shopAddressResponse); //Story Address in In Memory List
		logger.debug("Shop Address List Size"+ shopAddressList.size());
		logger.debug("Ending Method saveShopAddress");
		return shopAddressResponse;

	}

}
