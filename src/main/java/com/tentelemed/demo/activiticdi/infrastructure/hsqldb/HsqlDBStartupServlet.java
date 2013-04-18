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

package com.tentelemed.demo.activiticdi.infrastructure.hsqldb;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * @author Werner Punz (latest modification by $Author$)
 * @version $Revision$ $Date$
 *
 * This servlet starts the hsql db we also could
 * use it embedded but this way we can have a look
 * into the running db
 */
@WebServlet(urlPatterns = "/db/*", loadOnStartup = 1)
public class HsqlDBStartupServlet extends HttpServlet
{
    Logger log = LoggerFactory.getLogger(HsqlDBStartupServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            log.info("Starting Database");
            HsqlProperties p = new HsqlProperties();
            p.setProperty("server.database.0", "file:data/hsqldb");
            p.setProperty("server.dbname.0", "testdb2");
            p.setProperty("server.port", "9001");
            Server server = new Server();
            server.setProperties(p);
            server.setLogWriter(null);
            server.setErrWriter(null);
            server.start();
        }
        catch (ServerAcl.AclFormatException | IOException ex) {
            throw new ServletException(ex);
        }
    }
}
