package ru.cft.starterkit.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.starterkit.entity.SampleEntity;
import ru.cft.starterkit.exception.ObjectNotFoundException;
import ru.cft.starterkit.repository.SampleEntityRepository;
import ru.cft.starterkit.service.SampleEntityService;

import java.util.UUID;

@Service
public class SampleEntityServiceImpl implements SampleEntityService {

    private final SampleEntityRepository sampleEntityRepository;

    @Autowired
    public SampleEntityServiceImpl(SampleEntityRepository sampleEntityRepository) {
        this.sampleEntityRepository = sampleEntityRepository;
    }

    @Override
    public SampleEntity add(String foo, Double bar) {
        SampleEntity sampleEntity = new SampleEntity();
        sampleEntity.setFoo(foo);
        sampleEntity.setBar(bar);
        sampleEntity.setBaz("Create timestamp: " + System.currentTimeMillis());
        sampleEntity.setSessionId(UUID.randomUUID().toString());
        return sampleEntityRepository.add(sampleEntity);
    }

    @Override
    public SampleEntity get(Long id) throws ObjectNotFoundException {
        return sampleEntityRepository.get(id);
    }

}
