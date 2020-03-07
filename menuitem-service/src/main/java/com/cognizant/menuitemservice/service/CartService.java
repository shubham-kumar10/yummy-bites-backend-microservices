package com.cognizant.menuitemservice.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.menuitemservice.dto.CartDTO;
import com.cognizant.menuitemservice.exception.CartEmptyException;
import com.cognizant.menuitemservice.model.MenuItem;
import com.cognizant.menuitemservice.model.USER;
import com.cognizant.menuitemservice.repository.MenuItemRepository;
import com.cognizant.menuitemservice.repository.UserRepository;

@Service
public class CartService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	MenuItemRepository menuItemRepository;

	@Transactional
	public boolean addCartItem(String username, long menuItemId) {

		USER user = userRepository.findByUsername(username);
		MenuItem menuItem = menuItemRepository.getOne(menuItemId);
		user.getMenuItems().add(menuItem);
		userRepository.save(user);
		return true;
	}

	@Transactional
	public CartDTO getAllCartItems(String username) throws CartEmptyException {
		CartDTO cartDTO;
		List<MenuItem> menuItemList = (ArrayList<MenuItem>) userRepository.getMenuItems(username);

		if (menuItemList == null || menuItemList.size() == 0) {
			return new CartDTO(new ArrayList(), 0);
		} else {
			cartDTO = new CartDTO();
			cartDTO.setCartItems(menuItemList);
			cartDTO.setTotal(userRepository.getCartTotal(username));
		}
		return cartDTO;
	}

	@Transactional
	public void deleteCartItem(String username, Long menuItemId) {
		List<MenuItem> menuItemList = userRepository.getMenuItems(username);
		USER user = userRepository.findByUsername(username);
		MenuItem menuItem = menuItemRepository.getOne(menuItemId);
		menuItemList.remove(menuItem);
		user.setMenuItems(menuItemList);
		userRepository.save(user);
	}

}
