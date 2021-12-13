package com.example.NotCursedWork.repository;

import com.example.NotCursedWork.models.Order1;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order1, Long> {
    @Query("from Order1 where id=:id")
    List<Order1> findAllbyidId(@Param("id") Long id);

    @Query("from Order1 order by Price ASC")
    List<Order1> findByPrice();
}
