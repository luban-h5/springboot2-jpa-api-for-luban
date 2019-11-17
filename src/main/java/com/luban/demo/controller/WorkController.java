package com.luban.demo.controller;

import com.luban.demo.dto.WorkDto;
import com.luban.demo.request.WorkCreateRequest;
import com.luban.demo.request.WorkUpdateRequest;
import com.luban.demo.service.WorkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Yang Hao
 * @date 2019/11/17 20:42
 */
@RestController
@RequestMapping("/works")
@Slf4j
public class WorkController {
    @Resource
    private WorkService workService;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WorkDto findWorkById(@PathVariable Long id) {
        return workService.findWorkById(id);
    }


    @RequestMapping(method = RequestMethod.GET)
    public List listAllWorks() {
        return workService.listAllWorks();
    }

    /**
     * ResponseStatus 和strapi.js(鲁班官方后端框架) response保持一致
     *
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public WorkDto createWork(@RequestBody @Valid WorkCreateRequest request) {
        WorkDto workDto = new WorkDto();
        BeanUtils.copyProperties(request, workDto);
        return workService.createWork(workDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public WorkDto updateWork(@PathVariable Long id, @RequestBody @Valid WorkUpdateRequest request) {
        WorkDto workDto = new WorkDto();
        workDto.setId(id);
        BeanUtils.copyProperties(request, workDto);
        return workService.updateWork(workDto);
    }


}
