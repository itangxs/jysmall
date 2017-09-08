package cn.qhjys.mall.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class FileUtil {
	// 上传文件保存路径
	public static final String path = "/upload/";
	// 一次读取多少字节
	public static int bufferSize = 1024 * 8;

	/**
	 * 文件上传
	 * 
	 * @param fileName
	 * @param path
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public static FileState upload4Stream(String fileName, String path, InputStream is) throws Exception {
		FileState state = FileState.UPLOAD_FAILURE;
		FileOutputStream fos = null;
		try {
			path = getDoPath(path);
			mkDir(path);
			fos = new FileOutputStream(path + fileName);
			byte[] buffer = new byte[bufferSize];
			int len = 0;
			while ((len = is.read(buffer)) > 0)
				fos.write(buffer, 0, len);
			state = FileState.UPLOAD_SUCCSSS;
		} catch (FileNotFoundException e) {
			state = FileState.UPLOAD_NOTFOUND;
			throw e;
		} catch (IOException e) {
			state = FileState.UPLOAD_FAILURE;
			throw e;
		} finally {
			if (is != null) {
				is.close();
			}
			if (fos != null) {
				fos.flush();
				fos.close();
			}
		}
		return state;
	}

	/**
	 * 文件上传
	 * 
	 * @param fileName
	 * @param path
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static FileState upload4Stream(String fileName, String path, File file) throws Exception {
		FileState state = FileState.UPLOAD_FAILURE;
		FileInputStream fis = null;
		try {
			long size = file.length();
			if (size <= 0)
				state = FileState.UPLOAD_ZEROSIZE;
			else {
				fis = new FileInputStream(file);
				state = upload4Stream(fileName, path, fis);
			}
		} catch (FileNotFoundException e) {
			state = FileState.UPLOAD_NOTFOUND;
			throw e;
		} catch (IOException e) {
			state = FileState.UPLOAD_FAILURE;
			throw e;
		} finally {
			if (fis != null)
				fis.close();
		}
		return state;
	}

	/**
	 * 删除文件
	 * 
	 * @param path
	 * @return
	 */
	public static boolean removeFile(String path) {
		return removeFile(new File(path));
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 * @return
	 */
	public static boolean removeFile(File file) {
		boolean flag = false;
		if (file != null && file.exists())
			flag = file.delete();
		return flag;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 * @param path
	 * @return
	 */
	public static boolean removeFile(String fileName, String path) {
		boolean flag = false;
		if (isFileExist(fileName, path)) {
			File file = new File(getDoPath(path) + fileName);
			flag = file.delete();
		}
		return flag;
	}

	/**
	 * 随机生成文件名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getRandomName(String fileName) {
		String randomName = UUID.randomUUID().toString();
		return getNewFileName(fileName, randomName, "txt");

	}

	/**
	 * 根据日期生成文件名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getNumberName(String fileName) {
		SimpleDateFormat format = new SimpleDateFormat("yyMMddhhmmss");
		int rand = new Random().nextInt(1000);
		String numberName = format.format(new Date()) + rand;
		return getNewFileName(fileName, numberName, getSuffix(fileName));
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param fileName
	 * @param path
	 * @return
	 */
	public static boolean isFileExist(String fileName, String path) {
		File file = new File(getDoPath(path) + fileName);
		return file.exists();
	}

	/**
	 * 处理系统文件路径
	 * 
	 * @param path
	 * @return
	 */
	public static String getDoPath(String path) {
		path = path.replace("\\", "/");
		String lastChar = path.substring(path.length() - 1);
		if (!"/".equals(lastChar))
			path += "/";
		return path;
	}

	/**
	 * 创建指定的path路径目录
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static boolean mkDir(String path) throws Exception {
		File file = null;
		try {
			file = new File(path);
			if (!file.exists())
				return file.mkdirs();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			file = null;
		}
		return false;
	}

	/**
	 * 传递一个文件名称和一个新名称，组合成一个新的带后缀文件名,当传递的文件名没有后缀，会添加默认的后缀
	 * 
	 * @param fileName
	 * @param newName
	 * @param nullSuffix
	 * @return
	 */
	public static String getNewFileName(String fileName, String newName, String nullSuffix) {
		String suffix = getSuffix(fileName);
		if (suffix != null)
			newName += suffix;
		else
			newName = newName.concat(".").concat(nullSuffix);
		return newName;
	}

	/**
	 * 传入一个文件名，得到这个文件名称的后缀
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getSuffix(String fileName) {
		int index = fileName.lastIndexOf(".");
		if (index != -1) {
			String suffix = fileName.substring(index);// 后缀
			return suffix;
		} else
			return null;
	}
}