package com.tentelemed.demo.activiticdi.service;

import com.tentelemed.demo.activiticdi.bo.Message;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    ProcessEngine processEngine;

    //@Inject
    RuntimeService runtimeService;

    //@Inject
    private RepositoryService repositoryService;

    @Produces
    @Named("processDefinitionList")
    public List<ProcessDefinition> getProcessDefinitionList() {
        initRepositoryService();
        return repositoryService.createProcessDefinitionQuery()
                .list();
    }

    public void resetProcessEngine() {
        repositoryService = null;
    }

    /**
     * Il est possible de définir les ressources a charger dans le fichier
     * processes.xml au lieu de les définir dans le code
     */
    private void initRepositoryService() {
        if (repositoryService == null) {
            repositoryService = processEngine.getRepositoryService();
            Deployment d = repositoryService
                    .createDeployment()
                    .addClasspathResource("diagrams/drools.bpmn")
                    .addClasspathResource("diagrams/process.bpmn20.xml")
                    .addClasspathResource("rules/hello.drl")
                    .deploy();
            runtimeService = processEngine.getRuntimeService();
        }
    }

    public void startProcess(String processKey) {
        try {
            initRepositoryService();

            log.info("Start process : "+processKey);

            Map<String, Object> variableMap = new HashMap<>();
            Message message = new Message();
            message.setMessage("Hello World");
            message.setStatus(Message.HELLO);
            variableMap.put("m", message);

            ProcessInstance inst = runtimeService.startProcessInstanceByKey(processKey, variableMap);
            Message result = (Message) inst.getProcessVariables().get("m");

            // TODO : formulaires pour taches utilisateur
            System.err.println("END");

        } catch (Exception e) {
            e.printStackTrace();
            log.error(null, e);
        }
    }

}
