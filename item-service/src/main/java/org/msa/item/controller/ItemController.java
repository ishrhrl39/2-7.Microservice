package org.msa.item.controller;

import org.msa.item.dto.ItemDTO;
import org.msa.item.dto.ResponseDTO;
import org.msa.item.exception.ApiException;
import org.msa.item.service.ItemService;
import org.msa.item.valid.ItemTypeValid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="v1/item")
@Slf4j
@RequiredArgsConstructor
@Validated
public class ItemController {
	
	private final ItemService itemService;
	
	@RequestMapping(value="/add/{itemType}", method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO> add(@Valid @RequestBody ItemDTO itemDTO, @ItemTypeValid @PathVariable String itemType) throws Exception{
		ResponseDTO.ResponseDTOBuilder responseBuilder = ResponseDTO.builder();
		
		/*
		log.debug("path.variable itemType = {}", itemType);
		boolean hasItemType = false;
		ItemType [] itemTypeList = ItemType.values();
		for(ItemType i : itemTypeList) {
			hasItemType = i.hasItemCd(itemType);
			if(hasItemType) break;
		}
		
		if(!hasItemType) {
			responseBuilder.code("500").message("invalid itemType .[" + itemType + "]");
			return ResponseEntity.ok(responseBuilder.build());
		}else {
			itemDTO.setItemType(itemType);
		}
		
		
		try {
			Integer.parseInt("test");
		}catch(Exception e) {
			throw new ApiException("test에러");
		}
		*/
		
		itemDTO.setItemType(itemType);
		
		itemService.insertItem(itemDTO);
		log.debug("request add item id = {}", itemDTO.getId());
		
		responseBuilder.code("200").message("success");
		return ResponseEntity.ok(responseBuilder.build());
	}
}
