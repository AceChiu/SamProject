package com.ace.request;

import com.ace.common.ClarifyEnum;
import lombok.Data;

import java.util.List;

/**
 * @author: ACE.CHIU
 * @create: 2023-03-19
 */
@Data
public class TeacherDtoRequest {
    private String username;

    private String imgUrl;
    private String selfIntroduction;
    private String partOfCompany;
    private String suitAgeFrom;
    private String suitAgeTo;
    private List<String> labelNames;
    private ClarifyEnum clarify;
 }
