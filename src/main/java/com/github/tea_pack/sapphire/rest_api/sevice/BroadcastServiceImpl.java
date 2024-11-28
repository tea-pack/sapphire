package com.github.tea_pack.sapphire.rest_api.sevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.github.tea_pack.sapphire.entities.Broadcast;

import org.springframework.stereotype.Service;

@Service
public class BroadcastServiceImpl implements BroadcastService {

    private static final Map<Integer, Broadcast> BROADCAST_REPOSITORY_MAP = new HashMap<>();

    private static final AtomicInteger BROADCAST_ID_HOLDER = new AtomicInteger();

    @Override
    public void creat(Broadcast broadcast) {
        final int broadcastID = BROADCAST_ID_HOLDER.incrementAndGet();
        broadcast.entityID = broadcastID;
        BROADCAST_REPOSITORY_MAP.put(broadcastID, broadcast);
    }

    @Override
    public List<Broadcast> readAll() {
        return new ArrayList<>(BROADCAST_REPOSITORY_MAP.values());
    }

    @Override
    public Broadcast read(int id) {
        return BROADCAST_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Broadcast broadcast, int id) {
        if (BROADCAST_REPOSITORY_MAP.containsKey(id)) {
            broadcast.entityID = id;
            BROADCAST_REPOSITORY_MAP.put(id, broadcast);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        return BROADCAST_REPOSITORY_MAP.remove(id) != null;
    }
}
