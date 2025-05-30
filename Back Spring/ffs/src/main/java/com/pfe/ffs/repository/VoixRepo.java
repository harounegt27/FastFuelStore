package com.pfe.ffs.repository;

import com.pfe.ffs.entity.Voix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoixRepo extends JpaRepository<Voix, Long> {
    Optional<Voix> findByName(String name);
}
