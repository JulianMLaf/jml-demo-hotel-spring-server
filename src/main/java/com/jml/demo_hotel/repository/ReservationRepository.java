package com.jml.demo_hotel.repository;

import org.springframework.data.repository.CrudRepository;

import com.jml.demo_hotel.entity.ReservationEntity;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {

}
