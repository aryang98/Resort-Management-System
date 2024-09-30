package com.resort.repository;

// Importing necessary Spring Data JPA classes and repository annotation
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resort.entity.Reservation;

// Annotation to mark this interface as a repository
@Repository
// Extending JpaRepository for managing Reservation entities with Long as the type of primary key
public interface IReservationRepo extends JpaRepository<Reservation, Long> {
	/*
	 * This interface inherits various CRUD (Create, Read, Update, Delete) methods
	 * from JpaRepository It operates on the Reservation entity with Long as the
	 * primary key type
	 */
}