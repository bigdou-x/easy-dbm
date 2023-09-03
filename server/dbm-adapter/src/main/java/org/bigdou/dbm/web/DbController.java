package org.bigdou.dbm.web;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.bigdou.dbm.api.DbServiceI;
import org.bigdou.dbm.dto.DbConnectCmd;
import org.bigdou.dbm.dto.DbDetailByDbIdQry;
import org.bigdou.dbm.dto.DbDisConnectedCmd;
import org.bigdou.dbm.dto.DbRemoveCmd;
import org.bigdou.dbm.dto.DbSaveCmd;
import org.bigdou.dbm.dto.TableNameListByDbIdQry;
import org.bigdou.dbm.dto.TableSyncCmd;
import org.bigdou.dbm.dto.data.DbDetailDTO;
import org.bigdou.dbm.dto.data.DbInfoDTO;
import org.bigdou.dbm.dto.data.DbOptionDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 数据源管理
 * 这是管理数据源的接口集合
 * @module bigdou
 *
 * @author xuqing F00722
 * @date 2023/1/10 17:27
 */
@RestController
@RequestMapping("db")
public class DbController {

    @Resource
    private DbServiceI dbService;

    @GetMapping("list")
    public MultiResponse<DbInfoDTO> list(@RequestParam(required = false) String name) {
        return this.dbService.list(name);
    }

    @GetMapping("options")
    public MultiResponse<DbOptionDTO> listOptions() {
        return this.dbService.listOptions();
    }

    @GetMapping("detail")
    public SingleResponse<DbDetailDTO> detail(@RequestParam Long id) {
        DbDetailByDbIdQry dbDetailByDbIdQry = new DbDetailByDbIdQry();
        dbDetailByDbIdQry.setDbId(id);
        return this.dbService.detail(dbDetailByDbIdQry);
    }

    @PostMapping("save")
    public SingleResponse<Long> save(@Validated @RequestBody DbSaveCmd cmd) {
        return this.dbService.save(cmd);
    }

    @PostMapping("remove")
    public Response remove(@Validated @RequestBody DbRemoveCmd cmd) {
        return this.dbService.remove(cmd);
    }

    @GetMapping("tableNames")
    public MultiResponse<String> queryTableNames(@RequestParam Long dbId) {
        TableNameListByDbIdQry tableNameListByDbIdQry = new TableNameListByDbIdQry();
        tableNameListByDbIdQry.setDbId(dbId);
        return this.dbService.queryTableNames(tableNameListByDbIdQry);
    }

    @PostMapping("sync")
    public Response sync(@Validated @RequestBody TableSyncCmd cmd) {
        return this.dbService.sync(cmd);
    }

    @PostMapping("connect")
    public Response connect(@Validated @RequestBody DbConnectCmd cmd) {
        return this.dbService.connect(cmd);
    }

    @PostMapping("closeConnected")
    public Response closeConnected(@Validated @RequestBody DbDisConnectedCmd cmd) {
        return this.dbService.closeConnected(cmd);
    }

}
