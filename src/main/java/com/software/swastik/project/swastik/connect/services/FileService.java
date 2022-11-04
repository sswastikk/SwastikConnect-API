package com.software.swastik.project.swastik.connect.services;

import org.springframework.web.multipart.MultipartFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService
{
    //Upload Image
    String uploadImage(String path, MultipartFile file) throws IOException;

    //Get Resource
    InputStream getResource(String path, String fileName) throws FileNotFoundException;
}
