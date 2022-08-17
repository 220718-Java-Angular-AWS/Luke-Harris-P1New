package com.revature.consoleUI.userUI;
import com.revature.services.UserService;
import java.util.Scanner;

public class  deleteUser {
    public void deleteUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("========== Delete Target User ==========");
        System.out.println("Enter User ID to Delete: ");
        Integer id = Integer.parseInt(sc.nextLine());
        UserService delete = new UserService();
        delete.deleteUser(id);

        System.out.println("Target has been Deleted.");
    }

}
