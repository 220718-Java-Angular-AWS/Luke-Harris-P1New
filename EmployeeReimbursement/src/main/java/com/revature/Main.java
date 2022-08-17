package com.revature;



import com.revature.services.ConnectionManager;
import com.revature.daos.UserDAO;
import com.revature.pojos.User;


public class Main {
    public static void main(String[] args) {
        User luke = new User("Luke", "Harris", "password1", "luke@rev.com" );
        UserDAO something = new UserDAO();
        something.create(luke);
        System.out.println("Done!");





    }
}