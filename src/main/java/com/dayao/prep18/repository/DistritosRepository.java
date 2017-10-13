package com.dayao.prep18.repository;

import com.dayao.prep18.domain.Distritos;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Distritos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DistritosRepository extends JpaRepository<Distritos, Long> {

}
