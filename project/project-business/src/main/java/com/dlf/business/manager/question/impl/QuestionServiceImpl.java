package com.dlf.business.manager.question.impl;

import com.dlf.business.manager.question.QuestionService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.enums.GlobalResultEnum;
import com.dlf.model.dto.question.QuestionReqDTO;
import com.dlf.model.dto.question.QuestionSearchDTO;
import com.dlf.model.mapper.QuestionMapper;
import com.dlf.model.po.Question;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    QuestionMapper questionMapper;

    @Transactional
    public GlobalResultDTO addOrUpdateQuestion(QuestionReqDTO questionReqDTO) {
        try {
            Question question = new Question();
            BeanUtils.copyProperties(questionReqDTO, question);
            if(null != question.getId()){
                questionMapper.updateByPrimaryKey(question);
            }else{
                questionMapper.insert(question);
            }
            return GlobalResultDTO.SUCCESS();
        }catch (Exception e){
            e.printStackTrace();
            return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getCode(),e.getMessage());
        }
    }

    public GlobalResultDTO queryPageByParams(QuestionSearchDTO questionSearchDTO) {
        try {

            return GlobalResultDTO.SUCCESS();
        }catch (Exception e){
            e.printStackTrace();
            return GlobalResultDTO.FAIL("");
        }
    }
}