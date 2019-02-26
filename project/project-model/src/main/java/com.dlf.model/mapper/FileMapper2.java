package com.dlf.model.mapper;

import com.dlf.model.dto.file.FileDTO;
import com.dlf.model.po.File;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper2 extends FileMapper{

    List<File> queryByUser(File file);

    List<File> queryByParams(File file);
}