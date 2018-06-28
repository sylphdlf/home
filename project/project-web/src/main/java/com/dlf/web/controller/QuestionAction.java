package com.dlf.web.controller;

import com.dlf.business.manager.question.QuestionService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.question.QuestionReqDTO;
import com.dlf.model.dto.question.QuestionSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/5/7.
 */
@Controller
@RequestMapping("/question")
public class QuestionAction {

    @Autowired
    QuestionService questionService;

    /**
     * 新增或更新
     * @param questionReqDTO
     * @return
     */
    @RequestMapping(value = "/questionAdd",method = RequestMethod.POST)
    @ResponseBody
    public GlobalResultDTO questionAdd(@RequestBody QuestionReqDTO questionReqDTO){

        return questionService.addOrUpdateQuestion(questionReqDTO);
    }

    /**
     * 分页查询
     * @param questionSearchDTO
     * @return
     */
    @RequestMapping(value = "/queryPageByParams", method = RequestMethod.POST)
    @ResponseBody
    public GlobalResultDTO queryPageByParams(@RequestBody QuestionSearchDTO questionSearchDTO){

        return questionService.queryPageByParams(questionSearchDTO);
    }
}
