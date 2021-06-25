package uk.ac.chen.middleware.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Qiuyu
 */
@SpringBootTest
public class GedcomServiceTests {

    @Autowired
    private GedcomService gedcomService;

    @Test
    void testLoadGedcomFile() {
        String filePath = "/Users/chen/Desktop/Newcastle/Dissertation/database/sample.ged";
        gedcomService.loadGedcomFile(filePath);
    }
}
