package com.blogapp.Scribble_Hub.service.Impl;

import com.blogapp.Scribble_Hub.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //file name
        String name = file.getOriginalFilename();  //suppose abc.png
        //full path
        String filePath=path + File.separator + name;  // suppose images/abc.png
        //create folder if not created
        File f=new File(path);
        if (!f.exists()){
            f.mkdir();
        }
        //file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));  //to read the image and save it on the path
        return name;
    }
}
