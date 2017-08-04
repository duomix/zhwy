package org.aisno.core.mogilefs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.log4j.BasicConfigurator;
import org.springframework.context.annotation.Configuration;
import com.guba.mogilefs.BadHostFormatException;
import com.guba.mogilefs.MogileFS;
import com.guba.mogilefs.NoTrackersException;
import com.guba.mogilefs.StorageCommunicationException;
import com.guba.mogilefs.TrackerCommunicationException;

/**
 * MogileFS分布式文件服务客户端
 * @author XZY
 * 2017-1-22-下午2:09:00
 */
@Configuration
public class MogileFSutils {
	
//	public static void main(String[] args) throws IOException {
//		String n = "TC+++Primer.pdf";
//		
//		//del(n);
//		
////		File f = new File("/root/桌面/"+n);
////		InputStream in = new FileInputStream(f);
////		upload(n,"",in);
//		
//		
//		byte[] bs = download(n);
//		// 确定写出文件的位置
//		File file1 = new File("/home/下载"+n);
//		// 建立输出字节流
//		FileOutputStream fos = new FileOutputStream(file1);
//		// 用FileOutputStream 的write方法写入字节数组
//		fos.write(bs);
//		System.out.println("写入成功");
//		// 为了节省IO流的开销，需要关闭
//		fos.close();
//	}
	
	static MogileFS mfs = null;
	static{
		try {
			BasicConfigurator.configure();
			mfs = new MogileFS(PropertiesUtils.getProperty("spring.mogilefs.domain"),new String[] { PropertiesUtils.getProperty("spring.mogilefs.addressesCsv") }, true);
		} catch (NoTrackersException e) {
			e.printStackTrace();
		} catch (BadHostFormatException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 上传文件
	 * @param key 文件key
	 * @param storageClass 
	 * @param file   文件
	 */
	public static void upload(String key,String storageClass, File file){
		if (key != null && file != null && file.exists()) {
			OutputStream out = null;
			FileInputStream in = null;
			try {
				storageClass = storageClass == null ? "" : storageClass;
				out = mfs.newFile(key, storageClass, file.length());
				in = new FileInputStream(file);
				byte[] buffer = new byte[1024];
				int count = 0;
				while ((count = in.read(buffer)) >= 0) {
					out.write(buffer, 0, count);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NoTrackersException e) {
				e.printStackTrace();
			} catch (TrackerCommunicationException e) {
				e.printStackTrace();
			} catch (StorageCommunicationException e) {
				e.printStackTrace();
			}finally{
				try {
					if(in != null){
							in.close();
					}
					if(out != null){
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void upload(String key,String storageClass, InputStream in){
		if (key != null && in != null) {
			OutputStream out = null;
			try {
				storageClass = storageClass == null ? "" : storageClass;
				out = mfs.newFile(key, storageClass, in.available());
				byte[] buffer = new byte[1024];
				int count = 0;
				while ((count = in.read(buffer)) >= 0) {
					out.write(buffer, 0, count);
				}
			} catch (NoTrackersException | TrackerCommunicationException
					| StorageCommunicationException | IOException e) {
				e.printStackTrace();
			}finally{
				try {
					if(in != null){
							in.close();
					}
					if(out != null){
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	
	
	
	/**
	 * 下载文件
	 * @param key   文件key
	 * @return
	 */
	public static byte[] download(String key){
		byte[] bs = null;
		try {
			if(key != null && !"".equals(key)){
				bs = mfs.getFileBytes(key);
			}
		} catch (NoTrackersException | TrackerCommunicationException
				| StorageCommunicationException | IOException e) {
			e.printStackTrace();
		}
		return bs;
	}
	public static InputStream downloadFormInputStream(String key){
		InputStream in = null;
			if(key != null && !"".equals(key)){
				try {
					in = mfs.getFileStream(key);
				} catch (NoTrackersException | TrackerCommunicationException
						| StorageCommunicationException e) {
					e.printStackTrace();
				}
			}
		return in;
	}
	/**
	 * @param key
	 * @param filepath   文件保存路径
	 */
	public static String download(String key, String filepath){
		FileOutputStream fos = null;
		try {
			byte[] bs = null;
			if(key != null && !"".equals(key) && filepath != null){
				bs = mfs.getFileBytes(key);
				// 确定写出文件的位置
				File file = new File(filepath);
				// 建立输出字节流
				fos = new FileOutputStream(file);
				// 用FileOutputStream 的write方法写入字节数组
				fos.write(bs);
			}
		} catch (NoTrackersException | TrackerCommunicationException
				| StorageCommunicationException | IOException e) {
			e.printStackTrace();
		}finally{
			if(fos != null){
				// 为了节省IO流的开销，需要关闭
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return filepath;
	}
	
	/**
	 * 删除文件
	 * @param key
	 */
	public static void del(String key){
		try {
			mfs.delete(key);
		} catch (NoTrackersException e) {
			e.printStackTrace();
		}
	}
}