package com.example.demo.configuration;

import com.example.demo.Entity.Peer;
import com.example.demo.Entity.TorrentFile;
import com.example.demo.Repository.FileRepository;
import com.example.demo.Repository.PeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PeerConfig {
    @Bean
    CommandLineRunner cmdRunner(PeerRepository peerRepository, FileRepository fileRepository){
        return args -> {
            Peer peer1 = new Peer("080808",8080);
            Peer peer2 = new Peer("test.com", 5050);
            peerRepository.saveAll(List.of(peer1,peer2));
            TorrentFile torrentFile = new TorrentFile("asfdgas","test","pdf",300);
            fileRepository.save(torrentFile);
        };
    }
}
