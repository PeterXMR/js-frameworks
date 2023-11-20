package cz.eg.hr.service;

import cz.eg.hr.data.JavascriptFramework;
import cz.eg.hr.repository.JavascriptFrameworkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JavascriptFrameworkServiceImpl implements JavascriptFrameworkService {

    public final JavascriptFrameworkRepository repository;

    public JavascriptFrameworkServiceImpl(JavascriptFrameworkRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<JavascriptFramework> findAll() {
        return repository.findAll();
    }

    @Override
    public JavascriptFramework save(JavascriptFramework javascriptFramework) {
        return repository.save(javascriptFramework);
    }

    @Override
    public Optional<JavascriptFramework> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean existById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<JavascriptFramework> findAllByNameContains(String name) {
        return repository.findAllByNameContains(name);
    }
}
