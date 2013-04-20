package com.tentelemed.demo.activiticdi.service;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 20/04/13
 * Time: 13:46
 */
public class ProcessService {

    @Inject
    private RepositoryService repositoryService;

    @Produces
    @Named("processDefinitionList")
    public List<ProcessDefinition> getProcessDefinitionList() {
        return repositoryService.createProcessDefinitionQuery()
                .list();
    }

}
