package cn.zxlee.znotifyapi.utils.oss;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IOssService {
    List<String> uploadFiles(MultipartFile[] files);
}
