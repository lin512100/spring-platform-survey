package com.jtang.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 属性结构图
 * @date 2020/7/12 0:05
 * @author LinJinTang
 */
public class TreeUtils {
    private static final String ROOT_ID = "0";
    private static final String GET = "get";
    private static final String SET = "set";

    /**
     * 私有构造器
     */
    private TreeUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 使用递归方法建树
     * (如果项目中的各个表的id、父id、封装子对象的list名称均是固定，则可以再次简化，将反射获取属性的过程直接写
     * 死，这样调用时只需传list即可)
     * @param list         需要转化树形的list
     * @param idName       实体类中id的属性名(首字母需要大写,例如属性为id,则传Id)
     * @param parentIdName 实体类中父id的属性名(首字母需要大写,例如属性为parentId,则传ParentId)
     * @param childrenName 实体类中封装树形的子list方法名(首字母需要大写,例如属性为list,则传List)
     * @return 树形list
     */
    public static <T> List<T> buildByRecursive(List<T> list, String idName, String parentIdName, String childrenName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<T> result = new ArrayList<>();
        //遍历封装pid=0的
        for (T t : list) {
            //根据反射，获取父id的值
            String pId = reflect(t.getClass(), GET + parentIdName, t).toString();
            //如果pd=0，即是首层
            if (ROOT_ID.equals(pId)) {
                //递归查询并封装子对象
                result.add(findChildren(t, list, idName, parentIdName, childrenName));
            }
        }
        return result;
    }

    /**
     * 递归查找子节点
     *
     * @param idName       实体类中id的属性名(首字母需要大写)
     * @param parentIdName 实体类中父id的属性名(首字母需要大写)
     * @param bean
     * @param beans
     * @return
     */
    public static <T> T findChildren(T bean, List<T> beans, String idName, String parentIdName, String childrenName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //获取bean的id, bean.getId()
        String id = bean.getClass().getMethod(GET + idName).invoke(bean).toString();
        //获取bean用来封装子对象的list, bean.getList()
        List<T> children = (List) reflect(bean.getClass(), GET + childrenName, bean);
        //递归遍历
        for (T it : beans) {
            //根据反射，获取it的pId的值 it.getParentId()
            String parentId = it.getClass().getMethod(GET + parentIdName).invoke(it).toString();
            //如果是其子对象
            if (id.equals(parentId)) {
                //如果封装子对象的list为空，则创建
                if (children == null) {
                    bean.getClass().getMethod(SET + childrenName, List.class).invoke(bean, new ArrayList<>());
                }
                //重新获取子对象的list
                children = (List) reflect(bean.getClass(), GET + childrenName, bean);
                //继续递归遍历
                children.add(findChildren(it, beans, idName, parentIdName, childrenName));
            }
        }
        return bean;
    }

    /**
     * 根据反射获取属性值
     *
     * @param clazz      实体类名
     * @param methodName 方法名
     * @param t          对象
     * @param <T>        对象类
     * @return
     */
    private static <T> Object reflect(Class clazz, String methodName, T t) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return clazz.getMethod(methodName).invoke(t);
    }

}
