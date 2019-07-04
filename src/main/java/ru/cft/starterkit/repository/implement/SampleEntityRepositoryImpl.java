package ru.cft.starterkit.repository.implement;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.cft.starterkit.entity.SampleEntity;
import ru.cft.starterkit.exception.ObjectNotFoundException;
import ru.cft.starterkit.repository.SampleEntityRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SampleEntityRepositoryImpl implements SampleEntityRepository {

    private static final File STORAGE_FILE = new File("C:\\temp\\samples.json");

    private static final Logger log = LoggerFactory.getLogger(SampleEntityRepositoryImpl.class);

    private final AtomicLong idCounter = new AtomicLong();

    private final Map<Long, SampleEntity> storage = new ConcurrentHashMap<>();

    private final ObjectMapper objectMapper;

    @Autowired
    public SampleEntityRepositoryImpl(ObjectMapper objectMapper) {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public SampleEntity add(SampleEntity sampleEntity) {
        sampleEntity.setId(idCounter.incrementAndGet());
        storage.put(sampleEntity.getId(), sampleEntity);

        log.info("Added new sample entity to storage: {}", sampleEntity);
        return sampleEntity;
    }

    @Override
    public SampleEntity get(Long id) throws ObjectNotFoundException {
        SampleEntity sampleEntity = storage.get(id);

        if (sampleEntity == null) {
            log.error("Failed to get sample entity with id '{}' from storage", id);
            throw new ObjectNotFoundException(String.format("Sample entity with id %s not found", id));
        }

        log.info("Returned sample entity with id '{}' from storage: {}", id, sampleEntity);
        return sampleEntity;
    }

    @PostConstruct
    private void initStorage() {
        try {
            SampleEntity[] entriesFromFile = objectMapper.readValue(STORAGE_FILE, SampleEntity[].class);
            for (SampleEntity sampleEntity : entriesFromFile) {
                storage.put(sampleEntity.getId(), sampleEntity);
                if (idCounter.get() < sampleEntity.getId()) {
                    idCounter.set(sampleEntity.getId());
                }
            }
            log.info("Loaded {} entities to storage. Id counter set to {}.", storage.size(), idCounter.get());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    private void shutdown() {
        log.info("Start shutdown!");
        try {
            objectMapper.writeValue(STORAGE_FILE, storage.values());
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Shutdown is ready!");
    }

}
