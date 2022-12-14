package cn.zxlee.znotifyapi.utils.oss.impl;

import cn.zxlee.znotifyapi.consts.ConstantProperties;
import cn.zxlee.znotifyapi.exception.CommonException;
import cn.zxlee.znotifyapi.utils.SnowFlake;
import cn.zxlee.znotifyapi.utils.oss.IOssService;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: z-notify-api
 * @description: 阿里云oss服务实现类
 * @author: zxlee
 * @create: 2022-12-13 14:16
 **/

@Component
public class OssServiceImpl implements IOssService {
    @Override
    public List<String> uploadFiles(MultipartFile[] files) {
        if (null == files || 0 == files.length) {
            throw new CommonException("未选择需要上传的文件");
        }

        String endPoint = ConstantProperties.END_POIND;
        String accessKeyId = ConstantProperties.ACCESS_KEY_ID;
        String accessKeySecret = ConstantProperties.ACCESS_KEY_SECRET;
        String bucketName = ConstantProperties.BUCKET_NAME;

        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        List<String> list = new ArrayList<>();

        try {
            for (MultipartFile file : files) {
                String fileName = new DateTime().toString("yyyy/MM/dd")
                        + "/"
                        + SnowFlake.nextId()
                        + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                ossClient.putObject(ConstantProperties.BUCKET_NAME, fileName, file.getInputStream());
                String url = "http://" + bucketName + "." + endPoint + "/" + fileName;
                list.add(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return list;
    }
}
