package com.github.tea_pack.sapphire.rest_api.sevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.github.tea_pack.sapphire.entities.Channel;

import org.springframework.stereotype.Service;

@Service
public class ChannelServiceImpl implements ChannelService {

    private static final Map<Integer, Channel> CHANNEL_REPOSITORY_MAP = new HashMap<>();

    private static final AtomicInteger CHANNEL_ID_HOLDER = new AtomicInteger();

    @Override
    public void creat(Channel channel) {
        final int channelId = CHANNEL_ID_HOLDER.incrementAndGet();
        channel.entityID = channelId;
        CHANNEL_REPOSITORY_MAP.put(channelId, channel);
    }

    @Override
    public List<Channel> readAll() {
        return new ArrayList<>(CHANNEL_REPOSITORY_MAP.values());
    }

    @Override
    public Channel read(int id) {
        return CHANNEL_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Channel channel, int id) {
        if (CHANNEL_REPOSITORY_MAP.containsKey(id)) {
            channel.entityID = id;
            CHANNEL_REPOSITORY_MAP.put(id, channel);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        return CHANNEL_REPOSITORY_MAP.remove(id) != null;
    }
}
