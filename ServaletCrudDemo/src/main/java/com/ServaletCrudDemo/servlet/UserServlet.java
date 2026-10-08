package com.ServaletCrudDemo.servlet;

import com.ServaletCrudDemo.Service.UserService;
import com.ServaletCrudDemo.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
     Integer id = Integer.parseInt(request.getParameter("id"));
     String name = request.getParameter("name");
     String email = request.getParameter("email");
     String mobile = request.getParameter("mobile");

     if (id==null || email==null|| name==null|| mobile==null){

     }
     User user = new User(id,name,email,mobile);

     User createdUser = userService.createUser(user);

    }
    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){


    }
    @Override
    public void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){


    }
    @Override
    public void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){


    }
}
