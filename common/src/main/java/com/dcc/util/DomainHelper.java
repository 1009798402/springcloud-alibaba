package com.dcc.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jianchun.chen
 * @date 2020/11/16 10:28
 *     <p>-----DTO,VO,Entity转换的工具类
 */
@Slf4j
public class DomainHelper {

  public static <D, E> E dto2Entity(D d, Class<E> cls) {

    if (d == null) {
      return null;
    }

    E e = null;
    try {
      e = cls.getDeclaredConstructor().newInstance();
      BeanUtils.copyProperties(d, e);
    } catch (InstantiationException
        | IllegalAccessException
        | InvocationTargetException
        | NoSuchMethodException instantiationException) {
      instantiationException.printStackTrace();
      log.error("dto2Entity 异常");
    }

    return e;
  }

  public static <E, V> V entity2Vo(E e, Class<V> cls) {

    if (e == null) {
      return null;
    }

    V v = null;
    try {
      v = cls.getDeclaredConstructor().newInstance();
      BeanUtils.copyProperties(e, v);
    } catch (InstantiationException
        | IllegalAccessException
        | InvocationTargetException
        | NoSuchMethodException instantiationException) {
      instantiationException.printStackTrace();
      log.error("entity2Vo 异常");
    }

    return v;
  }

  public static <D, E> List<E> dtoArray2Entity(D[] ds, Class<E> cls) {

    List<E> es = new ArrayList<>();
    if (ds == null || ds.length == 0) {
      return es;
    }

    for (D d : ds) {
      E e = dto2Entity(d, cls);
      if (e != null) {
        es.add(e);
      }
    }

    return es;
  }

  public static <D, E> List<E> dtoList2Entity(List<D> ds, Class<E> cls) {

    List<E> es = new ArrayList<>();
    if (ds == null || ds.size() == 0) {
      return es;
    }

    ds.forEach(
        d -> {
          E e = dto2Entity(d, cls);
          if (e != null) {
            es.add(e);
          }
        });

    return es;
  }

  public static <E, V> List<V> entity2VoList(List<E> es, Class<V> cls) {

    List<V> vs = new ArrayList<>();
    if (es == null || es.size() == 0) {
      return vs;
    }

    es.forEach(
        e -> {
          V v = entity2Vo(e, cls);
          if (v != null) {
            vs.add(v);
          }
        });

    return vs;
  }
}
