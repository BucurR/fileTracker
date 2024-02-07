package com.example.demo.Service;

import com.example.demo.Entity.Peer;
import com.example.demo.Entity.TorrentFile;
import com.example.demo.Repository.FileRepository;
import com.example.demo.Repository.PeerRepository;
import com.example.demo.cache.TorrentFileCache;
import com.example.demo.dtos.PeerDto;
import com.example.demo.dtos.TorrentFileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class FileService {
    private final FileRepository fileRepository;
    private final PeerRepository peerRepository;
    private TorrentFileCache torrentFileCache;

    @Autowired
    public FileService(FileRepository fileRepository, PeerRepository peerRepository) {
        this.fileRepository = fileRepository;
        this.peerRepository = peerRepository;
        this.torrentFileCache = new TorrentFileCache();
    }

    public List<TorrentFileDto> getFiles() {
        return fileRepository.findAll()
                .stream()
                .map(torrentFile -> TorrentFileDto
                            .builder()
                        .fileHash(torrentFile.getFileHash())
                        .fileName(torrentFile.getFileName())
                        .fileType(torrentFile.getFileType())
                        .fileSize(torrentFile.getFileSize())
                        .activePeers(torrentFile.getAssociatedPeers()
                                .stream()
                                .map(peer -> PeerDto
                                        .builder()
                                        .id(String.valueOf(peer.getId()))
                                        .address(peer.getAddress())
                                        .port(peer.getPort())
                                        .build())
                                .toList())
                        .build()
                )
                .toList();
    }

    public List<Peer> getPeersForFile(String fileHash) {
        return peerRepository.findByAssociatedFiles_FileHash(fileHash);
    }

    public TorrentFile getFileByHash(String fileHash) {
        TorrentFile res = torrentFileCache.get(fileHash);
        if (res == null) {
            res = fileRepository.findByFileHash(fileHash);
            if (res != null) {
                torrentFileCache.put(res);
            }
        }
        return res;
    }


    public TorrentFile getExistingOrNewFile(TorrentFileDto requestBody) {
        String fileHash = requestBody.getFileHash();
        TorrentFile existingFile = getFileByHash(fileHash);
        if (existingFile == null) {
            existingFile = new TorrentFile(requestBody.getFileHash(), requestBody.getFileName(), requestBody.getFileType(), requestBody.getFileSize());
        }
        return existingFile;
    }

    @Transactional
    public void addOrUpdatePeerToTorrentFile(TorrentFile existingFile, Peer peer) {
        existingFile.getAssociatedPeers().add(peer);
        fileRepository.save(existingFile);
    }
}
