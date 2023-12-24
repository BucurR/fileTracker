package com.example.demo.Repository;

import com.example.demo.Entity.PeerHashTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PeerHashTableRepository extends JpaRepository<PeerHashTable, Long> {
    Optional<PeerHashTable> findByFileHash(String fileHash);
}