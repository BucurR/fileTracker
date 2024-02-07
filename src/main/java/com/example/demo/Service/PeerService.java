package com.example.demo.Service;

import com.example.demo.Entity.Peer;
import com.example.demo.Repository.PeerRepository;
import com.example.demo.dtos.TorrentFileDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PeerService {
    private final PeerRepository peerRepository;

    @Autowired
    public PeerService(PeerRepository peerRepository){
        this.peerRepository = peerRepository;
    }

    public List<Peer> getPeers(){
        return peerRepository.findAll();
    }

    public UUID registerPeer(String host, int port){
        Peer p = new Peer(host,port);
        peerRepository.save(p);
        return  p.getId();
    }

    @Transactional
    public Peer createOrUpdatePeer(TorrentFileDto requestBody, HttpServletRequest request) {
        Peer peer = new Peer();
        peer.setId(requestBody.getPeerIdentifier());
        peer.setAddress(request.getRemoteAddr());
        peer.setPort(request.getRemotePort());
        return peer;
    }
}
