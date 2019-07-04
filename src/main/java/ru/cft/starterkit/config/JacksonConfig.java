package ru.cft.starterkit.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ru.cft.starterkit.entity.SampleEntity;

@Configuration
public class JacksonConfig {

    @Autowired
    public JacksonConfig(ObjectMapper objectMapper) {
        objectMapper.addMixIn(SampleEntity.class, SampleEntityDto.class);
    }

    private class SampleEntityDto {
        @JsonIgnore
        private String sessionId;
    }
}
