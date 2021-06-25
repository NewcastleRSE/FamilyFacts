package uk.ac.chen.middleware.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author: Qiuyu
 */
@SpringBootTest
public class FileServiceTests {
    @Resource
    private FileService fileService;

    @Test
    void testCreateDatabase() {
        String databaseName = "";
        String databasePath = "/Users/chen/Desktop/Newcastle/Dissertation/" +
                "code/FamilyFacts/Middleware/src/main/resources/sqlite/ged.db";
        fileService.createDatabase(databaseName, databasePath);
    }

    @Test
    void testOpenDatabase() {
        String databasePath = "/Users/chen/Desktop/Newcastle/Dissertation/" +
                "code/FamilyFacts/Middleware/src/main/resources/sqlite/test.db";
        // /Users/chen/Desktop/Newcastle/Dissertation/code/FamilyFacts/Middleware/src/main/resources/sqlite/test.db
        Assertions.assertTrue(fileService.openDatabase(null, databasePath));
    }

    @Test
    void renameDatabase() {
        String oldName = "fileTest";
        String newName = "newFileTest";
        String databasePath = "/Users/chen/Desktop/Newcastle/Dissertation/" +
                "code/FamilyFacts/Middleware/src/main/resources/sqlite/";
        Assertions.assertTrue(fileService.renameDatabase(oldName, newName, databasePath));
    }

    @Test
    void testDeleteDatabase() {
        String databasePath = "/Users/chen/Desktop/Newcastle/Dissertation/" +
                "code/FamilyFacts/Middleware/src/main/resources/sqlite/fileTest.db";
        Assertions.assertTrue(fileService.deleteDatabase(null, databasePath));
    }

}
