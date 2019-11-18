package com.luban.demo.controller;

import com.luban.demo.dto.WorkDto;
import com.luban.demo.request.WorkCreateRequest;
import com.luban.demo.request.WorkQueryRequest;
import com.luban.demo.request.WorkUpdateRequest;
import com.luban.demo.service.WorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping(value = "/works")
@Api(value = "作品管理", tags = {"作品管理"})
@Slf4j
public class WorkController {
    @Resource
    private WorkService workService;

    @ApiOperation("根据workId查询")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WorkDto findWorkById(@PathVariable Long id) {
        return workService.findWorkById(id);
    }


    /**
     * 查询所有work
     *
     * @param request
     * @return
     */
    @ApiOperation("查询所有work")
    @RequestMapping(method = RequestMethod.GET)
    public List<WorkDto> listAllWorks(@Valid @ModelAttribute WorkQueryRequest request) {
        WorkDto dto = new WorkDto();
        BeanUtils.copyProperties(request, dto);
        return workService.listAllWorks(dto);
    }

    /**
     * 分页查询works
     *
     * @param request
     * @return
     */
    @ApiOperation("分页查询works")
    @RequestMapping(value = "/pageable", method = RequestMethod.GET)
    public Page<WorkDto> listWorks(@Valid @ModelAttribute WorkQueryRequest request,
                                   @PageableDefault(sort = {"createdTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        WorkDto dto = new WorkDto();
        BeanUtils.copyProperties(request, dto);
        return workService.listWorks(dto, pageable);
    }

    /**
     * ResponseStatus 和strapi.js(鲁班官方后端框架) response保持一致
     *
     * @param request
     * @return
     */
    @ApiOperation("创建work")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public WorkDto createWork(@RequestBody @Valid WorkCreateRequest request) {
        WorkDto workDto = new WorkDto();
        BeanUtils.copyProperties(request, workDto);
        return workService.createWork(workDto);
    }

    /**
     * 根据workId 修改work
     *
     * @param id
     * @param request
     * @return
     */
    @ApiOperation("更新work")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public WorkDto updateWork(@PathVariable Long id, @RequestBody @Valid WorkUpdateRequest request) {
        WorkDto workDto = new WorkDto();
        workDto.setId(id);
        BeanUtils.copyProperties(request, workDto);
        return workService.updateWork(workDto);
    }

    /**
     * 删除work
     *
     * @param id
     * @return
     */
    @ApiOperation("删除work")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteWorkById(@PathVariable Long id) {
        workService.deleteWorkById(id);
    }

    /**
     * 设为模板
     *
     * @param id
     * @param request
     * @return
     */
    @ApiOperation("设为模板")
    @RequestMapping(value = "/set-as-template/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public WorkDto setTemplateWork(@PathVariable Long id, @RequestBody @Valid WorkUpdateRequest request) {
        WorkDto workDto = new WorkDto();
        workDto.setId(id);
        BeanUtils.copyProperties(request, workDto);
        return workService.updateWork(workDto);
    }


}
