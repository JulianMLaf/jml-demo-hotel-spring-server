package com.jml.demo_hotel.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.jml.demo_hotel.entity.RoomEntity;

public interface RoomRepository extends CrudRepository<RoomEntity, Long>{

	Optional<RoomEntity> findById(Long id);
}
