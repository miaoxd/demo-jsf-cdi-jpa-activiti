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

import com.github.werpu.tomeedemo.orm.HelloEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Werner Punz (latest modification by $Author$)
 * @version $Revision$ $Date$
 */

@Stateless
public class HelloEJB {
    @PersistenceContext(name = "Demo_Unit")
    EntityManager em;

    public String getHello() {
        Query query = em.createQuery("select hello from HelloEntity hello");
        if (query.getResultList().size() == 0) {
            HelloEntity entity = new HelloEntity();
            em.persist(entity);
            return entity.getHelloWorld();
        }
        HelloEntity entity = (HelloEntity) query.getResultList().get(0);
        return entity.getHelloWorld();
    }
}
