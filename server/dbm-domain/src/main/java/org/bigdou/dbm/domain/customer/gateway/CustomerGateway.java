package org.bigdou.dbm.domain.customer.gateway;

import org.bigdou.dbm.domain.customer.Customer;

public interface CustomerGateway {
    Customer getByById(String customerId);
}
