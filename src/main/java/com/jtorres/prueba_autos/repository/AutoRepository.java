package com.jtorres.prueba_autos.repository;

import com.jtorres.prueba_autos.entity.Auto;
import com.jtorres.prueba_autos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutoRepository extends JpaRepository<Auto, String> {

    List<Auto> findByUser(User user);

    List<Auto> findByUserId(Integer userId);

    List<Auto> findByMarca(String marca);

    List<Auto> findByModelo(String modelo);

    List<Auto> findByYear(Integer Year);

    List<Auto> findByColor(String color);

    @Query("SELECT COUNT(a) FROM Auto a WHERE a.user.id = :userId")
    Long countAutosByUserId(@Param("userId") Integer userId);
}
