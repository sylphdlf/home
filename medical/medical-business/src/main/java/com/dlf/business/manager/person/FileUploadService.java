package com.dlf.business.manager.person;

import javax.servlet.http.HttpServletRequest;

import com.dlf.model.dto.GlobalResultDTO;

public interface FileUploadService {
	/**
	 *	文件上传
	 */
	GlobalResultDTO uploadFile(HttpServletRequest request);
	/**
	 *	文件下载
	 */
	GlobalResultDTO downloadFile(HttpServletRequest request);
}
