package com.huak.easygo;

import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

public class RedisTools {
    private static JedisPool pool = null;
    private static ShardedJedisPool shardedPool = null;
    private static String IP = "r-2zed72542358ec94.redis.rds.aliyuncs.com";
    private static int PORT = 6379;
    private static String  PWD = "huareSer123";

    /**
     * 构建redis连接池
     *
     * @return JedisPool
     */
    public synchronized static JedisPool getPool() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
            config.setMaxActive(500);
            // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(5);
            // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
            config.setMaxWait(1000 * 100);

            // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);
            pool = new JedisPool(config, IP, PORT,Protocol.DEFAULT_TIMEOUT,PWD);
        }
        return pool;
    }

    /**
     *
     * 集群模式下，获取连接
     *
     * @author IG
     * @Date 2017年4月18日 下午1:18:56
     * @version 1.0.0
     * @return ShardedJedisPool
     */
    public static synchronized ShardedJedisPool getShardedPool() {
        if (shardedPool == null) {
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
            poolConfig.setMaxActive(500);
            // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            poolConfig.setMaxIdle(5);
            // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
            poolConfig.setMaxWait(1000 * 100);

            // 定义集群信息
            List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
            JedisShardInfo jedisShardInfo = new JedisShardInfo(IP, PORT);
            jedisShardInfo.setPassword(PWD);

            shards.add(jedisShardInfo);
            // shards.add(new JedisShardInfo("127.0.0.1", 6380));

            // 定义集群连接接
            shardedPool = new ShardedJedisPool(poolConfig, shards);
        }
        return shardedPool;

    }

    /**
     * 单节点模式返还到连接池
     *
     * @param pool
     * @param redis
     */
    public static void returnResource(JedisPool pool, Jedis redis) {
        if (redis != null) {
            pool.returnResourceObject(redis);
        }
    }

    /**
     * 集群模式释放连接到连接池
     *
     * @author IG
     * @Date 2017年4月18日 下午1:14:27
     * @version 1.0.0
     * @param pool
     * @param redis
     */
    public static void returnResource(ShardedJedisPool pool, ShardedJedis redis) {
        if (redis != null) {
            pool.returnResourceObject(redis);
        }
    }

    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        String value = null;

        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            // 释放redis对象
            if(null != pool){
                pool.returnBrokenResource(jedis);
            }
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(pool, jedis);
        }

        return value;
    }

    /**
     * 获取连接
     *
     * @return
     */
    public static Jedis getJedis() {

        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jedis;
    }

    public static void main(String[] args) {
        ShardedJedisPool pool = RedisTools.getShardedPool();
        ShardedJedis jedis = pool.getResource();
        // jedis.set("666", "5555");
        System.out.println(jedis.get("666"));
        pool.returnResourceObject(jedis);
    }
}
