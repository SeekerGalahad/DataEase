package com.ease.data.service.impl;

import com.ease.data.model.HDFSFile;
import com.ease.data.service.FileService;
import com.ease.data.utils.PageUtil;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public PageUtil<HDFSFile> getFileList(HDFSFile params, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public HDFSFile get(Long id) {
        return null;
    }

    @Override
    public Integer uploadFile(HDFSFile file) {
        return null;
    }

    @Override
    public Integer delete(Long id) {
        return null;
    }
}
