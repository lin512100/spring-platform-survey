package com.jtang.resource.service;

import com.jtang.resource.entity.TbResourceAddress;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

/**
* 资源地址 服务类
* @author jtang
* @since 2020-09-07
*/
public interface ITbResourceAddressService extends IService<TbResourceAddress> {

    /**
     * 根据资源UID和类型查询存储信息
     * @param uuid UUID
     * @param channel 渠道
     * @return List<TbResourceAddress>
     * */
    List<TbResourceAddress> getResourceAddress(String uuid, Integer channel, Integer belong);

}
