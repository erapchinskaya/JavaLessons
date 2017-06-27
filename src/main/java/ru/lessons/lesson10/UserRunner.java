package ru.lessons.lesson10;

import java.util.*;

/**
 * Created by User on 08.06.2017.
 */
public class UserRunner {

    public static void main(String[] arg){

        User u1 = new User("1", "Sofia");
        User u2 = new User("1", "Sofia");
        User u3 = new User("1", "Sofia");

        List<User> users = new OverlapArrayList<>();
        users.add(u1);
        users.add(u1);
        users.add(u2);
        users.add(u1);
        users.add(u1);
        users.add(u2);
        users.add(u1);
        users.add(u1);
        users.add(u2);
        users.add(u1);
        users.add(u1);
        users.add(u2);
        users.add(u1);
        users.add(u1);
        users.add(u2);

        System.out.println("***List***");
        for (User u: users){
            System.out.println(u);
            System.out.println(u.hashCode());
        }


        System.out.println(users.contains(u3)?"y":"n");

        Iterator i = users.iterator();

        User pu = (User) i.next();
        while (i.hasNext())
        {
            User cu = (User) i.next();

            if(cu == pu){
                System.out.println(pu+"=="+cu);
            }
            else System.out.println(pu+"!=="+cu);

            if(cu.equals(pu)){
                System.out.println(pu+"equals"+cu);
            }
            else System.out.println(pu+"!equals"+cu);;


        }

//
//        Set<User> userss = new HashSet<>();
//        userss.add(new User("1", "Sofia"));
//        userss.add(new User("1", "Sofia"));
//
//        System.out.println();
//        System.out.println("***Set***");
//        for (User u: userss){
//            System.out.println(u);
//            System.out.println(u.hashCode());
//        }
    }
}
