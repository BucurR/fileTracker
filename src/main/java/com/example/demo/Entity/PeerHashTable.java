package com.example.demo.Entity;

import lombok.*;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PeerHashTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String fileHash; // The hash for the file content

    @ManyToMany(mappedBy = "peerHashTables")
    private Set<Peer> peers = new HashSet<>();

}
