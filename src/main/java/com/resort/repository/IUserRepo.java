// Declaring the package where the IUserRepo interface resides
package com.resort.repository;

// Importing necessary Spring and custom classes
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.resort.entity.UserEntity;

// Annotation to mark this interface as a repository
@Repository
// Extending JpaRepository with UserEntity as the entity type and Long as the ID type
public interface IUserRepo extends JpaRepository<UserEntity, Long> {
	UserEntity findByUserId(Long userId);

}