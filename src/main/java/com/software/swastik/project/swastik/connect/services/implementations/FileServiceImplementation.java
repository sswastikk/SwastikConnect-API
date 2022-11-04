package com.software.swastik.project.swastik.connect.services.implementations;

import com.software.swastik.project.swastik.connect.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImplementation implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String name        = file.getOriginalFilename();                                                                      //File Name : eg : abc.png
        String randomId = UUID.randomUUID().toString();                                                            //Random Name Generate File
        String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));
        String filePath    = path + File.separator + fileName1;                                                          //Full Path

        //Create Folder If Not Created
        File f = new File(path);

        if(!f.exists()){
            f.mkdir();
        }

        //File Copy
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName1;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }
}
