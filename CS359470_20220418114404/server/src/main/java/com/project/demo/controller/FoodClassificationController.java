package com.project.demo.controller;

import com.project.demo.entity.FoodClassification;
import com.project.demo.service.FoodClassificationService;
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
 *美食分类：(FoodClassification)表控制层
 *
 */
@RestController
@RequestMapping("/food_classification")
public class FoodClassificationController extends BaseController<FoodClassification,FoodClassificationService> {

    /**
     *美食分类对象
     */
    @Autowired
    public FoodClassificationController(FoodClassificationService service) {
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
