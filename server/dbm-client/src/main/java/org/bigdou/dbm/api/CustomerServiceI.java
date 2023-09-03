package org.bigdou.dbm.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import org.bigdou.dbm.dto.CustomerAddCmd;
import org.bigdou.dbm.dto.CustomerListByNameQry;
import org.bigdou.dbm.dto.data.CustomerDTO;

public interface CustomerServiceI {

    Response addCustomer(CustomerAddCmd customerAddCmd);

    MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry);
}
