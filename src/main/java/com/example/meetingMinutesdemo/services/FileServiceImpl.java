package com.example.meetingMinutesdemo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String fileUpload(MultipartFile file) {
        return null;
    }

    @Override
    public void fileDownload() {

    }
}
