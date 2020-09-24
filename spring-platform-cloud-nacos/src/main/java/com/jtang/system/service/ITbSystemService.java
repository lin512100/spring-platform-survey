package com.jtang.system.service;

import com.jtang.system.entity.TbSystem;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
*  服务类
* @author jtang
* @since 2020-09-24
*/
public interface ITbSystemService extends IService<TbSystem> {

    /** 全量回滚 */
    void all();

    /** 单个回滚 */
    void half();

}
