package com.dlf.business.manager.file.impl;

import com.dlf.business.exception.MyException;
import com.dlf.business.manager.file.FileService;
import com.dlf.common.utils.BeanUtils;
import com.dlf.common.utils.CodeGenerateUtils;
import com.dlf.common.utils.Md5Utils;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.file.FileDTO;
import com.dlf.model.dto.file.FileSearchDTO;
import com.dlf.model.dto.user.UserResDTO;
import com.dlf.model.enums.GlobalResultEnum;
import com.dlf.model.enums.file.FileResultEnums;
import com.dlf.model.mapper.FileMapper;
import com.dlf.model.mapper.FileMapper2;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    FileMapper2 fileMapper;
    @Value("${file.upload.root}")
    private String fileUploadRoot;

    @Override
    @Transactional
    public GlobalResultDTO upload(MultipartFile file) {
        String md5Str = "";
        String originalFileName = file.getOriginalFilename();
        //MD5加密
        try {
            md5Str = Md5Utils.transferFromBytes(file.getBytes());
        } catch (Exception e) {
            return GlobalResultDTO.FAIL(FileResultEnums.MD5_ENCODER_FAIL.getMsg());
        }
        //查询是否有重复的记录
        //是否属于当前用户,是则提示重复上传，否则做一份数据库的拷贝
        //获取用户id
        UserResDTO resDTO = (UserResDTO) SecurityUtils.getSubject().getPrincipal();
        com.dlf.model.po.File searchFile = new com.dlf.model.po.File();
        searchFile.setMd5(md5Str);
        List<com.dlf.model.po.File> fileList = fileMapper.queryByParams(searchFile);
        //重复上传，直接返回
        if(!CollectionUtils.isEmpty(fileList)
                && fileList.get(0).getCreateUserId().longValue() == resDTO.getId().longValue()){
            return GlobalResultDTO.FAIL(FileResultEnums.FILE_ALREADY_EXIST_BY_USER.getMsg());
        //非当前用户上传的文件，复制数据库中存在的值
        }else if(!CollectionUtils.isEmpty(fileList) && fileList.get(0).getCreateUserId().longValue() != resDTO.getId().longValue()){
            com.dlf.model.po.File thisFile = new com.dlf.model.po.File();
            //把ID设置为空
            fileList.get(0).setId(null);
            fileList.get(0).setMd5("");
            BeanUtils.copyProperties(fileList.get(0), thisFile);
            //设置自己的文件名
            thisFile.setOrgName(originalFileName);
            fileMapper.insert(thisFile);
            return GlobalResultDTO.SUCCESS();
        }
        //防重复文件名
        String fakeName = CodeGenerateUtils.codeGenerateByPrefix("f");
        //FIXME 可能会有没有后缀的文件
        String suffix = "";
        if(originalFileName.lastIndexOf(".") != -1) {
            suffix = originalFileName.substring(originalFileName.lastIndexOf(".") + 1, originalFileName.length());
        }else{
            suffix = "other";
        }
        //文件夹路径
        String folderPath = fileUploadRoot + File.separator + suffix;
        String filePathRelative = suffix + File.separator + fakeName + "." + suffix;
        //保存文件的路径
        String filePath = fileUploadRoot + File.separator + filePathRelative;
        try {
            //创建文件夹
            File folder = new File(folderPath);
            if(!folder.exists()){
                boolean mkdirs = folder.mkdirs();
            }
            file.transferTo(new File(filePath));
        }catch (Exception e){
            e.printStackTrace();
            return GlobalResultDTO.FAIL(e.getMessage());
        }
        try {
            //TODO 查询md5，如果存在则复制一份文件的备份
            //保存到数据库
            com.dlf.model.po.File fileData = new com.dlf.model.po.File();
            fileData.setName(fakeName);
            fileData.setOrgName(originalFileName);
            fileData.setPathRoot(fileUploadRoot);
            fileData.setSize(file.getSize());
            fileData.setSuffix(suffix);
            fileData.setPathRelative(filePathRelative);
            fileData.setMd5(md5Str.toUpperCase());
            int count = fileMapper.insert(fileData);
            if(count != 1){
                throw new MyException("database insert failed");
            }
            return GlobalResultDTO.SUCCESS();
        }catch (Exception e){
            //此处做文件的回滚操作
            File fileDel = new File(filePath);
            if(fileDel.exists()){
                boolean isDelete = fileDel.delete();
            }
        }
        return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getMsg());
    }

    @Override
    public GlobalResultDTO queryListByParams(FileSearchDTO searchDTO) {
        try {
            com.dlf.model.po.File file = new com.dlf.model.po.File();
            BeanUtils.copyProperties(searchDTO, file);
            PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
            List<FileDTO> list = fileMapper.queryListByUser(file);
            PageInfo<FileDTO> pageInfo = new PageInfo<FileDTO>(list);
            return new GlobalResultDTO(pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            return GlobalResultDTO.FAIL();
        }
    }
}
