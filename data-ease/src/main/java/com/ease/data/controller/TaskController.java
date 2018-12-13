package com.ease.data.controller;

import com.ease.data.model.Task;
import com.ease.data.service.TaskService;
import com.ease.data.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wagic
 */
@RestController
@RequestMapping("/comic/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * 分页查询
     * @param taskName
     * @param taskDesc
     * @param pageNum
     * @param pageSize
     * @return 分页数据
     **/
    @RequestMapping(value="/queryList")
    public PageUtil<Task> queryList(@RequestParam(value="taskName", required = false) String taskName,
                                    @RequestParam(value = "taskDesc", required = false) String taskDesc,
                                    @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                                    @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        Task params = new Task(taskName, taskDesc);
        return taskService.queryParamsByPage(params, pageNum, pageSize);
    }

    /**
     * 获取任务详情
     * @param id 主键id
     * @return 参数说明对象
     **/
    @RequestMapping(value="/taskDetail", method = RequestMethod.GET)
    public Task get(@RequestParam(value = "id") Long id){
        return taskService.get(id);
    }

    /**
     * 保存参数说明
     * @param task 保存信息
     * @return 保存结果
     **/
    @RequestMapping(value="/saveTask", method = RequestMethod.POST)
    public Integer save(@RequestBody Task task){
        return taskService.save(task);
    }

    /**
     * 删除存参数说明
     * @param id 主键id
     * @return 删除结果
     **/
    @RequestMapping(value="/deleteTask/{id}", method = RequestMethod.DELETE)
    public Integer delete(@PathVariable(value = "id") Long id){
        if (taskService.get(id) == null){
            return -1;
        }
        return taskService.delete(id);
    }
}
