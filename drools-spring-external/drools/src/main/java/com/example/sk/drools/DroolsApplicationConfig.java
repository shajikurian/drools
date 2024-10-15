package com.example.sk.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainerSessionsPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * Created on 10/12/2024
 * {@code @authors} shaji
 */
@Configuration
public class DroolsApplicationConfig {
    private static final KieServices kieServices = KieServices.Factory.get();
    //private static final String RULES_CUSTOMER_RULES_DRL = "rules/loan_rate.drl";
   /* @Bean
    public KieContainer kieContainer() {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_CUSTOMER_RULES_DRL));
        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        return kieContainer;
    }*/

   /* @Bean
    public KieContainer kieContainer() {
        KieServices ks = KieServices.Factory.get();
        KieRepository kr = ks.getRepository();
        Resource ex2Res = ks.getResources().newFileSystemResource(getFile("apprules"));
        KieModule kModule = kr.addKieModule(ex2Res);
        return ks.newKieContainer(kModule.getReleaseId());
    }*/

   /* @Bean
    public KieContainer kieContainer() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.newKieContainer(ks.newReleaseId("com.example.sk", "apprules", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = kieServices.newKieScanner( kieContainer );

        // Start the KieScanner polling the Maven repository every 10 seconds
        kScanner.start( 10000L );
        return kieContainer;
    }*/

    @Bean
    public KieContainerSessionsPool kieContainerPool() {
        KieServices ks = KieServices.Factory.get();
        return ks.getKieClasspathContainer().newKieSessionsPool(10);
    }



    public File getFile(String exampleName) {
        File folder = new File( DroolsApplicationConfig.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        File exampleFolder = null;
        while (folder != null) {
            exampleFolder = new File(folder, exampleName);
            if (exampleFolder.exists()) {
                break;
            }
            exampleFolder = null;
            folder = folder.getParentFile();
        }

        if (exampleFolder != null) {

            File targetFolder = new File(exampleFolder, "target");
            if (!targetFolder.exists()) {
                throw new RuntimeException("The target folder does not exist, please build project " + exampleName + " first");
            }

            for (String str : targetFolder.list()) {
                if (str.startsWith(exampleName) && str.endsWith(".jar") && !str.endsWith("-sources.jar") && !str.endsWith("-tests.jar") && !str.endsWith("-javadoc.jar")) {
                    return new File(targetFolder, str);
                }
            }
        }

        throw new RuntimeException("The target jar does not exist, please build project " + exampleName + " first");
    }
}
