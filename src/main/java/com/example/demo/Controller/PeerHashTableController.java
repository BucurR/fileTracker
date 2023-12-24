package com.example.demo.Controller;

import com.example.demo.Entity.Peer;
import com.example.demo.Entity.PeerHashTable;
import com.example.demo.Service.PeerHashTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test")
public class PeerHashTableController {

    @Autowired
    private PeerHashTableService peerHashTableService;

    // Example GET endpoint to retrieve a PeerHashTable
    @GetMapping("/{fileHash}")
    public PeerHashTable getPeerHashTable(@PathVariable String fileHash) {
        return peerHashTableService.getPeerHashTable(fileHash);
    }

    // Example POST endpoint to add a Peer to a PeerHashTable
    @PostMapping("/{fileHash}")
    public PeerHashTable addPeerToHashTable(@PathVariable String fileHash, @RequestBody Peer peer) {
        peerHashTableService.addPeer(fileHash, peer);
        return peerHashTableService.getPeerHashTable(fileHash);
    }

    // Additional endpoints as required...
}
