package com.notimplement.happygear.controllers;

import com.notimplement.happygear.service.ShopAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shopaddress")
@RequiredArgsConstructor
public class ShopAddressApi {

    private final ShopAddressService shopAddressService;

    @GetMapping("")
    public ResponseEntity<?> getAllShopAddress() {
        return ResponseEntity.ok(shopAddressService.getAllShopAddress());
    }
}
