package cz.eg.hr.service;

import cz.eg.hr.data.JavascriptFramework;

import java.util.List;
import java.util.Optional;

public interface JavascriptFrameworkService {

    public Iterable<JavascriptFramework> findAll();
    public JavascriptFramework save(JavascriptFramework javascriptFramework);
    public boolean existById(Long id);
    public Optional<JavascriptFramework> findById(Long id);
    public void deleteById(Long id);
    public List<JavascriptFramework> findAllByNameContains(String name);
}
