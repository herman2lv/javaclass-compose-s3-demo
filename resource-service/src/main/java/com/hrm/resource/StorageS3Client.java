package com.hrm.resource;

public interface StorageS3Client {

    void upload(String content, String key);

    String download(String key);

    void delete(String key);

}
