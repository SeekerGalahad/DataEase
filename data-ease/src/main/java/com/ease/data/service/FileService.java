package com.ease.data.service;

import com.ease.data.model.HDFSFile;
import com.ease.data.utils.PageUtil;

public interface FileService {

    /**
     * 获取文件列表
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageUtil<HDFSFile> getFileList(HDFSFile params, Integer pageNum, Integer pageSize);

    /**
     * 获取文件详情
     * @param id
     * @return
     */
    HDFSFile get(Long id);

    /**
     * 上传文件
     * @param file
     * @return
     */
    Integer uploadFile(HDFSFile file);

    /**
     * 删除文件
     *
     * @param id
     * @return
     */
    Integer delete(Long id);
}
