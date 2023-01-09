import controllers.GameServlet;
import entities.Game;
import entities.Question;
import entities.User;
import model.Model;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class GameServletTest extends Mockito {

    private static MockedStatic<Model> model;

    @BeforeAll
    public static void init() {
        model = mockStatic(Model.class);
    }

    @AfterAll
    public static void close() {
        model.close();
    }


    @ParameterizedTest(name = "setQuestionAttributesMethodTest with value {0}")
    @ValueSource(ints = {0, 2, 4})
    void setQuestionAttributes(int number) {
        GameServlet servlet = new GameServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        Mockito.when(Model.getInstance()).thenReturn(model);
        Mockito.when(model.getGameQuestion()).thenReturn(new Game().getQuestions());

        Mockito.when(request.getSession()).thenReturn(session);

        request.getSession();
        verify(request).getSession();

        List<Question> questions = new Game().getQuestions();
        Question question = questions.get(number);


        servlet.setQuestionAttributes(request, number);

        verify(session).setAttribute("currentQuestion", question.getText());
        verify(session).setAttribute("currentAnswerYes", question.getAnswers().get(0).getText());
        verify(session).setAttribute("currentAnswerNo", question.getAnswers().get(1).getText());

    }

    @ParameterizedTest(name = "endOfGameMethodTest with value {0}")
    @ValueSource(ints = {1, 3, 5, 6})
     public void endOfGame(int number) throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ServletConfig sg = mock(ServletConfig.class);

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        ServletContext context = mock(ServletContext.class);
        User user = mock(User.class);
        Model model = mock(Model.class);

        Mockito.when(Model.getInstance()).thenReturn(model);
        Mockito.when(model.getUser(any())).thenReturn(user);
        Mockito.when(model.getGameQuestion()).thenReturn(new Game().getQuestions());

        Mockito.when(request.getSession()).thenReturn(session);
        request.getSession();
        verify(request).getSession();

        List<Question> questions = new Game().getQuestions();
        Question question = questions.get(number);

        GameServlet servlet = new GameServlet();
        servlet.init(sg);

        when(servlet.getServletContext()).thenReturn(context);
        when(servlet.getServletContext().getRequestDispatcher(any())).thenReturn(requestDispatcher);

        servlet.endOfGame(request, response, number);

        verify(session).setAttribute("currentQuestion", question.getText());
        verify(servlet.getServletContext().getRequestDispatcher("/end.jsp")).forward(request, response);

    }

    @Test
     public void gameLogic() throws ServletException, IOException {


        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        User user = mock(User.class);
        ServletConfig sg = mock(ServletConfig.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);

        Mockito.when(Model.getInstance()).thenReturn(model);
        Mockito.when(model.getGameQuestion()).thenReturn(new Game().getQuestions());
        Mockito.when(model.getUser(any())).thenReturn(user);

        GameServlet servlet = Mockito.spy(new GameServlet());
        servlet.init(sg);

        when(servlet.getServletContext()).thenReturn(context);
        when(servlet.getServletContext().getRequestDispatcher(any())).thenReturn(requestDispatcher);

        when(request.getSession()).thenReturn(session);

        servlet.gameLogic(request,response,0,"No");
        Mockito.verify(servlet, times(1)).endOfGame(request, response, 1);

        servlet.gameLogic(request,response,0,"Yes");
        Mockito.verify(session,times(1)).setAttribute("questionNumber", 2);
        Mockito.verify(servlet, times(1)).setQuestionAttributes(request, 2);

        servlet.gameLogic(request,response,2,"No");
        Mockito.verify(servlet, times(1)).endOfGame(request, response, 3);

        servlet.gameLogic(request,response,2,"Yes");
        Mockito.verify(session,times(1)).setAttribute("questionNumber", 4);
        Mockito.verify(servlet, times(1)).setQuestionAttributes(request, 4);

        servlet.gameLogic(request,response,4,"No");
        Mockito.verify(servlet, times(1)).endOfGame(request, response, 5);

        servlet.gameLogic(request,response,4,"Yes");
        Mockito.verify(servlet, times(1)).endOfGame(request, response, 6);

    }


}


