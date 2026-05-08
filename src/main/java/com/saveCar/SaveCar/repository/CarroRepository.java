package com.saveCar.SaveCar.repository;

import com.saveCar.SaveCar.entity.CarroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<CarroEntity, Long> {
}
