package cn.zxlee.znotifyapi.mapper.base;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BaseMapper<P> {
    List<P> list(Map map);

    P listById(String id);

    int insertOne(P po);

    int updateOne(@Param("id") String id, @Param("po") P po);

    int deleteById(String id);
}
