/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.skywaking.apm.testcase.storage.provider;

import org.apache.servicecomb.provider.pojo.RpcSchema;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.apache.skywaking.apm.testcase.servicecomb.schemma.Hello;
import org.apache.skywaking.apm.testcase.servicecomb.schemma.models.Person;

@RpcSchema(schemaId = "catchH2")
public class CatchPojoHelloImpl implements Hello {

    private static Logger logger = Logger.getLogger(CatchPojoHelloImpl.class);
    private static final String CREATE_TABLE_SQL = " CREATE TABLE IF NOT EXISTS test_007(id VARCHAR(13) PRIMARY KEY ,value VARCHAR(13) NOT NULL)";
    private static final String INSERT_DATA_SQL = "INSERT INTO test_007(id, value) VALUES(?,?)";
    private static final String SELECT_DATA_SQL = "SELECT id,value FROM test_007 ";
    private static final String DROP_TABLE_SQL = " DROP table IF  EXISTS  test_007";

    @Override
    public String sayHi(String name) {
        SQLExecutor sqlExecute = null;
        try {
            sqlExecute = new SQLExecutor();
            sqlExecute.createTable(CREATE_TABLE_SQL);
            sqlExecute.insertData(INSERT_DATA_SQL, "h2", "ok");
            sqlExecute.selectData(SELECT_DATA_SQL);
            sqlExecute.dropTable(DROP_TABLE_SQL);
        } catch (SQLException e) {
            logger.error("Failed to execute sql.", e);
        } finally {
            if (sqlExecute != null) {
                try {
                    sqlExecute.closeConnection();
                } catch (SQLException e) {
                    logger.error("Failed to close connection.", e);
                }
            }
        }
        return "invoke catcheH2 success";
    }

    @Override
    public String sayHello(Person person) {
        return "Pojo Hello person " + person.getName();
    }
}
