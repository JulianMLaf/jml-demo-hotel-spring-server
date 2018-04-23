package com.jml.demo_hotel.rest;


import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jml.demo_hotel.entity.ReservationEntity;
import com.jml.demo_hotel.entity.RoomEntity;
import com.jml.demo_hotel.repository.PageableRoomRepository;
import com.jml.demo_hotel.repository.ReservationRepository;
import com.jml.demo_hotel.repository.RoomRepository;
import com.jml.demo_hotel.request.ReservationRequest;
import com.jml.demo_hotel.response.ReservableRoomResponse;
import com.jml.demo_hotel.response.ReservationResponse;

import convertor.RoomEntityToReservableRoomResponseConverter;

@RestController
@RequestMapping(ResourceConstants.ROOM_RESERVATION_V1)
@CrossOrigin
public class ReservationResource {

	@Autowired
	PageableRoomRepository pageableRoomRepository;
	
	@Autowired
	RoomRepository roomRepository;
	@Autowired	
	ReservationRepository reservationRepository;
	@Autowired
	ConversionService conversionService;
    @RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<ReservableRoomResponse> getAvailableRooms(
            @RequestParam(name = "checkin")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate checkin,
            @RequestParam(name = "checkout")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate checkout, Pageable pageable) {

    	Page<RoomEntity> roomEntityList = pageableRoomRepository.findAll(pageable);
        return roomEntityList.map(roomEntity->new RoomEntityToReservableRoomResponseConverter().convert(roomEntity));
    }

    @RequestMapping(path="/{roomId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RoomEntity> getRoomById(
    		@PathVariable
    		Long roomId){
    	
    	RoomEntity roomEntity = roomRepository.findById(roomId).get();
		
    	return new ResponseEntity<>(roomEntity,HttpStatus.OK);
    	
    }
    @RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ReservationResponse> createReservation(
            @RequestBody
            ReservationRequest reservationRequest) {

    	ReservationEntity reservationEntity = conversionService.convert(reservationRequest, ReservationEntity.class);
    	reservationRepository.save(reservationEntity);
    	
    	Optional<RoomEntity> roomEntityOptional = roomRepository.findById(reservationRequest.getRoomId());
    	RoomEntity roomEntity = roomEntityOptional.get();
    	roomEntity.addReservationEntity(reservationEntity);
    	roomRepository.save(roomEntity);
    	reservationEntity.setRoomEntity(roomEntity);
    	
    	ReservationResponse reservationResponse = conversionService.convert(reservationEntity, ReservationResponse.class);
        return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
    }

    @RequestMapping(path = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ReservableRoomResponse> updateReservation(
            @RequestBody
            ReservationRequest reservationRequest) {

        return new ResponseEntity<>(new ReservableRoomResponse(), HttpStatus.OK);
    }

    @RequestMapping(path = "/{reservationId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteReservation(
            @PathVariable
            long reservationId) {

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}

