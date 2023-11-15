package com.hrm.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppServiceImpl implements AppService {

    private final EntityObjectRepository repository;
    private final SongsClient songsClient;
    private final StorageS3Client s3Client;

    @Override
    public EntityObject get(Long id) {
        songsClient.ping();
        String content = LocalDateTime.now().toString();
        UUID key = UUID.nameUUIDFromBytes(content.getBytes());
        String location = "/bark/" + key;
        s3Client.upload(content, location);
        log.info("uploaded to S3: '{}'", location);
        String downloaded = s3Client.download(location);
        log.info("downloaded from S3: '{}'", downloaded);
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<EntityObject> getAll() {
        return repository.findAll();
    }

    @Override
    public EntityObject save(EntityObject entity) {
        entity.setId(null);
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
