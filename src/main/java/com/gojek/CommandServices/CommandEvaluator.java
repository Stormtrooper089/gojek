package com.gojek.CommandServices;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.gojek.ParkingLot.ParkingLot;
public class CommandEvaluator {

	private static Map<String, BiConsumer<String, String>> map = new HashMap<>();

    static {
        map.put("create_parking_lot", ParkingLot.CREATE_PARKING_LOT);
        map.put("park" ,ParkingLot.PARK);
        map.put("leave" , ParkingLot.LEAVE);
        map.put("status",ParkingLot.STATUS);
        map.put("slot_numbers_for_cars_with_colour",ParkingLot.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR);
        map.put("slot_number_for_registration_number",ParkingLot.SLOT_NUMBER_FOR_REGISTRATION_NUMBER);
        map.put("registration_numbers_for_cars_with_colour",ParkingLot.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR);
    }

    public static void consume(String input) {
        String [] arr = input.split(" ");
        //System.out.println( " the command entered is  "  +  input);
        String command = arr.length > 0 ? arr[0] : "";
        String firstArg = arr.length > 1 ? arr[1] : "";
        String secondArg = arr.length > 2 ? arr[2] : "";

        map.getOrDefault(command, ParkingLot.DEFAULT_).accept(firstArg,secondArg);
    }
	
}
