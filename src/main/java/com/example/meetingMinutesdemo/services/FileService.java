package com.example.meetingMinutesdemo.services;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

   abstract public String fileUpload(MultipartFile file);
    abstract public void  fileDownload();
}
