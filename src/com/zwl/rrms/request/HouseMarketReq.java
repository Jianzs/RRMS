package com.zwl.rrms.request;

import com.zwl.rrms.entity.HouseEntity;

import java.util.ArrayList;
import java.util.List;

public class HouseMarketReq {
    public static List<HouseEntity> listHouseByPage(int page) {
        List<HouseEntity> entities = new ArrayList<>();
        entities.add(new HouseEntity.Builder()
                .setNeighborhood("Hello N")
                .build());
        entities.add(new HouseEntity.Builder()
                .setNeighborhood("Hello N")
                .build());
        entities.add(new HouseEntity.Builder()
                .setNeighborhood("Hello N")
                .build());
        entities.add(new HouseEntity.Builder()
                .setNeighborhood("Hello N")
                .build());
        entities.add(new HouseEntity.Builder()
                .setNeighborhood("Hello N")
                .build());
        entities.add(new HouseEntity.Builder()
                .setNeighborhood("Hello N")
                .build());
        entities.add(new HouseEntity.Builder()
                .setNeighborhood("Hello N")
                .build());
        return entities;
    }
}
