package com.ace.common;

import lombok.Getter;

public enum TaskStatusEnum {
    USABLE("可使用"),
    PROCESSED("執行中"),
    FAILED("失敗"),
    DONE("完成"),
    NON_EXECUTED("沒有執行");

    @Getter
    private String status;

    private TaskStatusEnum(String status) {
        this.status = status;
    }
}
