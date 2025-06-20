package com.project.demo.controller;

import com.project.demo.entity.GourmetMaster;
import com.project.demo.service.GourmetMasterService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *美食达人：(GourmetMaster)表控制层
 *
 */
@RestController
@RequestMapping("/gourmet_master")
public class GourmetMasterController extends BaseController<GourmetMaster,GourmetMasterService> {

    /**
     *美食达人对象
     */
    @Autowired
    public GourmetMasterController(GourmetMasterService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
