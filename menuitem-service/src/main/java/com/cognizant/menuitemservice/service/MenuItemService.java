package com.cognizant.menuitemservice.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.menuitemservice.model.MenuItem;
import com.cognizant.menuitemservice.repository.MenuItemRepository;

@Service
public class MenuItemService {

	@Autowired
	MenuItemRepository menuItemRepository;

	@Transactional
	public List<MenuItem> getMenuItemListCustomer() {
		return menuItemRepository.menuItemListCustomer();
	}

	@Transactional
	public List<MenuItem> getMenuItemListAdmin() {
		return menuItemRepository.findAll();
	}

	@Transactional
	public MenuItem getMenuItem(Long id) {
		return menuItemRepository.findById(id).get();
	}

	@Transactional
	public boolean modifyMenuItem(MenuItem menuItem) {
		Optional<MenuItem> opMenuItem = menuItemRepository.findById(menuItem.getId());
		if (opMenuItem.isPresent()) {
			menuItemRepository.save(menuItem);
			return true;
		} else
			return false;

	}

}
