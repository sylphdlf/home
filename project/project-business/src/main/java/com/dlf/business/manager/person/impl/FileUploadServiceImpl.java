package com.dlf.business.manager.person.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dlf.business.manager.person.FileUploadService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.person.PicturePathDTO;
import com.dlf.model.mapper.PersonalCenterMapper;
@Service
public class FileUploadServiceImpl implements FileUploadService{
	
	
	//从配置文件获取文件路径
	@Value("${filePath}")
	private String filePath;
	//从配置文件获取单个文件大小限制
	@Value("${fileSize}")
	private long fileSize;
    @Resource
    PersonalCenterMapper PersonalCenterMapper;
	@Override
	public GlobalResultDTO uploadFile(HttpServletRequest request) {
		GlobalResultDTO globalResult = new GlobalResultDTO();
		InputStream mfIn = null;
		BufferedOutputStream bfOut = null;
		try {
			// 强转
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile mf = multipartRequest.getFile("fileName");
	    	if(mf.isEmpty()){
	    		globalResult.setCode("1");
	    		globalResult.setMsg("没有上传任何数据");
	            return globalResult;
	        }
	        int size = (int) mf.getSize();
	        if (size>=fileSize) {
	    		globalResult.setCode("1");
	    		globalResult.setMsg("文件大小超出限制");
	            return globalResult;
			}
		    Subject subject = SecurityUtils.getSubject();
	        //获得文件类型（可以判断如果不是图片，禁止上传）   
	        String contentType=mf.getContentType();  
	        //获得文件后缀名   
	        String suffixName=contentType.substring(contentType.indexOf("/")+1);     
	        //获取请求头参数，来标记是正面还是反面身份证照片
	        String sign= request.getHeader("sign");
	        String username= request.getHeader("username");
	        String photoid =username+sign;
	        String fileName = username+"_"+sign+"."+suffixName;
	        String path = filePath ;
	        File dest = new File(path + "/" + fileName);
	        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
	            dest.getParentFile().mkdir();
	        }
			mfIn = mf.getInputStream();
			bfOut = new BufferedOutputStream(new FileOutputStream(dest));	
			// 输出数据
			int len = 0;
			byte[] b = new byte[1024*1024*5];
			while ((len = mfIn.read(b)) > 0) {
				bfOut.write(b, 0, len);
			}
			bfOut.flush();// 刷出数据
			bfOut.close();
			mfIn.close();
			//数据库插入数据
			PicturePathDTO picturePath = new PicturePathDTO();
			picturePath.setFacepath(fileName);
			picturePath.setUsername(username);
			picturePath.setPhotoid(photoid);

			picturePath.setUpdateTime(new Date());
			//先更新再插入
			int a =PersonalCenterMapper.updatePicturePath(picturePath);
			if (a==0) {
				picturePath.setCreateTime(new Date());
				PersonalCenterMapper.insertPicturePath(picturePath);
			}
			globalResult.SUCCESS();
		} catch (Exception e) {
			e.printStackTrace();
			globalResult.setCode("1");
    		globalResult.setMsg(e.toString());
            return globalResult;
		}finally {		
			try {
				if(bfOut!=null){
					bfOut.close();
				}
				bfOut = null;
			} catch (Exception e) {
				e.printStackTrace();
			}			
			try {
				if(mfIn!=null){
					mfIn.close();
				}
				mfIn = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return globalResult;
	}
	@Override
	public GlobalResultDTO downloadFile(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
