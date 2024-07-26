package crudtest.springweeklyquiz.menu;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public ResponseEntity<MenuDto> createMenu(@RequestBody MenuDto menuDto) {
        MenuDto createdMenu = menuService.createMenu(menuDto);
        return new ResponseEntity<>(createdMenu, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDto> getMenuById(@PathVariable("id") Long id) {
        MenuDto menuById = menuService.getMenuById(id);
        return ResponseEntity.ok(menuById);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuDto>> getMenusByCategory(@PathVariable("category") String category) {
        List<MenuDto> menusByCategory = menuService.getMenusByCategory(category);
        return ResponseEntity.ok(menusByCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuDto> updateMenuById(@PathVariable("id") Long id, @RequestBody MenuDto menuDto) {
        MenuDto updatedMenu = menuService.updateMenuById(id, menuDto);
        return ResponseEntity.ok(updatedMenu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MenuDto> dtoResponseEntity(@PathVariable("id") Long id) {
        menuService.deleteMenuById(id);
        return ResponseEntity.noContent().build();
    }
}
