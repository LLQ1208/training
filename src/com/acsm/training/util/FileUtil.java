package com.acsm.training.util;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.channels.FileChannel;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;


public class FileUtil {

	private String message;

	public FileUtil() {
	}

	/*
	 *TODO 这里只支持了IP，没考虑到域名、机器名的情况 
	 * 截取URL中的IP
	 */
	public static String getMatcher(String sourceStr){
		String regex = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})";
		return StringUtils.getMatcher(regex, sourceStr);
	}
	
	/**
	 * 遍历文件夹中文件
	 *
	 * @param filepath
	 * @return 返回file［］数组
	 */
	public File[] getFileList(String filepath) {
		File d = null;
		File list[] = null;
		// 建立当前目录中文件的File对象
		try {
			d = new File(filepath);
			if (d.exists()) {
				list = d.listFiles();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			message = "遍历文件夹出错";
		}
		// 取得代表目录中所有文件的File对象数组

		return list;
	}

	/**
	 * 读取文本文件内容
	 *
	 * @param filePathAndName
	 *            带有完整绝对路径的文件名
	 * @param encoding
	 *            文本文件打开的编码方式
	 * @return 返回文本文件的内容
	 */
	@SuppressWarnings("resource")
	public String readTxt(String filePathAndName, String encoding)
			throws IOException {
		encoding = encoding.trim();
		StringBuffer str = new StringBuffer("");
		String st = "";
		try {
			FileInputStream fs = new FileInputStream(filePathAndName);
			InputStreamReader isr;
			if (encoding.equals("")) {
				isr = new InputStreamReader(fs);
			} else {
				isr = new InputStreamReader(fs, encoding);
			}
			BufferedReader br = new BufferedReader(isr);
			try {
				String data = "";
				while ((data = br.readLine()) != null) {
					str.append(data);
				}
			} catch (Exception e) {
				str.append(e.toString());
			}
			st = str.toString();
			if (st != null && st.length() > 1)
				st = st.substring(0, st.length() - 1);
		} catch (IOException es) {
			st = "";
		}
		return st;
	}

	/**
	 * 新建目录
	 *
	 * @param folderPath
	 *            目录
	 * @return 返回目录创建后的路径
	 */
	public String createFolder(String folderPath) {
		String txt = folderPath;
		try {
			File myFilePath = new File(txt);
			txt = folderPath;
			if (!myFilePath.exists()) {
				myFilePath.mkdirs();
			}
		} catch (Exception e) {
			message = "创建目录操作出错";
		}
		return txt;
	}


	/**
	 * 创建远程文件夹
	 * @param path  例如“smb://172.19.***.***shareTest/test”，别忘了shareTest目录要开共享
	 * @param userName 目标机器（172.19.***.***）的账户名
	 * @param password  目标机器（172.19.***.***）的账户名
	 * @return
	 */
	public String createRemoteFolder(String path,String userName,String password){
		try {
			String domainIP = getMatcher(path);
			NtlmPasswordAuthentication auth = getRemoteAuth(domainIP,userName,password);
			SmbFile dir = new SmbFile(path,auth);
			if(!dir.exists())
				dir.mkdirs();
			return dir.getPath();
		} catch (Exception e) {
			message = "创建远程文件夹出错";
			System.out.println(message);
			System.out.println(e);
		}
		return path;
	}

	/**
	 * 按照输入流、输出流新建文件
	 * @param inStream
	 * @param outStream
	 * @throws java.io.IOException
	 */
	public void createFileByStream(InputStream inStream, OutputStream outStream)
			throws IOException {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		bis = new BufferedInputStream(inStream);
		bos = new BufferedOutputStream(outStream);
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bos.flush();
		bos.close();
		bis.close();
	}

	/**
	 * 按照输入流、输出流新建文件
	 * @param inStream
	 * @param outStream
	 * @throws java.io.IOException
	 */
	public void createFileByStreamWithPart(InputStream inStream, OutputStream outStream,long start,long end)
			throws IOException {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		bis = new BufferedInputStream(inStream);
		bos = new BufferedOutputStream(outStream);
		//三种情况，如果start和end均为-1，则全部下载
		//如果start大于0，end为-1，则跳过start的长度，然后全部下载
		//如果均大于0，则截取中间的部分，即跳过start的长度，读到end的长度为止
		int BUFFER_LEN = 2048;
		byte[] buff = new byte[BUFFER_LEN];

		//跳过一部分字节，由于skip方法不一定保证准确的跳过，这里采用循环的方式
		if(start >= 0){
			int at = (int)start-1;
			while(at > 0) {
				long amt = bis.skip(at);
				if (amt == -1) {
					throw new RuntimeException("file: unexpected EOF");
				}
				at -= amt;
			}
		}

		if(end == -1){
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		}

		if(end > 0 && end > start ){
			long contentLength = end - start +1;
			int n = 0;
			long readLength = 0;//记录已读字节数
			while (readLength <= contentLength - 1024) {//大部分字节在这里读取
				n = bis.read(buff, 0, buff.length);
				readLength += BUFFER_LEN;
				bos.write(buff, 0, n);
			}
			if (readLength <= contentLength) {//余下的不足 1024 个字节在这里读取
				n = bis.read(buff, 0, (int)(contentLength - readLength));
				bos.write(buff, 0, n);
			}
		}
		bos.flush();
		bos.close();
		bis.close();
	}

	/**
	 * 按照路径、存储名和输入流新建文件
	 * @param storePath
	 * @param storeName
	 * @param fileContent
	 */
	public void createFileByInStream(String storePath, String storeName,InputStream fileContent) {
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream(storePath+storeName);
			byte[] rs = new byte[1024];
			while(true){
				int ret = fileContent.read(rs, 0, 1024);
				if(ret <=0)
					break;
				else{
					fs.write(rs, 0, ret);
				}
			}
			//			fs.flush();
			fs.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新建文本文件
	 * @param filePathAndName   文本文件完整绝对路径及文件名
	 * @param fileContent  文本文件内容
	 * @return
	 */
	public void createFile(String filePathAndName, String fileContent) {

		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			FileWriter resultFile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
			myFile.close();
			resultFile.close();
		} catch (Exception e) {
			message = "创建文件操作出错";
		}
	}

	/**
	 * 有编码方式的文本文件创建
	 * @param filePathAndName   文本文件完整绝对路径及文件名
	 * @param fileContent  文本文件内容
	 * @param encoding  编码方式 例如 GBK 或者 UTF-8
	 * @return
	 */
	public void createFile(String filePathAndName, String fileContent,String encoding) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			PrintWriter myFile = new PrintWriter(myFilePath, encoding);
			String strContent = fileContent;
			myFile.println(strContent);
			myFile.close();
		} catch (Exception e) {
			message = "创建文件操作出错";
		}
	}

	/**
	 * 创建远程文件（远程写文件）
	 * @param path
	 * @param userName
	 * @param password
	 * @param stream
	 * @return
	 * @throws java.io.IOException
	 */
	@SuppressWarnings("static-access")
	public void createRemoteFile(String storeName,String username,String password,InputStream stream) throws IOException{
//		String domainIP = storeName.substring(6,18);//storeName是什么?
		String domainIP = this.getMatcher(storeName);
		SmbFileOutputStream sfos = null;
		try {
			//			InetAddress addr = InetAddress.getByName(domainIP);
			NtlmPasswordAuthentication auth = getRemoteAuth(domainIP,username,password);
			SmbFile sFile = new SmbFile(storeName,auth);
			if(sFile.exists()){
				sFile.createNewFile();
			}
			sfos = new SmbFileOutputStream(sFile);
			byte[] rs = new byte[1024];
			while(true){
				int ret = stream.read(rs, 0, 1024);
				if(ret <=0)
					break;
				else{
					sfos.write(rs, 0, ret);
				}
			}
			sfos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SmbFile getRemoteFile(String storeName,String username,String password) throws IOException{
		
		String domainIP = this.getMatcher(storeName);
		NtlmPasswordAuthentication auth = getRemoteAuth(domainIP,username,password);
		SmbFile sFile = new SmbFile(storeName,auth);
		return sFile;
	} 
	
	
	/**
	 * 按照文件绝对路径名删除文件
	 * @param filePathAndName 文本文件完整绝对路径及文件名
	 * @return Boolean 成功删除返回true遭遇异常返回false
	 */
	public boolean delFile(String filePathAndName) {
		boolean bea = false;
		try {
			String filePath = filePathAndName;
			File myDelFile = new File(filePath);
			if (myDelFile.exists()) {
				myDelFile.delete();
				bea = true;
			} else {
				bea = false;
				message = (filePathAndName + "删除文件操作出错");
			}
		} catch (Exception e) {
			message = e.toString();
		}
		return bea;
	}

	/**
	 * 删除远程文件
	 * @param path
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean delRemoteFile(String path,String username,String password){
		String domainIP = getMatcher(path);
		InetAddress addr;
		try {
			NtlmPasswordAuthentication auth = getRemoteAuth(domainIP,username,password);
			SmbFile sFile = new SmbFile(path,auth);
			sFile.delete();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 删除文件夹
	 * @param folderPath 文件夹完整绝对路径
	 * @return
	 * @return
	 */
	public boolean delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			new File(folderPath).delete(); // 删除空文件夹
			return true;
		} catch (Exception e) {
			message = ("删除文件夹操作出错");
			return false;
		}
	}

	/**
	 * 删除远程文件夹
	 * @param folderPath 文件夹完整绝对路径
	 * @return
	 * @return
	 */
	public boolean delFolder(String folderPath,String username,String password) {
		try {
			delAllFile(folderPath, username, password); // 删除完里面所有内容
			String domainIP = folderPath.substring(6,18);
//			String domainIP = getMatcher(folderPath);
			NtlmPasswordAuthentication auth = getRemoteAuth(domainIP, username, password);
			SmbFile file = new SmbFile(folderPath,auth);
			file.delete(); // 删除空文件夹
			return true;
		} catch (Exception e) {
			message = ("删除文件夹操作出错");
			return false;
		}
	}

	/**
	 * 删除指定文件夹下所有文件
	 * @param path  文件夹完整绝对路径
	 * @return
	 * @return
	 */
	public boolean delAllFile(String path) {
		boolean bea = false;
		File file = new File(path);
		//		SmbFile file = new SmbFile(path);
		if (!file.exists()) {
			return bea;
		}
		if (!file.isDirectory()) {
			return bea;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				bea = true;
			}
		}
		return bea;
	}

	/**
	 * 删除指定 远程 文件夹下所有文件
	 * @param path  文件夹完整绝对路径
	 * @return
	 * @return
	 */
	public boolean delAllFile(String path,String username,String password) {
		boolean bea = false;
		String domainIP = getMatcher(path);
		try {
			NtlmPasswordAuthentication auth = getRemoteAuth(domainIP, username, password);
			SmbFile file = new SmbFile(path,auth);
			if (!file.exists()) {
				return bea;
			}
			if (!file.isDirectory()) {
				return bea;
			}
			String[] tempList = file.list();
			File temp = null;
			for (int i = 0; i < tempList.length; i++) {
				if (path.endsWith(File.separator)) {
					temp = new File(path + tempList[i]);
				} else {
					temp = new File(path + File.separator + tempList[i]);
				}
				if (temp.isFile()) {
					temp.delete();
				}
				if (temp.isDirectory()) {
					delAllFile(path + "/" + tempList[i],username, password);// 先删除文件夹里面的文件
					delFolder(path + "/" + tempList[i], username, password);// 再删除空文件夹
					bea = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bea;
	}

	/**
	 * 复制单个文件
	 *
	 * @param oldPathFile
	 *            准备复制的文件源
	 * @param newPathFile
	 *            拷贝到新绝对路径带文件名
	 * @return
	 */
	public void copyFile(String oldPathFile, String newPathFile) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPathFile);
			if (oldfile.exists()) { // 文件存在
				InputStream inStream = new FileInputStream(oldPathFile); // 读入源文件
				FileOutputStream fs = new FileOutputStream(newPathFile);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节 文件大小
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			message = ("复制单个文件操作出错");
		}
	}
	
   public void fileChannelCopy(String source , String ti) {
	   File s = new File(source);
	   File t = new File(ti);
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	/**
	 * 复制整个文件夹的内容
	 *
	 * @param oldPath
	 *            准备拷贝的目录
	 * @param newPath
	 *            指定绝对路径的新目录
	 * @return
	 */
	public void copyFolder(String oldPath, String newPath) {
		try {
			new File(newPath).mkdirs(); // 如果文件夹不存在 则建立新文件
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}
				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			message = "复制整个文件夹内容操作出错";
		}
	}

	/**
	 * 移动文件
	 *
	 * @param oldPath
	 * @param newPath
	 * @return
	 */
	public void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		delFile(oldPath);
	}

	/**
	 * 移动目录
	 *
	 * @param oldPath
	 * @param newPath
	 * @return
	 */
	public void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);
	}

	/**
	 * 建立一个可以追加的bufferedwriter
	 *
	 * @param fileDir
	 * @param fileName
	 * @return
	 */
	public BufferedWriter getWriter(String fileDir, String fileName) {
		try {
			File f1 = new File(fileDir);
			if (!f1.exists()) {
				f1.mkdirs();
			}
			f1 = new File(fileDir, fileName);
			if (!f1.exists()) {
				f1.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(f1.getPath(),
					true));
			return bw;
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}

	/**
	 * 得到一个bufferedreader
	 *
	 * @param fileDir
	 * @param fileName
	 * @param encoding
	 * @return
	 */
	public BufferedReader getReader(String fileDir, String fileName,
			String encoding) {
		try {
			File file = new File(fileDir, fileName);
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), encoding);
			BufferedReader br = new BufferedReader(read);
			return br;

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public String getMessage() {
		return this.message;
	}


	/**
	 * 远程登录，获取NtlmPasswordAuthentication实例
	 * @param domainIP
	 * @param username
	 * @param password
	 * @return
	 */
	public NtlmPasswordAuthentication getRemoteAuth(String domainIP,String username,String password){
		InetAddress addr;
		try {
			addr = InetAddress.getByName(domainIP);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		NtlmPasswordAuthentication auth =  new NtlmPasswordAuthentication(domainIP,username,password);
		//		UniAddress myDomain = new UniAddress(addr);
		//		SmbSession.logon(myDomain, auth);
		return auth;
	}
	
	//copy from https://github.com/google/guava/blob/master/guava/src/com/google/common/io/ByteStreams.java
	/**
	 * Wraps a {@link java.io.InputStream}, limiting the number of bytes which can be
	 * read.
	 *
	 * @param in the input stream to be wrapped
	 * @param limit the maximum number of bytes to be read
	 * @return a length-limited {@link java.io.InputStream}
	 * @since 14.0 (since 1.0 as com.google.common.io.LimitInputStream)
	 */
	public static InputStream limit(InputStream in, long limit) {
		return new LimitedInputStream(in, limit);
	}

	private static final class LimitedInputStream extends FilterInputStream {

		private long left;
		private long mark = -1;

		LimitedInputStream(InputStream in, long limit) {
			super(in);
			if (in == null) {
				throw new NullPointerException();
			}
			
			if (limit < 0) {
				throw new IllegalArgumentException("limit must be non-negative");
			}
			
			left = limit;
		}

		@Override public int available() throws IOException {
			return (int) Math.min(in.available(), left);
		}

		// it's okay to mark even if mark isn't supported, as reset won't work
		@Override public synchronized void mark(int readLimit) {
			in.mark(readLimit);
			mark = left;
		}

		@Override public int read() throws IOException {
			if (left == 0) {
				return -1;
			}

			int result = in.read();
			if (result != -1) {
				--left;
			}
			return result;
		}

		@Override public int read(byte[] b, int off, int len) throws IOException {
			if (left == 0) {
				return -1;
			}

			len = (int) Math.min(len, left);
			int result = in.read(b, off, len);
			if (result != -1) {
				left -= result;
			}
			return result;
		}

		@Override public synchronized void reset() throws IOException {
			if (!in.markSupported()) {
				throw new IOException("Mark not supported");
			}
			if (mark == -1) {
				throw new IOException("Mark not set");
			}

			in.reset();
			left = mark;
		}

		@Override public long skip(long n) throws IOException {
			n = Math.min(n, left);
			long skipped = in.skip(n);
			left -= skipped;
			return skipped;
		}
	}
	
	/**
	 * Discards {@code n} bytes of data from the input stream. This method
	 * will block until the full amount has been skipped. Does not close the
	 * stream.
	 *
	 * @param in the input stream to read from
	 * @param n the number of bytes to skip
	 * @throws java.io.EOFException if this stream reaches the end before skipping all
	 *     the bytes
	 * @throws java.io.IOException if an I/O error occurs, or the stream does not
	 *     support skipping
	 */
	public static void skipFully(InputStream in, long n) throws IOException {
		long toSkip = n;
		while (n > 0) {
			long amt = in.skip(n);
			if (amt == 0) {
				// Force a blocking read to avoid infinite loop
				if (in.read() == -1) {
					long skipped = toSkip - n;
					throw new EOFException("reached end of stream after skipping " + skipped + " bytes; " + toSkip + " bytes expected");
				}
				n--;
			} else {
				n -= amt;
			}
		}
	}
	//end copy
	
	

	
	
	
/********************************************************************************************/	
/*********************************从书刊云平台复制来的****************************************/		
/********************************************************************************************/		
	  public static void copyFile(File sourcefile,File targetFile) throws IOException{
	        
	        //新建文件输入流并对它进行缓冲
	        FileInputStream input=new FileInputStream(sourcefile);
//	        BufferedInputStream inbuff=new BufferedInputStream(input);
	        
	        //新建文件输出流并对它进行缓冲
	        FileOutputStream out=new FileOutputStream(targetFile);
//	        BufferedOutputStream outbuff=new BufferedOutputStream(out);
	        
	        //缓冲数组
	        byte[] b=new byte[1024*5];
	        int len=0;
//	        while((len=inbuff.read(b))!=-1){
//	            outbuff.write(b, 0, len);
//	        }
	        while((len=input.read(b))!=-1){
	        	out.write(b, 0, len);
	      }
	        //刷新此缓冲的输出流
//	        outbuff.flush();
	        
	        //关闭流
//	        inbuff.close();
//	        outbuff.close();
	        out.close();
	        input.close();
	        
	        
	    }
	    
	    public static void copyDirectiory(Integer bookFsId, String sourceDir,String targetDir) throws IOException{
	        
	        //新建目标目录
	        
	        (new File(targetDir)).mkdirs();
	        
	        //获取源文件夹当下的文件或目录
	        File[] file=(new File(sourceDir)).listFiles();
	        
	        for (int i = 0; i < file.length; i++) {
	            if(file[i].isFile()){
	                //源文件
	                File sourceFile=file[i];
	                    //目标文件file[i].getName()
	                String oldName=file[i].getName();
	                File targetFile=new File(new File(targetDir).getAbsolutePath()+File.separator+bookFsId+oldName.substring(oldName.lastIndexOf("_")));
	                copyFile(sourceFile, targetFile);
	            }
	            
	            
//	            if(file[i].isDirectory()){
//	                //准备复制的源文件夹
//	                String dir1=sourceDir+file[i].getName();
//	                //准备复制的目标文件夹
//	                String dir2=targetDir+"/"+file[i].getName();
//	                
//	                copyDirectiory(bookFsId, dir1, dir2);
//	            }
	        }
	        
	    }
	    
	    /**
	     * 复制文件及文件夹并进行文件重命名
	     * @param bookFsId 文件id
	     * @param url1 源文件夹
	     * @param url2 目标文件夹
	     * @return
	     * @throws java.io.IOException
	     */
	    public static int copyFolderAndFile(Integer bookFsId, String url1,String url2) throws IOException{
	    	   //创建目标文件夹
	        (new File(url2)).mkdirs();
	        //获取源文件夹当前下的文件或目录
	         File[] file=(new File(url1)).listFiles();
	         for (int i = 0; i < file.length; i++) {
//	          if(file[i].isFile()){
//	              //复制文件
//	              copyFile(file[i],new File(url2+bookFsId+"_"+i));
//	          }
//	          if(file[i].isDirectory()){
	              //复制目录
	              String sorceDir=url1+File.separator+file[i].getName();
	              String targetDir=url2+File.separator+file[i].getName();
	              copyDirectiory(bookFsId,sorceDir, targetDir);
//	          }
	      }
			return 0;
		}
	    
	    public static Object copyObj(Object object) throws Exception {
			Class<?> classType = object.getClass();
			Object objectCopy = classType.getConstructor(new Class[] {})
					.newInstance(new Object[] {});
			Field fields[] = classType.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String fieldName = field.getName();
				if(!"serialVersionUID".equals(fieldName)){
					String firstLetter = fieldName.substring(0, 1).toUpperCase();
					String getMethodName = "get" + firstLetter + fieldName.substring(1);
					String setMethodName = "set" + firstLetter + fieldName.substring(1);
					Method getMethod = classType.getMethod(getMethodName,
							new Class[] {});
					Method setMethod = classType.getMethod(setMethodName,
							new Class[] { field.getType() });
					Object value = getMethod.invoke(object, new Object[] {});
					if(value!=null){
						setMethod.invoke(objectCopy, new Object[] { value });
						
					}
				}
			}
			return objectCopy;
	 }
	
	
	
/********************************************************************************************/	
/********************************************************************************************/		
 /********************************************************************************************/		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		String path1 = "smb://172.19.70.176/RP EFilmFile Work Root/1050/3_491.pdf";
		String path2 = "smb://172.19.70.176/RP EFilmFile Work Root/1050/3_491-_-FHYF.key";
		NtlmPasswordAuthentication auth = new FileUtil().getRemoteAuth("172.19.70.176","Administrator","Founder123");
		SmbFile sFile = new SmbFile(path2,auth);
		InputStream inStream = new SmbFileInputStream(sFile);
		System.out.println("hello");
		
		
		//ByteArrayInputStream in = new ByteArrayInputStream(new byte[] { '1', '2', '3', '4', '5', '6', '7', '8', '9' });
//		InputStream in = new URL("http://localhost:8082/test.txt").openStream();
//		System.out.println(in.getClass());
//		//FileInputStream in = new FileInputStream("/dev/shm/tempshare/test.txt");
//		FileOutputStream out = new FileOutputStream("/dev/shm/t.txt");
//		new FileUtil().createFileByStreamWithPart(in, out, 11, -1);
//		in.close();
//		out.close();
//		System.out.println("end");
		
		{
				
//			String de = "http://172.019.123.123/test.txt";
//			String regex = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})";
//			String domain = StringUtil.getMatcher(regex, de);
//			System.out.println(domain);	
		}  
		
		{
			/*
			 * 测试创建远程文件夹
			 */
//			FileUtil util = new FileUtil();
//			//String path = "smb://172.19.70.161/shareTest/test";
//			String path = "smb://172.19.70.161/RP Remote Preview Root/3769/preview";
//			
//			String  userName = "Administrator";
//			String password = "Founder123";
//			util.createRemoteFolder(path, userName, password);
		}
		
		{
			/*
			 * 测试创建远程文件夹
			 */
//			FileUtil util = new FileUtil();
//			//String path = "smb://172.19.70.161/shareTest/test";
//			//String path = "smb://172.19.70.161/RP Remote Preview Root/3769/preview";
//			String path = "smb://172.19.70.176/Smart Books Remote Store Root/temp/12";
//			
//			String  userName = "Administrator";
//			String password = "Founder123";
//			util.createRemoteFolder(path, userName, password);
//			System.out.println("wanle-----");
		}
	}
	
}
