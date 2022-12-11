package cn.zxlee.znotifyapi.service.base;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;

import java.util.List;
import java.util.Map;

public interface IBaseService<V, B, PB> {
    List<V> list(Map map);

    PageResultVO<V> listByPage(Map map, PB pageBO);

    int saveOne(String token, B bo);

    int updateOne(Map map, String id, B bo);

    int deleteById(Map map, String id);
}
