package crudtest.springweeklyquiz.menu;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public MenuDto createMenu(MenuDto menuDto) {
        Menu menu = convertToEntity(menuDto);
        menu = menuRepository.save(menu);
        return convertToDto(menu);
    }

    public MenuDto updateMenuById(Long id, MenuDto menuDto) {
        Menu menu = menuRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 메뉴는 존재하지 않습니다"));
        menu.setName(menuDto.getName());
        menu.setCategory(menuDto.getCategory());
        menu.setPrice(menuDto.getPrice());
        menu.setDescription(menuDto.getDescription());

        Menu updatedMenu = menuRepository.save(menu);
        return convertToDto(updatedMenu);
    }

    public void deleteMenuById(Long id) {
        menuRepository.deleteById(id);
    }

    public MenuDto getMenuById(Long id) {
        Menu menu = menuRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 메뉴는 존재하지 않습니다"));
        return convertToDto(menu);
    }

    public List<MenuDto> getMenusByCategory(String category) {
        List<Menu> menus = menuRepository.findByCategory(category);
        return menus.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private MenuDto convertToDto(Menu menu) {
        MenuDto menuDto = new MenuDto();
        menuDto.setId(menu.getId());
        menuDto.setName(menu.getName());
        menuDto.setCategory(menu.getCategory());
        menuDto.setPrice(menu.getPrice());
        menuDto.setDescription(menu.getDescription());
        return menuDto;
    }

    private Menu convertToEntity(MenuDto menuDto) {
        Menu menu = new Menu();
        menu.setId(menuDto.getId());
        menu.setName(menuDto.getName());
        menu.setCategory(menuDto.getCategory());
        menu.setPrice(menuDto.getPrice());
        menu.setDescription(menuDto.getDescription());
        return menu;
    }
}
