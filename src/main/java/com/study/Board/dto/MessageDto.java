package com.study.Board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Getter
@AllArgsConstructor
public class MessageDto {

    private String message;             // 사용자에게 전달할 메시지
    private String redirectUri;         // 리다이렉트 Uri
    private RequestMethod method;       // HTTP 요청 method
    private Map<String, Object> data;   // 화면(view)으로 전달할 데이터 key-value
}
