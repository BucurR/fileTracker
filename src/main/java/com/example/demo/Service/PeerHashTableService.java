package com.example.demo.Service;

import com.example.demo.Entity.Peer;
import com.example.demo.Entity.PeerHashTable;
import com.example.demo.Repository.PeerHashTableRepository;
import com.example.demo.Repository.PeerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PeerHashTableService {

    @Autowired
    private PeerHashTableRepository peerHashTableRepository;

    private Map<String, PeerHashTable> peerHashTableCache = new HashMap<>();

    @PostConstruct
    public void init() {
        List<PeerHashTable> hashTables = peerHashTableRepository.findAll();
        for (PeerHashTable hashTable : hashTables) {
            peerHashTableCache.put(hashTable.getFileHash(), hashTable);
        }
    }

    public void addPeer(String fileHash, Peer peer) {
        PeerHashTable peerHashTable = peerHashTableCache.computeIfAbsent(fileHash, k -> new PeerHashTable());
        //peerHashTable.getPeers().add(peer);
        peerHashTableRepository.save(peerHashTable);
    }

    public void synchronizeDatabase() {
        peerHashTableRepository.saveAll(peerHashTableCache.values());
    }

    public PeerHashTable getPeerHashTable(String fileHash) {
        PeerHashTable peerHashTable = peerHashTableCache.get(fileHash);

        if (peerHashTable == null) {
            throw new NoSuchElementException("Peer hash table not found for file hash: " + fileHash);
        }

        return peerHashTable;
    }
}
