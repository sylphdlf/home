package com.dlf.web.controller;

import com.dlf.business.manager.python.IPythonDataTransferService;
import com.dlf.business.manager.question.QuestionService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.question.QuestionReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/5/7.
 */
@Controller
@RequestMapping("/question")
public class QuestionAction {

    @Autowired
    QuestionService questionService;

    @RequestMapping(value = "/questionAdd",method = RequestMethod.POST)
    @ResponseBody
    public GlobalResultDTO questionAdd(@RequestBody QuestionReqDTO questionReqDTO){

        return questionService.addOrUpdateQuestion(questionReqDTO);
    }

}
