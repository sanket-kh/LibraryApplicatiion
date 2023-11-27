package com.librarymanagement.repositories;

import com.librarymanagement.entities.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FineRepo extends JpaRepository<Fine, Long> {
}
