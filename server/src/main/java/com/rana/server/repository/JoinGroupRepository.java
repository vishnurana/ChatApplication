package com.rana.server.repository;

import java.util.Map;

public class JoinGroupRepository {

    private Map<Long, Long> clientGroupMapping;

    private void addGroup(long clientId, long groupId) {
        clientGroupMapping.put(clientId, groupId);
    }

    private long leaveGroup(long clientId) {
        return clientGroupMapping.remove(clientId);
    }

}
