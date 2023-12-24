package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Peer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private int port;


    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "peer_hash_table",
            joinColumns = @JoinColumn(name = "peer_id"),
            inverseJoinColumns = @JoinColumn(name = "peer_hash_table_id")
    )
    private Set<PeerHashTable> peerHashTables = new HashSet<>();

}
