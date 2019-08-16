package parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;

public class InOrderParkingStrategy implements ParkingStrategy {

	public Receipt park(List<ParkingLot> parkingLots, Car car) {

		parkingLots = Optional.ofNullable(parkingLots).orElse(new ArrayList<>());//如果参数的parkingLots为null的话直接新建一个空的数组
		
		for (ParkingLot parkingLot : parkingLots) {
			if (parkingLot.isFull()) {
				continue;
			}
			
			parkingLot.getParkedCars().add(car);
			return createReceipt(parkingLot, car);
		}
		
		return createNoSpaceReceipt(car);
	}

	@Override
	public Integer calculateHourlyPrice() {
		return ParkingLot.getBasicHourlyPrice();
	}

	protected Receipt createReceipt(ParkingLot parkingLot, Car car) {
		
		Receipt receipt = new Receipt();
		receipt.setCarName(car.getName());
		receipt.setParkingLotName(parkingLot.getName());
		return receipt;
	}

	private Receipt createNoSpaceReceipt(Car car) {
		
		Receipt receipt = new Receipt();
		receipt.setCarName(car.getName());
		receipt.setParkingLotName(NO_PARKING_LOT);
		return receipt;
	}

	
}
