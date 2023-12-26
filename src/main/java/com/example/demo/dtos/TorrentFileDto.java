package com.example.demo.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class TorrentFileDto {
    private UUID peerIdentifier;
    private String fileHash;
    private String fileName;
    private String fileType;
    private int fileSize;
    private List<PeerDto> activePeers;


}
