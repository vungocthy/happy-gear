package com.notimplement.happygear.model.wallet;

import java.io.Serializable;
import java.util.List;

import com.google.auto.value.AutoValue.Builder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerMemberShipDto implements Serializable{
    private CustomerDto customer;
    private MembershipDto membership;
    private LevelDto nextLevel;
    private List<LevelDto> levelList;
    private List<WalletDto> walletList;
}
