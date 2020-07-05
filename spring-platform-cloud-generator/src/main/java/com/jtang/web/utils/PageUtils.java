package com.jtang.web.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jtang.base.utils.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * description 分页工具类
 * @date 2020/3/2 11:39
 * @author LinJinTang
 */
public class PageUtils {

    public static <T> Pagination converterToPagination(IPage<T> iPage) {
        return new Pagination<>(iPage.getTotal(), iPage.getRecords());
    }

    /**
     * 手动分页类
     * @param datas 数据列
     * @param pageSize 行数
     * @param pageNo 页码
     * @param <T> 数据
     * @return T
     */
    public static <T> List<T> getPageSizeDataForRelations(List<T> datas, int pageSize, int pageNo){
        //起始截取数据位置
        int startNum = (pageNo-1)* pageSize+1 ;
        if(startNum > datas.size()){
            return new ArrayList<>();
        }
        List<T> res = new ArrayList<>();
        int rum = datas.size() - startNum;
        if(rum < 0){
            return new ArrayList<>();
        }
        if(rum == 0){
            //说明正好是最后一个了
            int index = datas.size() -1;
            res.add(datas.get(index));
            return res;
        }
        if(rum / pageSize >= 1){
            //剩下的数据还够1页，返回整页的数据
            //截取从startNum开始的数据
            for(int i=startNum;i<startNum + pageSize;i++){
                res.add(datas.get(i-1));
            }
            return res;
            //不够一页，直接返回剩下数据
        }else if(rum / pageSize == 0){
            for(int j = startNum ;j<=datas.size();j++){
                res.add(datas.get(j-1));
            }
            return res;
        }else{
            return new ArrayList<>();
        }
    }
}
