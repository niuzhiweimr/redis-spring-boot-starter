package com.easy.redis.cache;

import com.easy.redis.adapter.RedisAdapter;

import java.util.HashMap;
import java.util.Map;

public class RedisAdapterAndProcessorCache {


    public static final String ADAPTER_KEY = "etcRedisAdapter";

    public static final String PROCESSOR_SINGLE_KEY = "redisSingleProcessor";

    public static final String PROCESSOR_CLUSTER_KEY = "redisClusterProcessor";

    public static final String PROCESSOR_SENTINEL_KEY = "redisSentinelProcessor";


    /*适配器缓存map*/
    public static Map<String, RedisAdapter> RedisAdapterMap = new HashMap<>();

    /*处理器缓存map*/
    public static Map<String, Object> RedisProcessorMap = new HashMap<>();


}
