package com.project.demo.controller;

import com.project.demo.entity.FoodQuestions;
import com.project.demo.service.FoodQuestionsService;
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
 *美食提问：(FoodQuestions)表控制层
 *
 */
@RestController
@RequestMapping("/food_questions")
public class FoodQuestionsController extends BaseController<FoodQuestions,FoodQuestionsService> {

    /**
     *美食提问对象
     */
    @Autowired
    public FoodQuestionsController(FoodQuestionsService service) {
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
