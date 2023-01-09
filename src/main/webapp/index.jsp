<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="WEB-INF/parts/header.html" %>

<!DOCTYPE html>
<html>
<body>
<div class="container" style="margin-top: 8%;">
    <form class="form-horizontal" action=${pageContext.request.contextPath}/welcome_servlet method="post">
        <fieldset>
            <h3 class="h3">ПРОЛОГ</h3>
            <p>Ты стоишь в космическом порту и готов подняться на борт своего корабля. Разве ты не об этом мечтал? Стать
                капитаном галактического судна с экипажем, который будет совершать подвиги под твоим командованием.
                Так что вперед!
            </p>

            <h3 class="h3">ЗНАКОМСТВО С ЭКИПАЖЕМ</h3>
            <p>Когда ты поднялся на борт корабля, тебя поприветствовала девушка c черной папкой в руках: - Здравствуйте,
                командир! Я Зинаида - ваша помощница. Видите? Там в углу пьет кофе наш штурман – сержант Перегарный
                Шлейф, под штурвалом спит наш бортмеханик – Черный Богдан, а фотографирует его Сергей Стальная Пятка -
                наш навигатор.
                А как обращаться к Вам?
            </p>

            <!-- Form Name -->
            <legend class="h2">UfoQuest (Текстовый квест)</legend>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="user_name">Имя Пользователя</label>
                <div class="col-md-5">
                    <input id="user_name" name="user_name" type="text" placeholder="Введите имя"
                           class="form-control input-md" required="">
                </div>
            </div>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="singlebutton"></label>
                <div class="col-md-4">
                    <button id="singlebutton" name="singlebutton" class="btn btn-primary">Представиться!</button>
                </div>
            </div>

        </fieldset>
    </form>
</div>
</body>

</html>