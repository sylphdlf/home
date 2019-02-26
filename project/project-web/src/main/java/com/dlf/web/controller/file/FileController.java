package com.dlf.web.controller.file;

import com.dlf.business.anno.FunDescriptionAnno;
import com.dlf.business.anno.MenuDescriptionAnno;
import com.dlf.business.anno.UrlPermissionIgnoreAnno;
import com.dlf.business.manager.dashboard.DashboardService;
import com.dlf.business.manager.file.FileService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.file.FileSearchDTO;
import com.dlf.model.dto.spider.WeatherSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@MenuDescriptionAnno(name = "文件列表", parent = "文件管理")
public class FileController {

    @Autowired
    private FileService fileService;

//    queryListByParams
    @FunDescriptionAnno(name = "查询文件列表")
    @RequestMapping(value = "/queryListByParams",method = RequestMethod.POST)
    public GlobalResultDTO queryListByParams(@RequestBody FileSearchDTO searchDTO){
        return fileService.queryListByParams(searchDTO);
    }

//    @UrlPermissionIgnoreAnno
    @FunDescriptionAnno(name = "文件上传")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public GlobalResultDTO upload(@RequestParam("file") MultipartFile file){
        return fileService.upload(file);
    }
}
