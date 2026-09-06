package com.kenneth.remind_me.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ResponseWrapper<T>{
    private T data;
    private String response;
}
