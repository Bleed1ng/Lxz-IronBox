package com.bleeding.ironbox.controller.like;

import com.bleeding.ironbox.service.like.ILikeService;
import com.bleeding.ironbox.utils.IronboxUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class LikeController {
    @Resource
    private ILikeService likeService;

    @RequestMapping(value = "like", method = RequestMethod.POST)
    @ResponseBody
    public String like(@RequestBody Map<String, String> params) {
        String userId = params.get("userId");
        int entityType = Integer.parseInt(params.get("entityType"));
        String entityId = params.get("entityId");

        // 点赞
        likeService.like(userId, entityType, entityId);
        // 数量
        long likeCount = likeService.findEntityLikeCount(entityType, entityId);
        // 状态
        int likeStatus = likeService.findEntityLikeStatus(userId, entityType, entityId);
        // 返回的结果
        Map<String, Object> map = new HashMap<>();
        map.put("likeCount", likeCount);
        map.put("likeStatus", likeStatus);

        return IronboxUtil.getJSONString(0, null, map);
    }

}
