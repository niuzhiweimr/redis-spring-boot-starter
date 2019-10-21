package com.easy.redis.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 环境
 *
 * @author niuzhiwei
 */
@Getter
@Setter
@ToString
public class RedisEnvironment {

    public static final String DEFAULT_ADAPTER_KEY = "etcRedisAdapter";
    public static final String DEFAULT_PROCESSOR_SINGLE_KEY = "redisSingleProcessor";
    public static final String DEFAULT_PROCESSOR_CLUSTER_KEY = "redisClusterProcessor";
    public static final String DEFAULT_PROCESSOR_SENTINEL_KEY = "redisSentinelProcessor";

    private Boolean redisConnectionFactoryIsInit = Boolean.FALSE;

    private String adapterKey = DEFAULT_ADAPTER_KEY;

    private String processorSingleKey = DEFAULT_PROCESSOR_SINGLE_KEY;

    private String processorClusterKey = DEFAULT_PROCESSOR_CLUSTER_KEY;

    private String processorSentinelKey = DEFAULT_PROCESSOR_SENTINEL_KEY;


}
