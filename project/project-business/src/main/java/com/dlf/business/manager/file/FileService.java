package com.dlf.business.manager.file;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.file.FileSearchDTO;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    /**
     * 文件上传
     * @param file
     * @return
     */
    GlobalResultDTO upload(MultipartFile file);

    GlobalResultDTO queryListByParams(FileSearchDTO searchDTO);
}
