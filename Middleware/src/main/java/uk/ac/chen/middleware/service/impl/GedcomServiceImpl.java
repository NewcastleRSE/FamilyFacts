package uk.ac.chen.middleware.service.impl;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.*;
import org.gedcom4j.parser.GedcomParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uk.ac.chen.middleware.entity.PersonEntity;
import uk.ac.chen.middleware.service.GedcomService;
import uk.ac.chen.middleware.service.PersonService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author: Qiuyu
 */
@Service("GedcomService")
public class GedcomServiceImpl implements GedcomService {

    private static Logger logger = LoggerFactory.getLogger(GedcomServiceImpl.class);

    @Resource
    private PersonService personService;

    @Override
    public void loadGedcomFile(String filePath) {
        GedcomParser gp = new GedcomParser();
        try {
            gp.load(filePath);
        } catch (IOException | GedcomParserException e) {
            e.printStackTrace();
            logger.error("Can not load the gedcom file:{}", e.getMessage());
        }
        Gedcom gedcom = gp.getGedcom();

        /**
         * Add individuals and relationships between them
         */
        Map<String, Family> familyMap = gedcom.getFamilies();
        if (familyMap != null && familyMap.size() > 0) {
            addIndividualByFamily(familyMap);
        }

        /**
         * Add other individuals
         */
        Map<String, Individual> individuals = gedcom.getIndividuals();
        for (Individual individual : individuals.values()) {
            String[] individualName = getName(individual);
            List<PersonEntity> persons = personService.
                    getPersonByFullName(individualName[0], individualName[1]);
            if (persons == null || persons.size() == 0) {
                addIndividual(individual);
            }
        }
    }

    /**
     * Get name from the individual
     * @param individual
     * @return name[0] - firstname, name[1] - lastname
     */
    private String[] getName(Individual individual) {
        String[] name = new String[2];
        Arrays.fill(name, "");
        List<PersonalName> personalNames = individual.getNames();
        if (personalNames == null || personalNames.size() == 0) {
            return name;
        }
        if (personalNames.get(0) != null && personalNames.get(0).getGivenName() != null
                && personalNames.get(0).getGivenName().getValue() != null) {
            name[0] = personalNames.get(0).getGivenName().getValue();
        }
        if (personalNames.get(0) != null && personalNames.get(0).getSurname() != null
                && personalNames.get(0).getSurname().getValue() != null) {
            name[1] = personalNames.get(0).getSurname().getValue();
        }
        return name;
    }

    private int addIndividual(Individual individual) {
        if (individual == null) {
            return -1;
        }
        String[] name = getName(individual);
        Integer sex = "M".equals(individual.getSex().getValue()) ? 0 : 1;
        return personService.addPerson(name[0], name[1], sex, null, null, null);
    }

    private void addIndividualByFamily(Map<String, Family> familyMap) {
        /**
         * Add individuals and relationships between them
         */
        for (Family f : familyMap.values()) {
            int husbandId = -1;
            if (f.getHusband() != null) {
                Individual husband = f.getHusband().getIndividual();

                husbandId = addIndividual(husband);
            }
            int wifeId = -1;
            if (f.getWife() != null) {
                Individual wife = f.getWife().getIndividual();
                wifeId = addIndividual(wife);
            }

            if (husbandId != -1 && wifeId != -1) {
                personService.updateSpouseOfPerson(husbandId, wifeId);
            }

            if (f.getChildren() != null && f.getChildren().size() != 0) {
                List<IndividualReference> children = f.getChildren();
                for (IndividualReference childRef : children) {
                    Individual child = childRef.getIndividual();
                    int childId = addIndividual(child);
                    if (husbandId != -1) {
                        personService.updateFatherOfPerson(childId, husbandId);
                    }
                    if (wifeId != -1) {
                        personService.updateMotherOfPerson(childId, wifeId);
                    }
                }
            }
        }
    }
}
