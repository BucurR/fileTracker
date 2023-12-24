package com.example.demo.Repository;

import com.example.demo.Entity.Peer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeerRepository extends JpaRepository<Peer, Long> {
    // You can add custom query methods if needed
}
