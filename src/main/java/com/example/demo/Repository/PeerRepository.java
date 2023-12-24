package com.example.demo.Repository;

import com.example.demo.Entity.Peer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PeerRepository extends JpaRepository<Peer, Long> {
 Optional<Peer> findPeersById(UUID id);
}
