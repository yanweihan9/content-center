package com.youngzi.contentcenter.dto.content;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "share")
public class ShareDTO {

    private Integer id;
    private Integer userId;
    private String title;
    private Date createTime;
    private Date updateTime;
    private byte[] isOriginal;
    private String author;
    private String cover;
    private String summary;
    private Integer price;
    private String downloadUrl;
    private Integer buyCount;
    private byte[] showFlag;
    private String auditStatus;
    private String reason;

    private String wxNickName;
}