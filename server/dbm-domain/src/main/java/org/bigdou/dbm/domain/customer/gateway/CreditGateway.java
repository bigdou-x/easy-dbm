package org.bigdou.dbm.domain.customer.gateway;

import org.bigdou.dbm.domain.customer.Credit;

//Assume that the credit info is in another distributed Service
public interface CreditGateway {
    Credit getCredit(String customerId);
}
