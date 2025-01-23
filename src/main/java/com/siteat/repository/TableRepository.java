package com.siteat.repository;
import com.siteat.model.Table;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Table, Long> {
}
