 # 版本说明
           --注：最新版本master未经过测试请使用tag版本
    默认spring boot 版本为 2.1.5.RELEASE 下载完成项目后可更改为自己项目使用的spring boot版本  
 
    
 # redis集成说明
                    
1：启动类需要注解 { @EnableRedis } 开启redis（默认单点模式）  
       
        redis模式：
              1.单点：@EnableRedis(value = RedisModeConstants.REDIS_SINGLE)
              2.集群：@EnableRedis(value = RedisModeConstants.REDIS_CLUSTER)
              3.哨兵：@EnableRedis(value = RedisModeConstants.REDIS_SENTINEL)
 
2：关于redis的配置参数 详见类 { RedisProperties }
  
3：maven导入坐标 ${version}根据自己编译的版本选择导入
 
        <dependency>
           <groupId>com.easy.redis</groupId>
            <artifactId>redis-spring-boot-starter</artifactId>
            <version>${version}</version>
        </dependency>
 
 # redis使用说明
  
1：注入 EasyRedisTemplate 即可使用所有redis操作函数  例：
  
     1> 注入
     
     @Autowired
     private EasyRedisTemplate easyRedisTemplate;

     2> 使用函数
     
     easyRedisTemplate.set("helloWorld", "helloWorld");
                                          
