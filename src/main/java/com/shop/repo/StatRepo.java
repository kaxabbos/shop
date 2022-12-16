package com.shop.repo;

import com.shop.model.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatRepo extends JpaRepository<Stat, Long> {

}
