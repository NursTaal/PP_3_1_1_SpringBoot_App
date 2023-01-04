package Spring1.Springboot2.controller;

import Spring1.Springboot2.model.User;
import Spring1.Springboot2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/people")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(ModelMap model) {
        //Получим всех людей и отправим на представление
        model.addAttribute("users", userService.getAllUsers());
        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, ModelMap model) {
        // Получим одного человека и отправим на представление
        model.addAttribute("user", userService.getUserById(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "/users/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        //Создадим нового юсера и сохраним в базе данный
        userService.addUser(user);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "/users/edit";
    }

    @PatchMapping("/{id}")
    public String update (@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUserById(id,user);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/people";
    }
}
/*
Перейдем к созданию рабочего web-приложения. Все ключевые моменты были рассмотрены в предыдущих задачах.
Теперь вам требуется их сопоставить и связать в один проект.
Используя наработки по mvc и hibernate соберите CRUD-приложение.

Задание:
1. Написать CRUD-приложение. Использовать Spring MVC + Hibernate.
2. Должен быть класс User с произвольными полями (id, name и т.п.).
3. В приложении должна быть страница, на которую выводятся все юзеры с возможностью добавлять,
удалять и изменять юзера.
4. Конфигурация Spring через JavaConfig и аннотации, по аналогии с предыдущими проектами. SUCCESS
Без использования xml. Без Spring Boot. SUCCESS
5. Внесите изменения в конфигурацию для работы с базой данных. SUCCESS
Вместо SessionFactory должен использоваться EntityManager. SUCCESS
 */
