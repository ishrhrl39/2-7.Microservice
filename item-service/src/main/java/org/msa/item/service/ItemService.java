package org.msa.item.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.msa.item.domain.Item;
import org.msa.item.dto.ItemDTO;
import org.msa.item.repository.ItemRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
	private final ItemRepository itemRepository;
	
	public void insertItem(ItemDTO itemDTO) {
		SimpleDateFormat form = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = form.format(new Date());
	
		Item item = Item.builder()
				.id(itemDTO.getId())
				.name(itemDTO.getName())
				.description(itemDTO.getDescription())
				.itemType(itemDTO.getItemType())
				.count(itemDTO.getCount())
				.regDts(date)
				.updDts(date)
				.build();
		itemRepository.save(item);
	}

}
