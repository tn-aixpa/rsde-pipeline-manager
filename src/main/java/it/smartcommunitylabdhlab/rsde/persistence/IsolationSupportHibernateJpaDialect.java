/*******************************************************************************
 * Copyright 2015-2019 Smart Community Lab, FBK
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 ******************************************************************************/

package it.smartcommunitylabdhlab.rsde.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

import org.springframework.jdbc.datasource.ConnectionHandle;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;

/**
 * @author raman
 *
 */
public class IsolationSupportHibernateJpaDialect extends HibernateJpaDialect {

    private ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();
    private ThreadLocal<Integer> originalIsolation = new ThreadLocal<Integer>();

    @SuppressWarnings("null")
    @Override
    public Object beginTransaction(EntityManager entityManager, TransactionDefinition definition)
        throws PersistenceException, SQLException, TransactionException {
        boolean readOnly = definition.isReadOnly();
        ConnectionHandle handle = this.getJdbcConnection(entityManager, readOnly);
        Connection connection = handle.getConnection();
        connectionThreadLocal.set(connection);
        originalIsolation.set(DataSourceUtils.prepareConnectionForTransaction(connection, definition));

        entityManager.getTransaction().begin();

        return prepareTransaction(entityManager, readOnly, definition.getName());
    }

    @SuppressWarnings("null")
    @Override
    public void cleanupTransaction(Object transactionData) {
        try {
            super.cleanupTransaction(transactionData);
            DataSourceUtils.resetConnectionAfterTransaction(connectionThreadLocal.get(), originalIsolation.get(), true);
        } finally {
            connectionThreadLocal.remove();
            originalIsolation.remove();
        }
    }
}
