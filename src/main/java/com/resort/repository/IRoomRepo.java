// Declaring the package where the IRoomRepo interface resides
package com.resort.repository;

// Importing necessary classes and interfaces
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resort.dto.RoomType;
import com.resort.entity.Reservation;
import com.resort.entity.Room;

// Annotation to mark this interface as a repository component
@Repository
// Declaring the IRoomRepo interface extending JpaRepository with Room entity and Long as the ID type
public interface IRoomRepo extends JpaRepository<Room, Long> {

	// Custom query method to find rooms by price range
	List<Room> findByPriceBetween(double minPrice, double maxPrice);

	Optional<Reservation> findFirstByAvailabilityIsTrueAndRoomType(RoomType roomType);
}
