<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="WEB-INF/parts/header.html" %>

<!DOCTYPE html>
<html>
<body>
<div class="container" style="margin-top: 8%;">
    <form class="form-horizontal" action=${pageContext.request.contextPath}/Game_Servlet method="post">
        <fieldset>

            <h3 class="h3">Вопрос</h3>
            <p>
                <c:out value="${sessionScope.get('currentQuestion')}"/>
            </p>

            <!-- Form Name -->
            <legend class="h2">UfoQuest (Текстовый квест)</legend>
            <!-- Multiple Radios -->
            <div class="form-group">
                <label class="col-md-4 control-label">Выберите Ваш ответ: </label>
                <div class="col-md-4">
                    <div class="radio">
                        <label for="radio">
                            <input type="radio" name="radio"  id="radio" value="Yes" checked>
                            <c:out value="${sessionScope.get('currentAnswerYes')}"/>
                        </label>
                    </div>
                    <div class="radio">
                        <label for="radio">
                            <input type="radio" name="radio"  id="radio" value="No" >
                            <c:out value="${sessionScope.get('currentAnswerNo')}"/>
                        </label>
                    </div>
                </div>
            </div>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="singlebutton"></label>
                <div class="col-md-4">
                    <button id="singlebutton" name="singlebutton" class="btn btn-primary">Ответить</button>
                </div>
            </div>
        </fieldset>
        <div>
            <h3 class="h3">Статистика: </h3>
            <p>Имя пользователя: <c:out value="${sessionScope.get('user_name')}"/></p>
            <p>IP address: <c:out value="${sessionScope.get('ip_address')}"/></p>
            <p>Session ID: <c:out value="${sessionScope.get('session_ID')}"/></p>
            <p>Количество сыгранных игр: <c:out value="${sessionScope.get('user_game_counter')}"/></p>
        </div>

    </form>
</div>
</body>
</html>
