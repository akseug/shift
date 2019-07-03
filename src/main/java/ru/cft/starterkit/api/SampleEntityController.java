package ru.cft.starterkit.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.cft.starterkit.entity.SampleEntity;
import ru.cft.starterkit.exception.ObjectNotFoundException;
import ru.cft.starterkit.service.SampleEntityService;

@RestController(Paths.SAMPLES)
public class SampleEntityController {

    private final SampleEntityService sampleEntityService;

    @Autowired
    public SampleEntityController(SampleEntityService sampleEntityService) {
        this.sampleEntityService = sampleEntityService;
    }

    @PostMapping(
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json"
    )
    public SampleEntity add(
            @RequestParam(name = "foo") String foo,
            @RequestParam(name = "bar", defaultValue = "1.1") Double bar
    ) {
        return sampleEntityService.add(foo, bar);
    }

    @GetMapping(path = Paths._ID, produces = "application/json")
    public SampleEntity get(@PathVariable(name = "id") Long id) {
        try {
            return sampleEntityService.get(id);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}
