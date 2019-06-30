package com.lhf.spider.config;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
public class RedisConfig {


    /**
     *
     * @return
     */
    @Bean
    public JedisPoolConfig getRedisConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    /**
     *
     * @param host
     * @param password
     * @param port
     * @param index
     * @return
     */
    @Bean(name = "jedis.connectionFactory")
    public JedisConnectionFactory getConnectionFactory(
            @Value("${spring.redis.host}") String host,
            @Value("${spring.redis.password}") String password,
            @Value("${spring.redis.port}") int port,
            @Value("${spring.redis.database}") int index){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setPoolConfig(getRedisConfig());
        factory.setUsePool(true);
        factory.setHostName(host);
        factory.setPassword(password);
        factory.setPort(port);
        factory.setDatabase(index);
        return factory;
    }

    /**
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(
            @Qualifier("jedis.connectionFactory") RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(getJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(getJackson2JsonRedisSerializer());
        return template;
    }

    /**
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(
            @Qualifier("jedis.connectionFactory") RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }

    /**
     * JacksonJson序列化工具
     * @return
     */
    private RedisSerializer getJackson2JsonRedisSerializer(){
        Jackson2JsonRedisSerializer  serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);
        return serializer;
    }
}
