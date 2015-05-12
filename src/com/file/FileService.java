package com.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class FileService {

	private String repoistory = "/fileRepository";
	private static Log log = LogFactory.getLog(FileService.class);

	public String uploadFile(String filename, DataHandler dataHandler) {
		try {
			log.info("FileService uploadFile start ..... ");
			// ´´½¨²Ö¿âÄ¿Â¼
			File fileRepo = new File(repoistory);
			if (!fileRepo.exists()) {
				fileRepo.mkdir();
				log.info("fileRepo : " + fileRepo);
			}
			filename = (filename == null) ? "temp.txt" : filename;
			log.info("filename :" + filename);

			File upFile = new File(repoistory, filename);
			FileOutputStream fileOutputStream = new FileOutputStream(upFile, true);
			InputStream inputStream = dataHandler.getInputStream();
			byte[] read = new byte[1024];
			int n = 0;
			while ((n = inputStream.read(read)) != -1) {
				fileOutputStream.write(read, 0, n);
			}
			fileOutputStream.flush();
			fileOutputStream.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return " File: " + filename + "  send ok";
	}

	public DataHandler[] downloadFile(String filename) {
		log.info("FileService download start.....");
		DataHandler[] dataHandlers = null;
		try {
			File repoFile = new File(repoistory);
			if (!repoFile.exists()) {
				repoFile.mkdir();
				log.info("create a empty file repository !");
			}

			File dwFile = new File(repoistory, filename);
			if (!dwFile.exists()) {
				log.info("download all of files in " + repoistory);
				File[] files = repoFile.listFiles();
				dataHandlers = new DataHandler[files.length];
				for (int i = 0; i < files.length; i++) {
					dataHandlers[i] = new DataHandler(new FileDataSource(files[i]));
				}
			} else {
				log.info("download file " + filename);
				dataHandlers = new DataHandler[1];
				dataHandlers[0] = new DataHandler(new FileDataSource(dwFile));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataHandlers;
	}

	@Test
	public void testUp() {
		File file = new File("G:/a.txt");
		FileDataSource fileDataSource = new FileDataSource(file);
		DataHandler dataHandler = new DataHandler(fileDataSource);
		uploadFile("a.txt", dataHandler);
	}

	@Test
	public void testDown() {
		try {
			String dwDir = "G:/downRepository";
			//File dwRepo = new File("G:/downRepository");
			DataHandler[] downloadFile = downloadFile("a.txt");
			log.info(downloadFile.length);
//			for (int i = 0; i < downloadFile.length; i++) {
//				DataHandler dataHander = downloadFile[i];
//				InputStream inputStream = dataHander.getInputStream();
//				FileOutputStream fileOutputStream = new FileOutputStream(new File(dwDir,dataHander.getDataSource().getName()));
//				byte[] readBuffer = new byte[1024];
//				for(int n=inputStream.read(readBuffer) ; n > 0 ;) {
//					fileOutputStream.write(readBuffer, 0, n);
//				}
//				fileOutputStream.flush();
//				fileOutputStream.close();
//				inputStream.close();
//			}
			DataHandler dataHandler = downloadFile[0];
			InputStream inputStream = dataHandler.getDataSource().getInputStream();
			FileOutputStream fileOutputStream = new FileOutputStream(new File(dwDir,"a.txt"));
			byte[] readBuffer = new byte[1024];
			int n = 0;
			while((n=inputStream.read(readBuffer)) != -1) {
				fileOutputStream.write(readBuffer, 0, n);
			}
			fileOutputStream.flush();
			fileOutputStream.close();
			inputStream.close();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
