package com.example.demo.Repository;

import com.example.demo.Entity.Peer;
import com.example.demo.Entity.TorrentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PeerRepository extends JpaRepository<Peer, Long> {
 List<Peer> findByAssociatedFiles_FileHash(String fileHash);
}
