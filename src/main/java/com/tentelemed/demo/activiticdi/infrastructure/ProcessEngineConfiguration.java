package com.tentelemed.demo.activiticdi.infrastructure;

import org.activiti.cdi.CdiStandaloneProcessEngineConfiguration;
import org.activiti.cdi.spi.ProcessEngineLookup;
import org.activiti.engine.ProcessEngine;

public class ProcessEngineConfiguration implements ProcessEngineLookup {

    private ProcessEngine processEngine;

    public int getPrecedence() {
        return 10;
    }

    public ProcessEngine getProcessEngine() {
        processEngine = new CdiStandaloneProcessEngineConfiguration()
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