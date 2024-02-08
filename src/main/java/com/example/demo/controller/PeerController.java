package com.example.demo.controller;


import com.example.demo.Entity.Peer;
import com.example.demo.Service.PeerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/api/v1/peers")

public class PeerController {

    private final PeerService peerService;

    @GetMapping("/")
    public List<Peer> getPeers(){
        return peerService.getPeers();
    }

    @PostMapping("/register")
    public ResponseEntity<UUID> registerClient(HttpServletRequest request){

        int port = request.getLocalPort();
        String address = request.getLocalAddr();
        UUID pID = peerService.registerPeer(address,port);

        if(pID == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.ok(pID);
    }
}
