package org.bigdou.dbm.web;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.bigdou.dbm.api.GraphEdgeServiceI;
import org.bigdou.dbm.api.GraphServiceI;
import org.bigdou.dbm.dto.GraphDetailQry;
import org.bigdou.dbm.dto.GraphLayoutSaveCmd;
import org.bigdou.dbm.dto.GraphListQry;
import org.bigdou.dbm.dto.GraphRemoveCmd;
import org.bigdou.dbm.dto.GraphSaveCmd;
import org.bigdou.dbm.dto.TableGraphLayoutQry;
import org.bigdou.dbm.dto.data.GraphDetailDTO;
import org.bigdou.dbm.dto.data.GraphInfoDTO;
import org.bigdou.dbm.dto.data.TableGraphLayoutDTO;
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
@RequestMapping("graph")
public class GraphController {

    @Resource
    private GraphServiceI graphService;

    @Resource
    private GraphEdgeServiceI graphEdgeService;

    @GetMapping("layout")
    public SingleResponse<TableGraphLayoutDTO> queryTableGraphLayout(@RequestParam Long dbId,
                                                                    @RequestParam Long graphLayoutId) {
        TableGraphLayoutQry tableGraphLayoutQry = new TableGraphLayoutQry();
        tableGraphLayoutQry.setDbId(dbId);
        tableGraphLayoutQry.setGraphLayoutId(graphLayoutId);
        return this.graphService.queryTableGraphLayout(tableGraphLayoutQry);
    }

    @GetMapping("list")
    public MultiResponse<GraphInfoDTO> listGraph(@RequestParam Long dbId) {
        GraphListQry graphListQry = new GraphListQry();
        graphListQry.setDbId(dbId);
        return this.graphService.list(graphListQry);
    }

    @PostMapping("save")
    public SingleResponse<Long> saveGraph(@Validated @RequestBody GraphSaveCmd cmd) {
        return this.graphService.save(cmd);
    }

    @PostMapping("remove")
    public Response removeGraph(@Validated @RequestBody GraphRemoveCmd cmd) {
        return this.graphService.remove(cmd);
    }

    @GetMapping("detail")
    public SingleResponse<GraphDetailDTO> detailGraph(@RequestParam Long id) {
        GraphDetailQry graphDetailQry = new GraphDetailQry();
        graphDetailQry.setId(id);
        return this.graphService.detail(graphDetailQry);
    }

    @PostMapping("layout/save")
    public Response saveLayout(@Validated @RequestBody GraphLayoutSaveCmd cmd) {
        return this.graphEdgeService.saveLayout(cmd);
    }

}
