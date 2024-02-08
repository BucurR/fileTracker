package com.example.demo.controller;

import com.example.demo.Entity.TorrentFile;
import com.example.demo.Entity.Peer;
import com.example.demo.Service.FileService;
import com.example.demo.Service.PeerService;
import com.example.demo.dtos.PeerDto;
import com.example.demo.dtos.TorrentFileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    private final FileService fileService;
    private final PeerService peerService;

    @PostMapping("/")
    public ResponseEntity<TorrentFileDto> addFile(@RequestBody TorrentFileDto requestBody, HttpServletRequest request) throws Exception {
        if (requestBody.getPeerIdentifier() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid peer identifier");
        }
        TorrentFile existingFile = fileService.getExistingOrNewFile(requestBody);
        Peer peer = peerService.createOrUpdatePeer(requestBody, request);
        fileService.addOrUpdatePeerToTorrentFile(existingFile, peer);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<TorrentFileDto>> getFiles(){
        List<TorrentFileDto> files = fileService.getFiles();
        return new ResponseEntity<>(files, HttpStatus.OK);
    }

    @GetMapping("/{fileHash}/getPeers")
    public ResponseEntity<List<PeerDto>> getPeers(@PathVariable String fileHash){
        List<PeerDto> peers = fileService.getPeersForFile(fileHash);
        return new ResponseEntity<>(peers, HttpStatus.OK);
    }
}