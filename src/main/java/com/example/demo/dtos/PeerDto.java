package com.example.demo.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PeerDto {
    private String id;
    private String address;
    private int port;
}
