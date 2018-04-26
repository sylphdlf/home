package com.dlf.business.manager.question;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.question.QuestionReqDTO;

public interface QuestionService {
    /**
     * 添加或更新
     * @param questionReqDTO
     * @return
     */
    GlobalResultDTO addOrUpdateQuestion(QuestionReqDTO questionReqDTO);
}