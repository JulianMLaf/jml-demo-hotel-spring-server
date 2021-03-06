package convertor;

import org.springframework.core.convert.converter.Converter;

import com.jml.demo_hotel.entity.ReservationEntity;
import com.jml.demo_hotel.response.ReservationResponse;

public class ReservationEntityToReservationResponseConverter implements Converter<ReservationEntity, ReservationResponse> {

	@Override
	public ReservationResponse convert(ReservationEntity source) {
		ReservationResponse reservationResponse = new ReservationResponse();
		reservationResponse.setCheckin(source.getCheckin());
		reservationResponse.setCheckout(source.getCheckout());
		
        if (null != source.getRoomEntity())
            reservationResponse.setId(source.getRoomEntity().getId());
		
		return reservationResponse;
	}

}
