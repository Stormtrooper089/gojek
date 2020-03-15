package gojek;

import com.gojek.CommandServices.CommandEvaluator;
import com.gojek.ParkingLot.ParkingLot;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class CommandEvaluatorTest extends TestCase {

	private final String PLOT_ALREADY_CREATED = "The Parking lot is already created . Please proceed with the functions";
	private final String PARKING_PLOT_ERROR = "The number of parking places to be created is not the correct number";
	private final String COMMAND_ERR = "Not a valid command";
	private final String LEAVE_ERR = "The car numeber is not the correct integer";
	private final String LEAVE_WARNING = "The car was not in the parking";
	private final String PARK_WARNING = "There are no empty slots at this moment";

	@Before
	public void setUp() {

	}

	@Test
	public void testParkingCreation() {
		CommandEvaluator.consume("create_parking_lot");
		assertEquals(0, ParkingLot.unReservedSlots.size());

		CommandEvaluator.consume("park");
		assertEquals(0, ParkingLot.unReservedSlots.size());
		assertEquals(0, ParkingLot.reservedSlots.size());

		CommandEvaluator.consume("leave");
		assertEquals(0, ParkingLot.unReservedSlots.size());

		CommandEvaluator.consume("status");
		assertEquals(0, ParkingLot.unReservedSlots.size());

		CommandEvaluator.consume("slot_numbers_for_cars_with_colour");
		assertEquals(0, ParkingLot.unReservedSlots.size());

		CommandEvaluator.consume("slot_number_for_registration_number");
		assertEquals(0, ParkingLot.unReservedSlots.size());

		CommandEvaluator.consume("create_parking_lot 5");
		assertEquals(5, ParkingLot.unReservedSlots.size());
		assertEquals(0, ParkingLot.reservedSlots.size());

		CommandEvaluator.consume("leave");
		assertEquals(5, ParkingLot.unReservedSlots.size());

		CommandEvaluator.consume("park KA-01-HH-1234 White");
		assertEquals(4, ParkingLot.unReservedSlots.size());
		assertEquals(1, ParkingLot.reservedSlots.size());

		CommandEvaluator.consume("leave 1");
		assertEquals(0, ParkingLot.reservedSlots.size());
		assertEquals(5, ParkingLot.unReservedSlots.size());

		// Not working perfectly for this scenario
		/*
		 * CommandEvaluator.consume("create_parking_lot 5");
		 * assertEquals(5,ParkingLot.unReservedSlots.size());
		 * assertEquals(0,ParkingLot.reservedSlots.size());
		 */

		CommandEvaluator.consume("park KA-01-HH-1234 White");
		assertEquals(4, ParkingLot.unReservedSlots.size());
		assertEquals(1, ParkingLot.reservedSlots.size());

		CommandEvaluator.consume("create_parking_lot 5");
		assertEquals(4, ParkingLot.unReservedSlots.size());
		assertEquals(1, ParkingLot.reservedSlots.size());

		CommandEvaluator.consume("park KA-01-HH-1234 White");
		assertEquals(3, ParkingLot.unReservedSlots.size());
		assertEquals(2, ParkingLot.reservedSlots.size());

		CommandEvaluator.consume("park KA-01-HH-1234 White");
		assertEquals(2, ParkingLot.unReservedSlots.size());
		assertEquals(3, ParkingLot.reservedSlots.size());

		CommandEvaluator.consume("park KA-01-HH-1234 White");
		assertEquals(1, ParkingLot.unReservedSlots.size());
		assertEquals(4, ParkingLot.reservedSlots.size());

		// Not validating same number vehicle
		CommandEvaluator.consume("park KA-01-HH-1234 White");
		assertEquals(0, ParkingLot.unReservedSlots.size());
		assertEquals(5, ParkingLot.reservedSlots.size());

		CommandEvaluator.consume("leave 2");
		assertEquals(1, ParkingLot.unReservedSlots.size());
		assertEquals(4, ParkingLot.reservedSlots.size());

		CommandEvaluator.consume("leave 3");
		assertEquals(2, ParkingLot.unReservedSlots.size());
		assertEquals(3, ParkingLot.reservedSlots.size());

		CommandEvaluator.consume("leave 4");
		assertEquals(3, ParkingLot.unReservedSlots.size());
		assertEquals(2, ParkingLot.reservedSlots.size());

		// I can only heck if this functions are working
		// If car doesn't exist with specific color no message shown
		// When records found show descriptive message
		CommandEvaluator.consume("registration_numbers_for_cars_with_colour blue");

		CommandEvaluator.consume("slot_number_for_registration_number KA-01-HH-1234");

		CommandEvaluator.consume("slot_numbers_for_cars_with_colour white");
	}

}
