package com.example.demo.Controller;


import com.example.demo.Entity.Peer;
import com.example.demo.Service.PeerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/api/v1/peer")
public class PeerController {
    @Autowired
    private PeerService peerService;

    @GetMapping("/")
    public List<Peer> getPeers(){
        return peerService.getPeers();
    }
    @PostMapping("/register")
    public ResponseEntity<UUID> registerClient(HttpServletRequest request){
        int port = request.getRemotePort();
        String adress = request.getRemoteAddr();
        UUID pID= peerService.registerPeer(adress,port);
        if(pID == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok(pID);
    }
}
