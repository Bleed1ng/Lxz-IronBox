package com.bleeding.ironbox.service.like;

import com.bleeding.ironbox.dao.QuestionDao;
import com.bleeding.ironbox.utils.RedisKeyUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LikeServiceImpl implements ILikeService {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    QuestionDao questionDao;

    // 点赞
    @Override
    public void like(String userId, int entityType, String entityId) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
        boolean isMember = redisTemplate.opsForSet().isMember(entityLikeKey, userId);
        if (isMember) {
            redisTemplate.opsForSet().remove(entityLikeKey, userId);
            if (entityType == 1) {
                questionDao.deleteQuestionFollow(entityId, userId);
            }
        } else {
            redisTemplate.opsForSet().add(entityLikeKey, userId);
            if (entityType == 1) {
                questionDao.insertQuestionFollow(entityId, userId);
            }
        }

    }

    // 查询某实体点赞的数量
    @Override
    public long findEntityLikeCount(int entityType, String entityId) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
        return redisTemplate.opsForSet().size(entityLikeKey);
    }

    // 查询某人对某实体的点赞状态
    @Override
    public int findEntityLikeStatus(String userId, int entityType, String entityId) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
        return redisTemplate.opsForSet().isMember(entityLikeKey, userId) ? 1 : 0;
    }



}
