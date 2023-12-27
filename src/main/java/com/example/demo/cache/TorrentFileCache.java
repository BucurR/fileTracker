package com.example.demo.cache;

import com.example.demo.Entity.TorrentFile;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class TorrentFileCache {

    private static final int MAX_ENTRIES = 20;
    private  Map<String, TorrentFile> cache ;
    private  LinkedList<String> order ;
    private  ReentrantReadWriteLock lock ;
    public  TorrentFileCache(){
        this.cache = new ConcurrentHashMap<>();
        this.order = new LinkedList<>();
        this.lock = new ReentrantReadWriteLock();
    }
    public void put(TorrentFile torrentFile) {
        this.lock.writeLock().lock();
        try {
            if (order.size() > MAX_ENTRIES) {
                String removedKey = order.removeLast();
                cache.remove(removedKey);
            }
            cache.put(torrentFile.getFileHash(), torrentFile);
            order.addFirst(torrentFile.getFileHash());
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public  TorrentFile get(String hash) {
        TorrentFile res = cache.get(hash);
        if (res != null) {
            this.lock.writeLock().lock();
            try {
                order.remove(hash);
                order.addFirst(hash);
            } finally {
                this.lock.writeLock().unlock();
            }
        }
        return res;
    }
}