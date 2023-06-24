package com.notimplement.happygear.model.dto;

import java.util.Map;

import com.google.auto.value.AutoValue.Builder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    private String subject;
    private String content;
    private Map<String, String> data;
    private String image;
    private String token;
}
