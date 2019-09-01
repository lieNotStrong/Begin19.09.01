package com.neuedu.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {


    private JedisPool jedisPool;

    public RedisPool() {}

    /**
     * redis:
     max:
     total: 8
     idel: 8
     min:
     idel: 2
     test:
     borrow: true
     return: true
     timeout: 2000
     ip: 39.96.161.245
     port: 5379
     password: Lie1998..
     *
     * */
    public RedisPool(Integer maxTotle,Integer maxIdel,Integer minIdel,
                     boolean testBorrow,boolean testReturn,
                     Integer timeOut,
                     String ip,Integer port,
                     String password){

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotle);
        jedisPoolConfig.setMaxIdle(maxIdel);
        jedisPoolConfig.setMinIdle(minIdel);
        jedisPoolConfig.setTestOnBorrow(testBorrow);
        jedisPoolConfig.setTestOnReturn(testReturn);
        jedisPoolConfig.setBlockWhenExhausted(true);

        //GenericObjectPoolConfig poolConfig, String host, int port, int timeout, String password
        jedisPool = new JedisPool(jedisPoolConfig,ip,port,timeOut,password);
    }

    //获取jedis
    public Jedis getJedis(){
        return jedisPool.getResource();
    }

    //放回jedis
    public void returnJedis(Jedis jedis){
        jedisPool.returnResource(jedis);
    }

}
