package org.aisno.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aisino.utils.common.JsonUtil;
import org.aisino.utils.common.UUID;
import org.aisno.core.mogilefs.MogileFSutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 文件控制器
 */
@Controller
public class FileController {

	@Autowired
	MogileFSutils mogileFSutils;

	@RequestMapping("/test")
	public String file() {
		return "file";
	}

	/**
	 * 单文件上传
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			return saveFile(file);
		} else {
			return "上传失败,文件为空!";
		}
	}

	/**
	 * 多文件上传
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
	@ResponseBody
	public List<String> handleFileUpload(HttpServletRequest request) {
		MultipartHttpServletRequest r_file = (MultipartHttpServletRequest) request;
		List<MultipartFile> files = r_file.getFiles("file");
		MultipartFile file = null;
		List<String> keys = new ArrayList<String>();
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
				try {
					keys.add(saveFile(file));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return keys;
	}

	/**
	 * 下载文件
	 * 
	 * @param filePath
	 * @param showNam
	 * @param isOnLine
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("download.do")
	public void downloadFile(String key, HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ key + "\"");
		OutputStream outStream = null;
		try {
			outStream = response.getOutputStream();
			byte[] bs = mogileFSutils.download(key);
			if (bs != null) {
				outStream.write(bs);
				outStream.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outStream != null) {
				try {
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param key
	 * @return
	 */
	@RequestMapping("delfile.do")
	public String delFile(String key) {
		String r = "0";
		try {
			if (key != null && !"".equals(key)) {
				mogileFSutils.del(key);
				r = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * 保存文件
	 * 
	 * @param file
	 * @return
	 */
	public String saveFile(MultipartFile file) {
		String key;
		String filename = file.getOriginalFilename();// 上传文件名
		try {
			String hz = "";// 文件后缀
			if (filename.lastIndexOf(".") > -1) {
				hz = filename.substring(filename.lastIndexOf("."),
						filename.length());
			}
			key = UUID.GetUUID32() + hz;
			mogileFSutils.upload(key, null, file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			return "上传失败," + e.getMessage();
		}
		return "{\"key\":\"" + key + "\", \"filename\":\"" + filename + "\"}";
	}

}
