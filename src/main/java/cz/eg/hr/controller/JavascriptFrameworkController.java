package cz.eg.hr.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.eg.hr.data.JavascriptFramework;
import cz.eg.hr.repository.JavascriptFrameworkRepository;
import cz.eg.hr.service.JavascriptFrameworkServiceImpl;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
public class JavascriptFrameworkController {

    public final JavascriptFrameworkServiceImpl service;
    public final JavascriptFrameworkRepository repository;

    public JavascriptFrameworkController(JavascriptFrameworkServiceImpl service, JavascriptFrameworkRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("/frameworks")
    public ResponseEntity<Iterable<JavascriptFramework>> getAllFrameworks() {
        return ResponseEntity.ok(service.findAll());
    }

    @Transactional
    @PostMapping ("/frameworks")
    public ResponseEntity<Object> createFramework(@RequestBody String javascriptFramework) {
        JavascriptFramework parsedFramework;
        try {
            parsedFramework = getRequestData(javascriptFramework);
        } catch (JsonProcessingException error) {
            log.error("JavascriptFrameworkController:createFramework: Error occurred while processing request." + error.getMessage());
            return ResponseEntity.badRequest().body("Error occurred while processing request");
        }
        return ResponseEntity.ok(service.save(parsedFramework).toString());
    }

    @Transactional
    @PatchMapping("/frameworks/{id}")
    public ResponseEntity<Object> updateFrameworkById(@PathVariable Long id, @RequestBody String javascriptFramework) {
        JavascriptFramework parsedFramework;
        try {
            parsedFramework = getRequestData(javascriptFramework);
        } catch (JsonProcessingException error) {
            log.error("JavascriptFrameworkController:updateFrameworkById: Error occurred while processing request." + error.getMessage());
            return ResponseEntity.badRequest().body("Error occurred while processing request");
        }
        Optional<JavascriptFramework> framework = service.findById(id);
        if (framework.isPresent()) {
            parsedFramework.setId(framework.get().getId());
            return ResponseEntity.ok(service.save(parsedFramework));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @DeleteMapping("/frameworks/{id}")
    public ResponseEntity<Long> deleteFrameworkById(@PathVariable Long id) {
        if (service.existById(id)) {
            service.deleteById(id);
            return ResponseEntity.ok(id);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/frameworks/search-by-name")
    public ResponseEntity<Object> getFrameworksByNameContains(@RequestBody String name) {
        JavascriptFramework javascriptFramework;
        try {
            javascriptFramework = getRequestData(name);
        } catch (JsonProcessingException error) {
            log.error("JavascriptFrameworkController:getFrameworksByNameContains: Error occurred while processing request." + error.getMessage());
            return ResponseEntity.badRequest().body("Error occurred while processing request");
        }
        return ResponseEntity.ok(service.findAllByNameContains(javascriptFramework.getName()));
    }

    public JavascriptFramework getRequestData(String javascriptFramework) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(javascriptFramework, JavascriptFramework.class);
    }
}
