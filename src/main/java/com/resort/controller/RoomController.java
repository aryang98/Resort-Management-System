package com.resort.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resort.dto.GeneralResponseDTO;
import com.resort.dto.RoomRequest;
import com.resort.entity.Room;
import com.resort.service.IRoomService;

import jakarta.validation.Valid;

// Controller class to manage rooms based on user and admin functionalities
@RestController // Annotation to define this class as a REST controller
@RequestMapping("/home")
public class RoomController {

	@Autowired(required = true) // Autowiring the IRoomService bean
	private IRoomService roomService; // Declaring a variable of IRoomService type

	@GetMapping("/rooms") // HTTP GET method to get all rooms
	public ResponseEntity<GeneralResponseDTO> getAllRooms() {
		List<Room> rooms = roomService.fetchAllRooms(); // Fetch all rooms using the RoomService
		GeneralResponseDTO reponseDTO = new GeneralResponseDTO("List of rooms", rooms); // Create a response DTO
		return new ResponseEntity<GeneralResponseDTO>(reponseDTO, HttpStatus.OK); // Return the response
	}

	@GetMapping("/room/filter") // HTTP GET method to filter rooms by price range
	public ResponseEntity<GeneralResponseDTO> getByPriceRange(@RequestBody RoomRequest request) {
		List<Room> rooms = roomService.fetchByFilter(request); // Fetch rooms based on filter criteria
		GeneralResponseDTO reponseDTO = new GeneralResponseDTO("List of rooms", rooms); // Create a response DTO
		return new ResponseEntity<GeneralResponseDTO>(reponseDTO, HttpStatus.OK); // Return the response
	}

	@PostMapping("/addRoom") // HTTP POST method to add a new room
	public ResponseEntity<GeneralResponseDTO> saveUser(@Valid @RequestBody RoomRequest request) {
		String message = roomService.saveRoom(request); // Save room using RoomService
		GeneralResponseDTO responseDTO = new GeneralResponseDTO("message", message); // Create a response DTO
		return new ResponseEntity<GeneralResponseDTO>(responseDTO, HttpStatus.OK); // Return the response
	}

	@PutMapping("/resetRoom") // HTTP PUT method to reset room details
	public ResponseEntity<GeneralResponseDTO> updateCredientials(@Valid @RequestBody RoomRequest request) {
		String message = roomService.updateRoom(request); // Update room details using RoomService
		GeneralResponseDTO responseDTO = new GeneralResponseDTO("message", message); // Create a response DTO
		return new ResponseEntity<GeneralResponseDTO>(responseDTO, HttpStatus.OK); // Return the response
	}

	@DeleteMapping("/room") // HTTP DELETE method to delete a room
	public ResponseEntity<GeneralResponseDTO> deleteUser(@RequestBody RoomRequest request) {
		String message = roomService.deleteRoomById(request); // Delete room by ID using RoomService
		GeneralResponseDTO responseDTO = new GeneralResponseDTO("message", message); // Create a response DTO
		return new ResponseEntity<GeneralResponseDTO>(responseDTO, HttpStatus.OK); // Return the response
	}
}
