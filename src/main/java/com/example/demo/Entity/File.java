package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class File {
    private String fileHash;
    private String fileName;
    private int fileSize;
    private String fileType;
    @Id
    private Long id;
    private long peerId;
}
