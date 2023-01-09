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

@WebServlet(name = "GameServlet", value = "/Game_Servlet")
public class GameServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();

        int questionNumber = (int) session.getAttribute("questionNumber");
        String currentAnswer = req.getParameter("radio");

        gameLogic(req,resp,questionNumber,currentAnswer);

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/game.jsp");
        dispatcher.forward(req, resp);
    }

    public void setQuestionAttributes(HttpServletRequest req, int questionNumber) { //Установка атрибутов сессии для game.jsp
        List<Question> questions = Model.getInstance().getGameQuestion();
        HttpSession session = req.getSession();
        Question question = questions.get(questionNumber);
        if (!question.getAnswers().isEmpty()) {
        session.setAttribute("currentQuestion", question.getText());
        session.setAttribute("currentAnswerYes", question.getAnswers().get(0).getText());
        session.setAttribute("currentAnswerNo", question.getAnswers().get(1).getText());}
    }

    public void endOfGame(HttpServletRequest req, HttpServletResponse resp, int number) throws ServletException, IOException { //Установка атрибутов сессии для end.jsp. Конец игры.
        List<Question> questions = Model.getInstance().getGameQuestion();
        HttpSession session = req.getSession();
        Model model = Model.getInstance();
        User user = model.getUser((String) session.getAttribute("user_name"));
        user.increaseGameCounter();

        Question question = questions.get(number);
        session.setAttribute("currentQuestion", question.getText());

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/end.jsp");
        dispatcher.forward(req, resp);
    }

    public void gameLogic (HttpServletRequest req, HttpServletResponse resp, int questionNumber, String currentAnswer) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (questionNumber == 0 && currentAnswer.equals("No")) {
            endOfGame(req, resp, 1);
        }

        if (questionNumber == 0 && currentAnswer.equals("Yes")) {
            session.setAttribute("questionNumber", 2);
            setQuestionAttributes(req, 2);
        }

        if (questionNumber == 2 && currentAnswer.equals("No")) {
            endOfGame(req, resp, 3);
        }

        if (questionNumber == 2 && currentAnswer.equals("Yes")) {
            session.setAttribute("questionNumber", 4);
            setQuestionAttributes(req, 4);
        }

        if (questionNumber == 4 && currentAnswer.equals("Yes")) {
            endOfGame(req, resp, 6);
        }

        if (questionNumber == 4 && currentAnswer.equals("No")) {
            endOfGame(req, resp, 5);
        }

    }


}
