package com.tentelemed.demo.activiticdi.service;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 20/04/13
 * Time: 23:18
 */
@Stateless
//@RolesAllowed("authenticated-user")
@Named
public class ProcessService extends DefaultService {

    @Inject
    RuntimeService runtimeService;

    @Inject
    private RepositoryService repositoryService;

    @Produces
    @Named("processDefinitionList")
    public List<ProcessDefinition> getProcessDefinitionList() {
        return repositoryService.createProcessDefinitionQuery()
                .list();
    }

    public void startProcess(String processKey) {
        try {
            log.info("Start process : "+processKey);
            ProcessInstance inst = runtimeService.startProcessInstanceByKey(processKey);

            // TODO : formulaires pour taches utilisateur


        } catch (Exception e) {
            e.printStackTrace();
            log.error(null, e);
        }
    }

}
