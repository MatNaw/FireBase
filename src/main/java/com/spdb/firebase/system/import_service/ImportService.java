package com.spdb.firebase.system.import_service;

import com.spdb.firebase.domain.brigade.Brigade;
import com.spdb.firebase.domain.brigade.BrigadeService;
import com.spdb.firebase.system.SpringProfile;
import com.spdb.firebase.system.config.StorageConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImportService {
    private final Environment environment;
    private final StorageConfiguration storageConfiguration;
    private final BrigadesImporter brigadesImporter;
    private final BrigadeService brigadeService;

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        if(!Arrays.asList(environment.getActiveProfiles()).contains(SpringProfile.IMPORT))
            return;

        log.info("[IMPORT SERVICE] Start importing fire brigades");
        importBrigadesFromFiles();
        log.info("[IMPORT SERVICE] End importing fire brigades");
    }

    private void importBrigadesFromFiles() {
        try {
            File importDataDirectory = Paths.get(storageConfiguration.getImportDirectory()).toFile();
            FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Xlsx file", "xlsx");
            File[] files = importDataDirectory.listFiles();
            List<File> listOfFiles = files != null ? List.of(files) : List.of();

            for (File file : listOfFiles) {
                if (!file.isDirectory() && fileFilter.accept(file)) {
                    log.info("[IMPORT SERVICE] Start importing file: " + file.getName());
                    importBrigadesFromXlsxToDb(file);
                    log.info("[IMPORT SERVICE] Finish importing file: " + file.getName());
                }
            }
        } catch (IOException e) {
            log.error("[IMPORT FILE EXCEPTION]: Cannot move imported files to appropriate directories (errors/finalising)");
        }
    }

    private void importBrigadesFromXlsxToDb(File file) throws IOException {
        List<BrigadeXlsxModel> brigades = brigadesImporter.importData(file);

        log.info("[IMPORT SERVICE] Start saving fire brigades to db");
        for (BrigadeXlsxModel brigade : brigades) {
            Brigade brigadeToSave = prepareBrigade(brigade);
            brigadeService.addBrigade(brigadeToSave);
        }
        log.info("[IMPORT SERVICE] End saving fire brigades to db");
    }

    private Brigade prepareBrigade(BrigadeXlsxModel brigadeXlsx) {
        return Brigade.builder()
                .name(brigadeXlsx.getName())
                .city(brigadeXlsx.getCity())
                .postalCode(brigadeXlsx.getPostalCode())
                .street(brigadeXlsx.getStreet())
                .squadMaxAmount(randomNumber())
                .build();
    }

    private Long randomNumber() {
        Random r = new Random();
        return r.longs(1, 5).limit(1).findFirst().getAsLong();
    }
}
