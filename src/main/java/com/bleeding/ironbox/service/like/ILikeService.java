package com.bleeding.ironbox.service.like;

public interface ILikeService {
    
    void like(String userId, int entityType, String entityId);

    long findEntityLikeCount(int entityType, String entityId);

    int findEntityLikeStatus(String userId, int entityType, String entityId);
}
