package com.revature.consoleUI.userUI;

import com.revature.consoleUI.View;
import com.revature.pojos.User;
import com.revature.services.ConsoleService;
import com.revature.services.UserService;

import java.util.Scanner;

public class newUser extends View {
    private UserService service;
    public newUser() {
        viewName = "NewUser";
        consoleService = ConsoleService.getConsoleService();
        service = new UserService();
    }

    @Override
    public void renderView() {
        Scanner sc = new Scanner(System.in);

        System.out.println("========== NEW USER ==========");
        System.out.println("Enter first name: ");
        String firstName = sc.nextLine();
        System.out.println("Enter last name: ");
        String lastName = sc.nextLine();
        System.out.println("Enter email ");
        String email = sc.nextLine();
        System.out.println("Enter password: ");
        String userPass = sc.nextLine();

        User newUser = new User(firstName, lastName, email, userPass);
        service.saveUser(newUser);

        consoleService.navigate("MainMenu");


    }
}