package com.ease.data.controller;

import com.ease.data.model.HDFSFile;
import com.ease.data.service.FileService;
import com.ease.data.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wagic
 */
@RestController
@RequestMapping("/hdfs")
public class FileController {

    @Autowired
    private FileService fileService;


    /**
     * 分页查询
     * @param fileName
     * @param userName
     * @param pageNum
     * @param pageSize
     * @return 分页数据
     **/
    @RequestMapping(value="/getFileList")
    public PageUtil<HDFSFile> getFileList(@RequestParam(value="fileName", required = false) String fileName,
                                          @RequestParam(value = "userName", required = false) String userName,
                                          @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                                          @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        HDFSFile params = new HDFSFile(fileName, userName);
        return fileService.getFileList(params, pageNum, pageSize);
    }

    /**
     * 获取文件详情
     * @param id 主键id
     * @return 参数说明对象
     **/
    @RequestMapping(value="/fileDetail", method = RequestMethod.GET)
    public HDFSFile get(@RequestParam(value = "id") Long id){
        return fileService.get(id);
    }

    /**
     * 保存参数说明
     * @param file 保存信息
     * @return 保存结果
     **/
    @RequestMapping(value="/uploadFile", method = RequestMethod.POST)
    public Integer uploadFile(@RequestBody HDFSFile file){
        return fileService.uploadFile(file);
    }

    /**
     * 删除存参数说明
     * @param id 主键id
     * @return 删除结果
     **/
    @RequestMapping(value="/deleteTask/{id}", method = RequestMethod.DELETE)
    public Integer delete(@PathVariable(value = "id") Long id){
        if (fileService.get(id) == null){
            return -1;
        }
        return fileService.delete(id);
    }
}
