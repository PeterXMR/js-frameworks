package cz.eg.hr.controller;

import cz.eg.hr.data.JavascriptFramework;
import cz.eg.hr.repository.JavascriptFrameworkRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class JavascriptFrameworkControllerTest {

    @Autowired
    private JavascriptFrameworkController controller;

    @MockBean
    private JavascriptFrameworkRepository repository;

    @Test
    public void testGetAllFrameworks() throws ParseException {
        Date date = new SimpleDateFormat( "dd-MM-yyyy hh:mm:ss" ).parse( "01-01-2023 01:00:00");
        Iterable<JavascriptFramework> frameworks = Arrays.asList(
            new JavascriptFramework(1L,"Angular", 3.0, date , 5),
            new JavascriptFramework(2L,"React", 5.4, date , 4),
            new JavascriptFramework(3L,"Vue", 6.2, date , 3)
        );
        when(repository.findAll()).thenReturn(frameworks);

        ResponseEntity<Iterable<JavascriptFramework>> response = controller.getAllFrameworks();
        Iterable<JavascriptFramework> javascriptFrameworks = response.getBody();
        assert javascriptFrameworks != null;
        List<JavascriptFramework> frameworksList = StreamSupport
            .stream(javascriptFrameworks.spliterator(), false).toList();

        assertEquals(3, frameworksList.size());
        assertEquals("Angular", frameworksList.get(0).getName());
        assertEquals("Vue", frameworksList.get(2).getName());
    }
}
