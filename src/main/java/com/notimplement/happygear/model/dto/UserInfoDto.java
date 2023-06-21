package com.notimplement.happygear.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    private String displayName;
    private String email;
    private String phoneNumber;
    private String photoURL;
    private String providerId;
    private String uid;
}
