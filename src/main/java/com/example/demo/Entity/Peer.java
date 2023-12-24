package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

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
