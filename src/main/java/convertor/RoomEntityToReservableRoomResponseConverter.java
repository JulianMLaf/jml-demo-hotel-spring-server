package convertor;

import org.springframework.core.convert.converter.Converter;

import com.jml.demo_hotel.entity.RoomEntity;
import com.jml.demo_hotel.model.Links;
import com.jml.demo_hotel.model.Self;
import com.jml.demo_hotel.response.ReservableRoomResponse;
import com.jml.demo_hotel.rest.ResourceConstants;

public class RoomEntityToReservableRoomResponseConverter implements Converter<RoomEntity, ReservableRoomResponse>{

	@Override
	public ReservableRoomResponse convert(RoomEntity source) {

		ReservableRoomResponse reservationResponse = new ReservableRoomResponse();
		if(null != source.getId())
			reservationResponse.setId(source.getId());
		reservationResponse.setRoomNumber(source.getRoomNumber());
		reservationResponse.setPrice(Integer.valueOf(source.getPrice()));
		
		Links links = new Links();
		
		Self self = new Self();
		
		self.setRef(ResourceConstants.ROOM_RESERVATION_V1 + "/" + source.getId());
		links.setSelf(self);
		
		reservationResponse.setLinks(links);
		return reservationResponse;
	}

}
