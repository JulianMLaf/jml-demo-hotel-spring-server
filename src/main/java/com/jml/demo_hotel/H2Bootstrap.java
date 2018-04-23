package com.jml.demo_hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jml.demo_hotel.entity.RoomEntity;
import com.jml.demo_hotel.repository.RoomRepository;

@Component
public class H2Bootstrap implements CommandLineRunner{

	@Autowired
	RoomRepository roomRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Bootsrap Data: ");
		roomRepository.save(new RoomEntity(405, "200"));
		roomRepository.save(new RoomEntity(406, "220"));
		roomRepository.save(new RoomEntity(407, "250"));
		
		 Iterable<RoomEntity> itr=roomRepository.findAll();
		
		 System.out.println("Printing out Data: ");
		 
		 for(RoomEntity room:itr) {
			 System.out.println(room.getRoomNumber());
		 }
	}

}
