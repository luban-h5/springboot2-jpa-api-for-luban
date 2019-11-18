package com.luban.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.luban.demo.domain.Work;
import com.luban.demo.dto.WorkDto;
import com.luban.demo.repo.WorkRepo;
import com.luban.demo.service.WorkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yang Hao
 * @date 2019/11/17 20:48
 */
@Service
@Slf4j
public class WorkServiceImpl implements WorkService {

    @Resource
    WorkRepo workRepo;

    @Override
    public List<WorkDto> listAllWorks(WorkDto dto) {
        Work work = toWork(dto);
        Example<Work> example = Example.of(work);
        List<Work> works = workRepo.findAll(example);
        return works.stream().map(this::toWorkDto).collect(Collectors.toList());
    }

    @Override
    public Page<WorkDto> listWorks(WorkDto dto, Pageable pageable) {
        return null;
    }

    @Override
    public WorkDto createWork(WorkDto workDto) {
        Work work = toWork(workDto);
        return toWorkDto(workRepo.save(work));
    }

    @Override
    public WorkDto updateWork(WorkDto dto) {
        Work work = workRepo.findOne(dto.getId());
        if (!StringUtils.isEmpty(dto.getTitle())) {
            work.setTitle(dto.getTitle());
        }
        if (!StringUtils.isEmpty(dto.getDescription())) {
            work.setDescription(dto.getDescription());
        }
        if (!StringUtils.isEmpty(dto.getCoverImageUrl())) {
            work.setCoverImageUrl(dto.getCoverImageUrl());
        }
        if (!CollectionUtils.isEmpty(dto.getPages())) {
            work.setPages(JSON.toJSONString(dto.getPages()));
        }
        if (dto.isTemplate()) {
            work.setTemplate(true);
        }
        if (dto.isPublish()) {
            work.setPublish(true);
        }
        work.setUpdateTime(new Date());
        return toWorkDto(workRepo.save(work));
    }

    @Override
    public WorkDto findWorkById(Long id) {
        Work work = workRepo.findOne(id);
        return toWorkDto(work);
    }

    @Override
    public void deleteWorkById(Long id) {
        workRepo.delete(id);
    }

    private Work toWork(WorkDto workDto) {
        Work work = new Work();
        BeanUtils.copyProperties(workDto, work);
        if (!CollectionUtils.isEmpty(workDto.getPages())) {
            work.setPages(JSON.toJSONString(workDto.getPages()));
        }
        return work;
    }

    private WorkDto toWorkDto(Work work) {
        WorkDto workDto = new WorkDto();
        BeanUtils.copyProperties(work, workDto);
        if (!StringUtils.isEmpty(work.getPages())) {
            workDto.setPages(JSON.parseArray(work.getPages()));
        }
        return workDto;
    }
}
