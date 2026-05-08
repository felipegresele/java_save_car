package com.saveCar.SaveCar.repository;

import com.saveCar.SaveCar.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RolesEntity, Integer> {

    Optional<RolesEntity> findByName(String nome);

}
