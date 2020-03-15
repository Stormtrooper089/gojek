package com.gojek.ParkingLot;
import com.gojek.Model.Car;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class ParkingLot {

    private Car car;
    int MAX_SIZE = 0;

    private static ArrayList<Integer> reservedSlots = new ArrayList<>();
    private static ArrayList<Integer> unReservedSlots  = new ArrayList<>();
    private static ArrayList<Car> parkingLotDatabase = new ArrayList<>();



    public static BiConsumer<String, String> CREATE_PARKING_LOT = (i, v) -> {
        int size = Integer.parseInt(i);
        if(reservedSlots.size() != 0 ){
            System.out.println("The Parking lot is already created . Please proceed with the functions");
        }else{
            for(int j=1;j <= size ; j++){
                unReservedSlots.add(j);
            }
        }
    };

    public static BiConsumer<String, String> PARK = (i, v) -> {
        Car car = new Car(i,v,0);
        if(unReservedSlots.size() == 0 ){
            System.out.println("There are no empty slots at this moment");
        }else{
            car.setSlotNumber(unReservedSlots.get(0));

            car.setRegistrationNo(i);
            car.setColorOfTheCar(v);
            reservedSlots.add(unReservedSlots.get(0));
            parkingLotDatabase.add(car);

            System.out.println("Allocated slot number: " + unReservedSlots.get(0));
            unReservedSlots.remove(0);
        }
    };

    public static BiConsumer<String, String> LEAVE = (i, v) -> {

        int numbertoRemove = Integer.parseInt(i);
        if( reservedSlots.contains(numbertoRemove)){

            unReservedSlots.add(numbertoRemove);
            unReservedSlots.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return 0;
                }
            });

            Car carToRemove = null;
            for (Car car:
                    parkingLotDatabase) {
                if(car.getSlotNumber() == numbertoRemove){
                    carToRemove = car;
                    break;
                }
            }
            System.out.println("Slot number "+ numbertoRemove +" is free");

            parkingLotDatabase.remove(carToRemove);
            reservedSlots.remove(reservedSlots.indexOf(numbertoRemove));
        }else{
            System.out.println("The car was not in the parking");
        }


    };

    public static  BiConsumer<String,String> SLOT_NUMBERS_FOR_CARS_WITH_COLOUR = (i,v) -> {

        List<String> collect = parkingLotDatabase.stream()
                .filter(e -> e.getColorOfTheCar().equalsIgnoreCase(i))
                .map(e -> e.getSlotNumber())
                .map(Object::toString)
                .collect(Collectors.toList());
        String joined = String.join(", ", collect);
        System.out.println(joined);
    } ;

    public static  BiConsumer<String,String> SLOT_NUMBER_FOR_REGISTRATION_NUMBER = (i,v) -> {

        List<String> collect = parkingLotDatabase.stream()
                .filter(e -> e.getRegistrationNo().equalsIgnoreCase(i))
                .map(e -> e.getSlotNumber())
                .map(Object::toString)
                .collect(Collectors.toList());
        String joined = String.join(", ", collect);
        System.out.println(joined);
    } ;

    public static  BiConsumer<String,String> REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR = (i,v) -> {

        List<String> collect = parkingLotDatabase.stream()
                .filter(e -> e.getColorOfTheCar().equalsIgnoreCase(i))
                .map(e -> e.getRegistrationNo())
                .collect(Collectors.toList());
        String joined = String.join(", ", collect);
        System.out.println(joined);
    } ;

    public static BiConsumer<String, String> STATUS = (i, v) -> {
        for (Car entry:
                parkingLotDatabase ) {
            System.out.println(entry.toString());
        }
    };

    public static BiConsumer<String, String> DEFAULT_ = (i, v) -> { };
    
   

}

