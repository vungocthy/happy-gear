package com.notimplement.happygear.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private String commentId;
    private String content;
    private String commentParentId;
    private String userName;
    private Integer productId;
    private String avatarUrl;
    private List<CommentDto> replies;
}
