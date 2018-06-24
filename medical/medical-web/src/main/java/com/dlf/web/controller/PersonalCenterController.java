package com.dlf.web.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.enums.RedisPrefixEnums;
import com.dlf.model.dto.person.IdNumberDTO;
import com.dlf.model.dto.person.IdNumberResultDTO;
import com.dlf.model.dto.person.PayMethodDTO;
import com.dlf.model.dto.person.PayMethodResultDTO;
import com.dlf.model.dto.person.PersonInfoDTO;
import com.dlf.model.dto.person.PersonInfoResultDTO;
import com.dlf.model.dto.user.UserReqDTO;
import com.dlf.business.manager.person.FileUploadService;
import com.dlf.business.manager.person.PersonalCenterService;
import com.dlf.business.manager.user.UserService;

/**
 * 个人中心接口
 */
@Controller
@RequestMapping(value = "/person")
public class PersonalCenterController {

	@Autowired
	private PersonalCenterService PersonalCenterService;
	@Autowired
	private UserService userService;
	@Autowired
	private FileUploadService fileUploadService;

	/**
	 * 查询邮箱和手机号信息接口
	 */
	@RequestMapping(value = "/queryMailbox", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResultDTO<PersonInfoResultDTO> queryPhonenumberAndMailbox(@RequestBody PersonInfoDTO reqDTO) {
		return PersonalCenterService.queryPhonenumberAndMailbox(reqDTO);
	}

	/**
	 * 添加邮箱或者手机号信息
	 */
	@RequestMapping(value = "/updateMailbox", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResultDTO updatePhonenumberAndMailbox(@RequestBody PersonInfoDTO personInfo) {
		return PersonalCenterService.updatePhonenumberAndMailbox(personInfo, false,
				RedisPrefixEnums.REGISTER_MESSAGE.getCode());
	}

	/**
	 * 重置登陆密码
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResultDTO resetPassword(@RequestBody UserReqDTO reqDTO) {
		return userService.resetPassword(reqDTO);
	}

	/**
	 * 添加更新身份信息接口
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/updateIdNumber", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResultDTO updateIdNumber(@RequestBody IdNumberDTO idNumber) {
		return PersonalCenterService.updateIdNumber(idNumber);
	}

	/**
	 * 查询身份信息接口
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/selectIdNumber", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResultDTO<IdNumberResultDTO> selectIdNumber(@RequestBody PersonInfoDTO personInfo) {
		return PersonalCenterService.selectIdNumber(personInfo);
	}

	/**
	 * 添加更新支付方式接口
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/updatePayMethod", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResultDTO updatePayMethod(@RequestBody PayMethodDTO payMethod) {
		return PersonalCenterService.updatePayMethod(payMethod);
	}

	/**
	 * 查询支付方式接口
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/selectPayMethod", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResultDTO<PayMethodResultDTO> selectPayMethod(@RequestBody PersonInfoDTO personInfo) {
		return PersonalCenterService.selectPayMethod(personInfo);
	}

	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 图片上传
	 * 
	 * @param
	 * @return
	 */
	@PostMapping(value = "/uploadFile")
	@ResponseBody
	public GlobalResultDTO uploadZipBag(HttpServletRequest request, HttpServletResponse response) {
		return fileUploadService.uploadFile(request);
	}

	// 动态获取图片---服务端获取图片显示在页面中
	@RequestMapping("/testpic")
	public void testpic(HttpServletResponse response) throws IOException {
		FileInputStream fis = null;
		File file = new File("D://test2//16602122313999.png");
		fis = new FileInputStream(file);
		response.setContentType("image/jpg"); // 设置返回的文件类型
		response.setHeader("Access-Control-Allow-Origin", "*");// 设置该图片允许跨域访问
		IOUtils.copy(fis, response.getOutputStream());
	}
	// /**
	// * 图片上传
	// * @param
	// * @return
	// */
	// @PostMapping(value = "/downloadFile")
	// @ResponseBody
	// public GlobalResultDTO downloadZipBag(HttpServletRequest request,
	// HttpServletResponse response){
	// return fileUploadService.downloadFile(request);
	// }

	// @GetMapping(value = "/downloadFile")
	// @ResponseBody
	// public ResponseEntity<byte[]> downloadFile(HttpServletResponse response,
	// HttpServletRequest request)
	// throws Exception {
	//
	// ServletContext context = request.getServletContext();
	//// String path = "D:/test2/16602122313888.png";
	//// String group = path.substring(0, path.indexOf("/"));
	//// String subPath = path.substring(path.indexOf("/") + 1);
	// File file = new File("D://test2//16602122313999.png");
	// InputStream input = new FileInputStream(file);
	// byte[] obj = new byte[input.available()];
	//
	//
	// String ss="16602122313999.png";
	// String mimeType = context.getMimeType("16602122313999.png");
	// if (mimeType == null) {
	//
	// mimeType = "application/octet-stream";
	// }
	// // 用来封装响应头信息
	// HttpHeaders responseHeaders = new HttpHeaders();
	// // 下载的附件的类型
	// responseHeaders.setContentType(MediaType.parseMediaType(mimeType));
	// // 下载的附件的名称
	// responseHeaders.setContentDispositionFormData("attachment",
	// new String(ss.getBytes("utf-8"), "ISO8859-1"));
	//
	//// response.setHeader("content-disposition",
	//// "attachment;filename=" + URLEncoder.encode(ss, "utf-8"));
	//// responseHeaders.add("filename", ss);
	// //
	// // /**
	// // * arg1:需要响应到客户端的数据 arg2:设置本次请求的响应头 arg3:响应到客户端的状态码
	// // ***/
	//
	// // response.addHeader("Content-Disposition",
	// // downloadType+";filename=\"a.jpg\"");
	// response.setCharacterEncoding("UTF-8");
	// return new ResponseEntity<byte[]>(obj, responseHeaders, HttpStatus.CREATED);
	//
	// }

}
