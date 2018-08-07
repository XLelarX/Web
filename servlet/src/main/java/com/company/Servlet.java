package com.company;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;

public class Servlet extends HttpServlet {
    private static final String PATH = "page.jsp";
    private static final String BUTTON_TREE = "buttonTree";
    private static final String BUTTON_ONE = "buttonOne";
    private static final String BUTTON_TWO = "buttonTwo";
    private static final String EMPTY = "Пустая строка";
    private static final String ENTER = "enter";
    private static final String BUTTON = "button";
    private static final String ARRAY = "array";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ARRAY, TaskDAO.getList().toString().replace("[", "").replace("]", ""));

        req.getRequestDispatcher(PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String enter = req.getParameter(ENTER);
        String button = req.getParameter(BUTTON);

        try {
            if (BUTTON_TREE.equals(button))
                TaskDAO.removeAll();
            else if (enter.equals(""))
                System.out.println(EMPTY);
            else if (BUTTON_ONE.equals(button))
                TaskDAO.add(enter);
            else if (BUTTON_TWO.equals(button))
                TaskDAO.remove(enter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        doGet(req, resp);
    }
}
