package com.notimplement.happygear.service.imp;

import com.notimplement.happygear.model.dto.ShopAddressDto;
import com.notimplement.happygear.repositories.ShopAddressRepository;
import com.notimplement.happygear.service.ShopAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopAddressServiceImpl implements ShopAddressService {

    private final ShopAddressRepository shopAddressRepository;

    @Override
    public List<ShopAddressDto> getAllShopAddress() {
        return shopAddressRepository.findAllShopAddress().stream().map(
                shopAddress -> ShopAddressDto.builder()
                .shopAddressId(shopAddress.getShopAddressId())
                .address(shopAddress.getAddress())
                .latitude(shopAddress.getLatitude())
                .longitude(shopAddress.getLongitude())
                .build()).collect(Collectors.toList());
    }
}
