package com.monitor.common.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenModel {

    private long loginTime;

    private String username;

}
