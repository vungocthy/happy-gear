package com.notimplement.happygear.service.imp;

import com.notimplement.happygear.entities.ProductDescription;
import com.notimplement.happygear.model.dto.ProductDescriptionDto;
import com.notimplement.happygear.model.mapper.Mapper;
import com.notimplement.happygear.repositories.ProductDescriptionRepository;
import com.notimplement.happygear.repositories.ProductRepository;
import com.notimplement.happygear.service.ProductDescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ProductDescriptionServiceImpl implements ProductDescriptionService{

	private final ProductDescriptionRepository productDescriptionRepository;
	private final ProductRepository productRepository;

	@Override
	public ProductDescriptionDto create(ProductDescriptionDto b) {
		ProductDescription des = toProductDescription(b);
		ProductDescription res = productDescriptionRepository.save(des);
		return Mapper.toProductDescriptionDto(res);
	}

	@Override
	public ProductDescriptionDto update(ProductDescriptionDto b) {
		ProductDescription des = toProductDescription(b);
		ProductDescription res = productDescriptionRepository.save(des);
		return Mapper.toProductDescriptionDto(res);
	}

	@Override
	public ProductDescriptionDto getProductDescriptionByProductId(Integer id) {
		ProductDescription des = productDescriptionRepository.findProductDescriptionByProductId(id);
		if(des == null) {
			return null;
		}
		return Mapper.toProductDescriptionDto(des);
	}
	
	private ProductDescription toProductDescription(ProductDescriptionDto dto) {
		ProductDescription des = new ProductDescription();
		des.setProductDescriptionId(dto.getProductDescriptionId());
		des.setKeycap(dto.getKeycap());
		des.setSwitchKeyBoard(dto.getSwitchKeyBoard());
		des.setTypeKeyboard(dto.getTypeKeyboard());
		des.setConnect(dto.getConnect());
		des.setLed(dto.getLed());
		des.setFreigh(dto.getFreigh());
		des.setItemDimension(dto.getItemDimension());
		des.setCpu(dto.getCpu());
		des.setRam(dto.getRam());
		des.setOperatingSystem(dto.getOperatingSystem());
		des.setBattery(dto.getBattery());
		des.setHardDisk(dto.getHardDisk());
		des.setGraphicCard(dto.getGraphicCard());
		des.setKeyBoard(dto.getKeyBoard());
		des.setAudio(dto.getAudio());
		des.setWifi(dto.getWifi());
		des.setBluetooth(dto.getBluetooth());
		des.setColor(dto.getColor());
		des.setFrameRate(dto.getFrameRate());
		des.setScreenSize(dto.getScreenSize());
		des.setScreenType(dto.getScreenType());
		des.setProduct(productRepository.findByProductId(dto.getProductId()));
		return des;
	}
}
