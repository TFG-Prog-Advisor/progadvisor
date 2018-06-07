package org.costa.progadvisor.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

	private List<String> fileList;
	private String sourceFolderPath;
	//private static final String OUTPUT_ZIP_FILE = "../folder.zip";
	//private static final String sourceFolderPath = System.getProperty("user.dir");

	// Constructora
	public ZipUtils (String sourcePath) 
	{
		this.fileList = new ArrayList<String>();
		this.sourceFolderPath = sourcePath;
	}

	public byte [] zipIt (String zipFile)
	{
		byte[] buffer = new byte[1024];
		String source = new File(sourceFolderPath).getName();
		
		ZipOutputStream zos = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			zos = new ZipOutputStream(bos);

			System.out.println("Output to zip" + zipFile);
			FileInputStream in = null;

			for (String file: this.fileList) {
				ZipEntry ze = new ZipEntry(source + File.separator + file);
				zos.putNextEntry(ze);

				try {
					in = new FileInputStream(sourceFolderPath + File.separator + file);
					int len;
					while ((len = in .read(buffer)) > 0) {
						zos.write(buffer, 0, len);
					}
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					in.close();
				}
			}

			zos.closeEntry();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				zos.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		System.out.println(bos.toByteArray().length);
		return bos.toByteArray();
	}

	public void generateFileList(File node) {
		//add file only
		if (node.isFile()) {
			this.fileList.add(generateZipEntry(node.toString()));
		}

		if (node.isDirectory()) {
			String[] subNode = node.list();
			for (String fileName: subNode) {
				generateFileList(new File(node, fileName));
			}
		}
	}


	private String generateZipEntry(String file) {
		return file.substring(sourceFolderPath.length() + 1, file.length());
	}
}