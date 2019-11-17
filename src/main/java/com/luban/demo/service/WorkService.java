package com.luban.demo.service;

import com.luban.demo.domain.Work;
import com.luban.demo.dto.WorkDto;

import java.util.List;

/**
 * @author Yang Hao
 * @date 2019/11/17 20:47
 */
public interface WorkService {
    /**
     * 查询所有work 列表，暂不考虑分页
     *
     * @return
     */
    List<Work> listAllWorks();

    /**
     * 创建work
     *
     * @param workDto
     * @return
     */
    WorkDto createWork(WorkDto workDto);

    /**
     * 更新work
     *
     * @param workDto
     * @return
     */
    WorkDto updateWork(WorkDto workDto);

    /**
     * 根据Id 查询work
     * @param id
     * @return
     */
    WorkDto findWorkById(Long id);
}
