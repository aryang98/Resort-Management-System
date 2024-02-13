// Declaring the package where the IRoomService interface resides
package com.resort.service;

// Importing necessary classes and entities
import java.util.List;
import com.resort.dto.RoomRequest;
import com.resort.entity.Room;

// Interface representing Room Service
public interface IRoomService {

	// Method to fetch all rooms
	List<Room> fetchAllRooms();

	// Method to save (create/update) a room based on the provided RoomRequest
	String saveRoom(RoomRequest request);

	// Method to delete a room based on the provided RoomRequest
	String deleteRoomById(RoomRequest request);

	// Method to update a room based on the provided RoomRequest
	String updateRoom(RoomRequest id);

	// Method to fetch rooms based on filtering criteria provided in RoomRequest
	List<Room> fetchByFilter(RoomRequest request);

}
