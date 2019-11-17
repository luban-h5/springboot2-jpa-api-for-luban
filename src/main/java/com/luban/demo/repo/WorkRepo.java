package com.luban.demo.repo;

import com.luban.demo.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Yang Hao
 * @date 2019/11/17 20:51
 */
public interface WorkRepo extends JpaRepository<Work, Long>, JpaSpecificationExecutor<Work> {
}
