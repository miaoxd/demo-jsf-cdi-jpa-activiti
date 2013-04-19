/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.tentelemed.demo.activiticdi.service;

import com.tentelemed.demo.activiticdi.bo.HelloEntity;
import com.tentelemed.demo.activiticdi.bo.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author Werner Punz (latest modification by $Author$)
 * @version $Revision$ $Date$
 */

@Stateless
public class HelloService extends DefaultService {

    public String getHello() {
        Query query = em.createQuery("select hello from HelloEntity hello");
        String res = "";
        if (query.getResultList().size() == 0) {
            HelloEntity entity = new HelloEntity();
            em.persist(entity);
            res += entity.getHelloWorld();
        }
        HelloEntity entity = (HelloEntity) query.getResultList().get(0);
        res += entity.getHelloWorld();
        res += "DEBUT";

        TypedQuery<User> query2 = em.createQuery("select u from User u", User.class);
        for (User u : query2.getResultList()) {
            res += "\n"+u.getLogin();
        }

        res += "FIN";
        return res;
    }
}
