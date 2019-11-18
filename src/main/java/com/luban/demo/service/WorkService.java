package com.luban.demo.service;

import com.luban.demo.dto.WorkDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Yang Hao
 * @date 2019/11/17 20:47
 */
public interface WorkService {

    /**
     * 查询所有work 列表，暂不考虑分页
     *
     * @param dto
     * @return
     */
    List<WorkDto> listAllWorks(WorkDto dto);

    /**
     * 分页查询works
     *
     * @param dto
     * @param pageable
     * @return
     */
    Page<WorkDto> listWorks(WorkDto dto, Pageable pageable);

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
     *
     * @param id
     * @return
     */
    WorkDto findWorkById(Long id);

    /**
     * 删除作品
     *
     * @param id
     */
    void deleteWorkById(Long id);

    /**
     * 设置为模板
     *
     * @param id
     * @return
     */
    WorkDto markWorkAsTemplate(Long id);

    /**
     * 统计作品总数
     *
     * @return
     */
    Long countWork();
}
