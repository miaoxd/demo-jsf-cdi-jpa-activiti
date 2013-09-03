package com.tentelemed.demo.activiticdi.infrastructure;

import org.activiti.cdi.CdiStandaloneProcessEngineConfiguration;
import org.activiti.cdi.spi.ProcessEngineLookup;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.persistence.deploy.Deployer;
import org.activiti.engine.impl.rules.RulesDeployer;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe est automatiquement découverte par Activiti, car déclarée dans le
 * fichier org.activiti.cdi.spi.ProcessEngineLookup
 *
 * Si ce fichier est laissé vide, Activiti instanciera automatiquement l'implémentation
 * par default, qui utilise le fichier activiti.cfg.xml
 */
public class ProcessEngineConfiguration implements ProcessEngineLookup {

    private ProcessEngine processEngine;

    public int getPrecedence() {
        return 10;
    }

    public ProcessEngine getProcessEngine() {

        // pour l'integration Drools, il faut déclarer ceci
        // Une StateFullKnowledgeSession sera créé par default
        RulesDeployer deployer = new RulesDeployer();
        List<Deployer> deployers = new ArrayList<>();
        deployers.add(deployer);

        processEngine = new CdiStandaloneProcessEngineConfiguration()
                .setCustomPostDeployers(deployers) // Drools
                .setDatabaseSchemaUpdate("true")
                .setJdbcDriver("org.h2.Driver")
                .setJdbcUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJobExecutorActivate(true)
                .buildProcessEngine();

        return processEngine;
    }

    public void ungetProcessEngine() {
        processEngine.close();
    }
}