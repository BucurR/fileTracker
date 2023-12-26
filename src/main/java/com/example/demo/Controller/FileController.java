package com.example.demo.Controller;

import com.example.demo.Entity.TorrentFile;
import com.example.demo.Entity.Peer;
import com.example.demo.Service.FileService;
import com.example.demo.Service.PeerService;
import com.example.demo.dtos.TorrentFileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;


@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private PeerService peerService;

    @PostMapping("/addFile")
    public ResponseEntity<TorrentFileDto> addFile(@RequestBody TorrentFileDto requestBody, HttpServletRequest request) throws Exception {
        if (requestBody.getPeerIdentifier() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid peer identifier");
        }
        TorrentFile existingFile = fileService.getExistingOrNewFile(requestBody);
        Peer peer = peerService.createOrUpdatePeer(requestBody, request);
        fileService.addOrUpdatePeerToTorrentFile(existingFile, peer);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{fileHash}/getPeers")
    public ResponseEntity<List<Peer>> getPeers(@PathVariable String fileHash){
        List<Peer> peers = fileService.getPeersForFile(fileHash);
        return new ResponseEntity<>(peers, HttpStatus.OK);
    }
}