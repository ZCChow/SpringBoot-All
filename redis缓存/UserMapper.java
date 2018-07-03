package com.xianjinxia.bigdata.personas.writemapper;

import com.xianjinxia.bigdata.personas.readmapper.User;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Mapper
@Component
@CacheConfig(cacheNames = "user")
public interface UserMapper {
    @Insert("insert into user(name,age) values(#{name},#{age})")
    int addUser(@Param("name")String name, @Param("age")Integer age);

   /* @Select("select * from user where id =#{id}")
    @Cacheable(value = "user")
    User findById(@Param("id") String id);*/

    @Select("select * from user where id=#{id}")
    @Cacheable(key="#p0.id")
    User findById(User user);

   /* @Select("select * from user where id =#{id}")
    @Cacheable(keyGenerator = "myKeyGenerator")
    User findById(@Param("id") String id);*/

    @CachePut(key = "#id")
    @Update("update user set name=#{name} where id=#{id}")
    void updataById(@Param("id")String id,@Param("name")String name);

    //如果指定为 true，则方法调用后将立即清空所有缓存
    @CacheEvict(key ="#p0",allEntries=true)
    @Delete("delete from user where id=#{id}")
    void deleteById(@Param("id")String id);
}
