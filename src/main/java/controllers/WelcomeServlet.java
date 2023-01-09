package controllers;

import entities.Question;
import entities.User;
import model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "welcomeServlet", value = "/welcome_servlet")
public class WelcomeServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();

        String name = req.getParameter("user_name");
        Model model = Model.getInstance();

        User user = new User(name);
        model.add(user);
        user = model.getUser(name);

        List<Question> questions = model.getGameQuestion();
        Question question = questions.get(0);

        // Установка начальных атрибутов

        session.setAttribute("currentQuestion",question.getText());
        session.setAttribute("currentAnswerYes",question.getAnswers().get(0).getText());
        session.setAttribute("currentAnswerNo",question.getAnswers().get(1).getText());
        session.setAttribute("questionNumber",0);
        session.setAttribute("user_name",user.getName());
        session.setAttribute("ip_address",req.getRemoteAddr());
        session.setAttribute("session_ID",session.getId());
        session.setAttribute("user_game_counter",user.getGameCounter());


        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/game.jsp");
        dispatcher.forward(req, resp);


    }

    public void destroy() {
    }



}