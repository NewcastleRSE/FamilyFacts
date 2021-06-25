package uk.ac.chen.middleware.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.chen.middleware.entity.results.JsonResult;
import uk.ac.chen.middleware.entity.results.Result;
import uk.ac.chen.middleware.entity.results.ResultCode;
import uk.ac.chen.middleware.service.FileService;
import uk.ac.chen.middleware.service.GedcomService;

import javax.annotation.Resource;

/**
 * @author: Qiuyu
 */

@RestController
@RequestMapping("file/")
public class FileController {

    @Resource
    private FileService fileService;

    @Resource
    private GedcomService gedcomService;

    @PostMapping("database/create")
    public JsonResult createDatabase(@RequestParam("database_path") String databasePath) {
        fileService.createDatabase(null, databasePath);
        return Result.success();
    }

    @PostMapping("database/open")
    public JsonResult openDatabase(@RequestParam("database_path") String databasePath) {
        return fileService.openDatabase(null, databasePath) ?
                Result.success() : Result.fail(ResultCode.FILE_NOT_EXIST);
    }

    @PostMapping("database/delete")
    public JsonResult deleteDatabase(@RequestParam("database_path") String databasePath) {
        return fileService.deleteDatabase(null, databasePath) ?
                Result.success() : Result.fail();
    }

    @PostMapping("database/rename")
    public JsonResult renameDatabase(@RequestParam("old_name") String oldName,
                                     @RequestParam("new_name") String newName,
                                     @RequestParam("database_path") String databasePath) {
        return fileService.renameDatabase(oldName, newName, databasePath) ?
                Result.success() : Result.fail();
    }

    @PostMapping("gedcom/import")
    public JsonResult importGedcomFile(@RequestParam("gedcom_path") String gedcomFilePath) {
        gedcomService.loadGedcomFile(gedcomFilePath);
        return Result.success();
    }

}
