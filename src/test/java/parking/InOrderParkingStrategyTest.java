package parking;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class InOrderParkingStrategyTest {

    private Mock mock;

	@Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
	    * With using Mockito to mock the input parameter */
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        ParkingLot parkingLot  = mock(ParkingLot.class);
        when(parkingLot.getName()).thenReturn("AAAA");
        Car car  = mock(Car.class);
        when(car.getName()).thenReturn("C:12322");
        Receipt receipt = inOrderParkingStrategy.createReceipt(parkingLot, car);
        System.out.println(receipt.getCarName());
        verify(parkingLot,times(1)).getName();
        Assert.assertEquals("C:12322",receipt.getCarName());
    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */

    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){

	    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */

    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */

    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */

    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

    }


}
