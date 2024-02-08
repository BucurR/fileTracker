package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table
public class TorrentFile {
    @Id
    private String fileHash;
    private String fileName;
    private int fileSize;
    private String fileType;
    @ManyToMany
    @JoinTable(name = "peer_files",
    joinColumns = @JoinColumn(name="file_hash"),
    inverseJoinColumns = @JoinColumn(name = "peer_id"))
    private Set<Peer> associatedPeers = new HashSet<>();
    public TorrentFile() {

    }
    public TorrentFile(String fileHash,String fileName,String fileType,int fileSize){
        this.fileHash = fileHash;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }
}
