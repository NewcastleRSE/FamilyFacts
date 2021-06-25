package uk.ac.chen.middleware.service;

/**
 * @author: Qiuyu
 */
public interface GedcomService {

    /**
     * Import a gedcom file
     * @param filePath The path of this file
     */
    void loadGedcomFile(String filePath);
}
