package com.youngzi.contentcenter.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String wxId;
    private String wxNickname;
    private String roles;
    private String avatarUrl;
    private Date createTime;
    private Date updateTime;
    private Integer bonus;
}