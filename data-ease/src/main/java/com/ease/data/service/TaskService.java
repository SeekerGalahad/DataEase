package com.ease.data.service;

import com.ease.data.model.Task;
import com.ease.data.utils.PageUtil;

import java.util.List;

/**
 * @author Wagic
 */
public interface TaskService {

    Integer save(Task mockParams);

    Task get(Long id);

    Integer delete(Long id);

    PageUtil<Task> queryParamsByPage(Task queryParams, Integer pageNum, Integer pageSize);

    List<Task> selectByTaskName(String taskName);

}
