package com.luban.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.luban.demo.domain.Work;
import com.luban.demo.dto.WorkDto;
import com.luban.demo.repo.WorkRepo;
import com.luban.demo.service.WorkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public List<Work> listAllWorks() {
        return workRepo.findAll();
    }

    @Override
    public WorkDto createWork(WorkDto workDto) {
        Work work = toWork(workDto);
        return toWorkDto(workRepo.save(work));
    }

    @Override
    public WorkDto updateWork(WorkDto workDto) {
        Work work = workRepo.findOne(workDto.getId());
        BeanUtils.copyProperties(workDto, work);
        work.setPages(JSON.toJSONString(workDto.getPages()));
        return toWorkDto(workRepo.save(work));
    }

    @Override
    public WorkDto findWorkById(Long id) {
        Work work = workRepo.findOne(id);
        return toWorkDto(work);
    }

    private Work toWork(WorkDto workDto) {
        Work work = new Work();
        BeanUtils.copyProperties(workDto, work);
        work.setPages(JSON.toJSONString(workDto.getPages()));
        return work;
    }

    private WorkDto toWorkDto(Work work) {
        WorkDto workDto = new WorkDto();
        BeanUtils.copyProperties(work, workDto);
        workDto.setPages(JSON.parseArray(work.getPages()));
        return workDto;
    }
}
