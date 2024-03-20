package com.laptrinhweb.api;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadFile
 */
@WebServlet("/api/admin/UploadFile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 50, // 50MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Part filePart = request.getPart("fileName");
		String fileName = filePart.getSubmittedFileName();
		//PathServer
		String path = getServletContext().getRealPath("\\uploads\\");
		
		//Path clone về máy để lưu trữ khi đóng server
		String realPath = getServletContext().getRealPath("/");
		
		System.out.println(realPath);
		String pathFolderProject = realPath.substring(0, realPath.indexOf("\\.metadata\\"))+"\\MoviesDB_springMVC\\";
		System.out.println(pathFolderProject);
		System.out.println(pathFolderProject);
		File theDir = new File(pathFolderProject + "\\template\\uploads\\");
        if (!theDir.exists()) { //Tạo folder nếu chưa tồn tại ở máy
            theDir.mkdirs();
        }
        String pathUpload = pathFolderProject + "uploads\\";
        
        String pathUploadServer = getServletContext().getRealPath("") + "\\template\\uploads\\";
        theDir = new File(pathUploadServer);
        if (!theDir.exists()) { //Tạo folder nếu chưa tồn tại ở server
            theDir.mkdirs();
        }
		
		System.out.println("PathUploadServer: "+path);
		System.out.println("PathUpload:"+pathUpload);
				
        Part ClonefilePart = filePart;
		path = pathUploadServer+fileName;
		filePart.write(path);
		pathUpload = pathUpload+fileName;
		ClonefilePart.write(pathUpload);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

