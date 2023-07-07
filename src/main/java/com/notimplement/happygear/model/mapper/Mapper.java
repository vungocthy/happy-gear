package com.notimplement.happygear.model.mapper;

import com.notimplement.happygear.entities.*;
import com.notimplement.happygear.model.dto.*;

public class Mapper {
    public static BrandDto toBrandDto(Brand b) {
        return BrandDto.builder()
            .brandId(b.getBrandId())
            .brandName(b.getBrandName())
            .status(b.getStatus())
            .build();
    }
    public static CategoryDto toCategoryDto(Category c){
        return CategoryDto.builder()
            .categoryId(c.getCategoryId())
            .categoryName(c.getCategoryName())
            .categoryPicture(c.getCategoryPicture())
            .status(c.getStatus())
            .build();
    }
    public static CommentDto toCommentDto(Comment c){
        return CommentDto.builder()
            .commentId(c.getCommentId())
            .content(c.getContent())
            .commentParentId(c.getCommentParentId())
            .productId(c.getProduct().getProductId())
            .userName(c.getUser().getUsername())
            .build();
    }
    public static OrderDto toOrderDto(Order o){
        return OrderDto.builder()
            .orderId(o.getOrderId())
            .date(o.getDate())
            .status(o.getStatus())
            .total(o.getTotal())
            .userName(o.getUser().getUsername())
            .build();
    }
    public static OrderDetailDto toOrderDetailDto(OrderDetail od){
        return OrderDetailDto.builder()
            .detailId(od.getDetailId())
            .orderId(od.getOrder().getOrderId())
            .productId(od.getProduct().getProductId())
            .quantity(od.getQuantity())
            .price(od.getPrice())
            .status(od.getStatus())
            .build();
    }
    public static ProductDto toProductDto(Product p){
        return ProductDto.builder()
            .productId(p.getProductId())
            .productName(p.getProductName())
            .price(p.getPrice())
            .quantity(p.getQuantity())
            .insuranceInfo(p.getInsuranceInfo())
            .picture(p.getPicture())
            .brandId(p.getBrand().getBrandId())
            .categoryId(p.getCategory().getCategoryId())
            .status(p.getStatus())
            .build();
    }
    public static ProductDescriptionDto toProductDescriptionDto(ProductDescription pd){
        return ProductDescriptionDto.builder()
            .productDescriptionId(pd.getProductDescriptionId())
            .keycap(pd.getKeycap())
            .switchKeyBoard(pd.getSwitchKeyBoard())
            .typeKeyboard(pd.getTypeKeyboard())
            .connect(pd.getConnect())
            .led(pd.getLed())
            .freigh(pd.getFreigh())
            .itemDimension(pd.getItemDimension())
            .cpu(pd.getCpu())
            .ram(pd.getRam())
            .operatingSystem(pd.getOperatingSystem())
            .battery(pd.getBattery())
            .hardDisk(pd.getHardDisk())
            .graphicCard(pd.getGraphicCard())
            .keyBoard(pd.getKeyBoard())
            .audio(pd.getAudio())
            .wifi(pd.getWifi())
            .bluetooth(pd.getBluetooth())
            .color(pd.getColor())
            .frameRate(pd.getFrameRate())
            .screenSize(pd.getScreenSize())
            .screenType(pd.getScreenType())
            .productId(pd.getProduct().getProductId())
            .build();
    }
    public static ProductPictureDto toProductPictureDto(ProductPicture pp){
        return ProductPictureDto.builder()
            .pictureId(pp.getPictureId())
            .pictureUrl(pp.getPictureUrl())
            .status(pp.getStatus())
            .productId(pp.getProduct().getProductId())
            .build();
    }
    public static UserDto toUserDto(User u){
        return UserDto.builder()
            .username(u.getUsername())
            .fullName(u.getFullName())
            .address(u.getAddress())
            .password(u.getPassword())
            .email(u.getEmail())
            .phoneNumber(u.getPhoneNumber())
            .status(u.getStatus())
            .gender(u.getGender())
            .roleId(u.getRole().getRoleId())
            .build();
    }
    public static RoleDto toRoleDto(Role r){
        return RoleDto.builder()
            .roleId(r.getRoleId())
            .roleName(r.getRoleName())
            .status(r.getStatus())
            .build();
    }

    public static ShopAddressDto toShopAddressDto(ShopAddress shopAddress) {
        return ShopAddressDto.builder()
            .shopAddressId(shopAddress.getShopAddressId())
            .address(shopAddress.getAddress())
            .longitude(shopAddress.getLongitude())
            .latitude(shopAddress.getLatitude())
            .build();
    }
}
