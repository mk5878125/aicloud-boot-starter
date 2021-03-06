/** 
 * @Copyright (c) 2018, 周天琪 johnny_ztq@163.com.com All Rights Reserved.
 * @PackageName:com.aicloud 
 * @Date:2018年2月8日上午9:39:02  
 * 
*/

package com.aicloud.boot.jpa.support;

import com.aicloud.boot.jpa.QueryRepository;
import com.aicloud.boot.jpa.SpecificationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 包含易使用查询功能的Repository实现基类
 */
public class JpaQueryRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements QueryRepository<T, ID> {

    public JpaQueryRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public Page<T> query(Map<String, Object> searchParams, Pageable pageable) {
        return findAll(SpecificationUtils.parse(searchParams), pageable);
    }

    @Override
    public List<T> query(Map<String, Object> searchParams) {
        return findAll(SpecificationUtils.parse(searchParams));
    }

    @Override
    public List<T> query(Map<String, Object> searchParams, Sort sort) {
        return findAll(SpecificationUtils.parse(searchParams), sort);
    }

    @Override
    public long count(Map<String, Object> searchParams) {
        return count(SpecificationUtils.parse(searchParams));
    }
}
