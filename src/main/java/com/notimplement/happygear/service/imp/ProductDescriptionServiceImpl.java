package com.notimplement.happygear.service.imp;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.notimplement.happygear.entities.ProductDescription;
import com.notimplement.happygear.model.dto.ProductDescriptionDto;
import com.notimplement.happygear.model.mapper.ProductDescriptionMapper;
import com.notimplement.happygear.repositories.ProductDescriptionRepository;
import com.notimplement.happygear.service.ProductDescriptionService;


@Service
@Transactional
@RequiredArgsConstructor
public class ProductDescriptionServiceImpl implements ProductDescriptionService{

	private final ProductDescriptionRepository productDescriptionRepository;
	private final ModelMapper mapper;
	
	@Override
	public List<ProductDescriptionDto> listAll() {
		return productDescriptionRepository.findAll()
				.stream().map(v -> mapper.map(v, ProductDescriptionDto.class)).toList();
	}

	@Override
	public ProductDescriptionDto getById(Integer id) {
		return ProductDescriptionMapper.toProductDescriptionDto(productDescriptionRepository.findById(id).get());
	}

	@Override
	public ProductDescriptionDto create(ProductDescriptionDto b) {
		ProductDescription des = toProductDescription(b);
		return ProductDescriptionMapper.toProductDescriptionDto(productDescriptionRepository.save(des));
	}

	@Override
	public ProductDescriptionDto update(ProductDescriptionDto b) {
		ProductDescription des = toProductDescription(b);
		return ProductDescriptionMapper.toProductDescriptionDto(productDescriptionRepository.save(des));
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
		return des;
	}
}
