package org.bigdou.dbm.customer;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.catchlog.CatchAndLog;
import org.bigdou.dbm.api.CustomerServiceI;
import org.bigdou.dbm.dto.CustomerAddCmd;
import org.bigdou.dbm.dto.CustomerListByNameQry;
import org.bigdou.dbm.dto.data.CustomerDTO;
import org.bigdou.dbm.customer.executor.CustomerAddCmdExe;
import org.bigdou.dbm.customer.executor.query.CustomerListByNameQryExe;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
@CatchAndLog
public class CustomerServiceImpl implements CustomerServiceI {

    @Resource
    private CustomerAddCmdExe customerAddCmdExe;

    @Resource
    private CustomerListByNameQryExe customerListByNameQryExe;

    public Response addCustomer(CustomerAddCmd customerAddCmd) {
        return customerAddCmdExe.execute(customerAddCmd);
    }

    @Override
    public MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry) {
        return customerListByNameQryExe.execute(customerListByNameQry);
    }

}