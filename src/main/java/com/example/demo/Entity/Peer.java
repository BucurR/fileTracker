package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Table
@Data
public class Peer {

    @Id
    @GeneratedValue()
    private UUID id;
    private String address;
    private int port;
    @ManyToMany(mappedBy = "associatedPeers")
    private Set<TorrentFile> associatedFiles = new HashSet<>();
    public Peer(UUID id, String address, int port) {
        this.id = id;
        this.address = address;
        this.port = port;
    }

    public Peer(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public Peer() {

    }
}
