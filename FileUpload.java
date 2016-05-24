package com.hashMapData;
import java.io.File;
import java.io.FileInputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.*;

public class FileUpload {
	
	/*String SFTPHOST = "172.29.2.201";
    int SFTPPORT = 22;
    String SFTPUSER = "situsr2";
    String SFTPPASS = "Tcs@123";
    String SFTPWORKINGDIR = "/home/situsr2/ftp";*/

    Session session = null;
    Channel channel = null;
    ChannelSftp channelSftp = null;

	public void upload(String SFTPUSER, String SFTPPASS, String SFTPHOST, int SFTPPORT,  String SFTPWORKINGDIR) {
		  try {
		    JSch jsch = new JSch();
		session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
		session.setPassword(SFTPPASS);
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		channel = session.openChannel("sftp");
		channel.connect();
		channelSftp = (ChannelSftp) channel;
		channelSftp.cd(SFTPWORKINGDIR);

		File f1 = new File("/code/NRMS/FTP/" + "StringFile.txt");
		channelSftp.put(new FileInputStream(f1), f1.getName(),ChannelSftp.OVERWRITE);
		System.out.println("uploaded");
		/*File f2 = new File("ext_files/" + FILETOTRANSFER2);
		channelSftp.put(new FileInputStream(f2), f2.getName());*/

		channelSftp.exit();
		session.disconnect();
		f1.delete();
		} catch (Exception ex) {
		  ex.printStackTrace();
		  }
		}
	
	public static void main(String[] args) {
		FileUpload upload = new FileUpload();
		upload.upload("situsr2", "Tcs@123", "172.29.2.201", 22, "/home/situsr2/ftp");		
	}
}
