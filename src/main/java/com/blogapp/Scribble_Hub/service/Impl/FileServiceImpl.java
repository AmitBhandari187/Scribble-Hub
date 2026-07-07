package com.blogapp.Scribble_Hub.service.Impl;

import com.blogapp.Scribble_Hub.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //file name
        String name = file.getOriginalFilename();  //suppose abc.png

        String randomId= UUID.randomUUID().toString();
        String fileName=randomId +"_"+ name;

        //full path
        String filePath=path + File.separator + fileName;  // suppose images/abc.png

        // Example:
        // 7b8b0f3b-7b7e-4f8c-9d8e-2d8d8c3f4a11_abc.png

        //create folder if not created
        File f=new File(path);
        if (!f.exists()){
            f.mkdir();
        }
        //file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));  //to read the image and save it on the path
        return name;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
            String fullPath=path + File.separator+fileName;
            InputStream is=new FileInputStream(fullPath);
        return is;
    }

}
