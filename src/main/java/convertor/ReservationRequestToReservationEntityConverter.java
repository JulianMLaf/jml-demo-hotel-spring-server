package convertor;

import org.springframework.core.convert.converter.Converter;

import com.jml.demo_hotel.entity.ReservationEntity;
import com.jml.demo_hotel.request.ReservationRequest;

public class ReservationRequestToReservationEntityConverter
		implements Converter<ReservationRequest, ReservationEntity> {

	@Override
	public ReservationEntity convert(ReservationRequest source) {
		ReservationEntity reservationEntity = new ReservationEntity();
		reservationEntity.setCheckin(source.getCheckin());
		reservationEntity.setCheckout(source.getCheckout());

		if (null != source.getId()) {
			reservationEntity.setId(source.getId());
		}

		return reservationEntity;
	}

}
