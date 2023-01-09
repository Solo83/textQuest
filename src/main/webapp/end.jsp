<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="WEB-INF/parts/header.html" %>

<!DOCTYPE html>
<html>
<body>
<div class="container" style="margin-top: 8%;">
    <form class="form-horizontal" action=${pageContext.request.contextPath}/index.jsp method="post">
        <fieldset>

            <h3 class="h3">Игра окончена!</h3>
            <p>
                <c:out value="${sessionScope.get('currentQuestion')}"/>
            </p>

            <!-- Form Name -->
            <legend class="h2">UfoQuest (Текстовый квест)</legend>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="singlebutton"></label>
                <div class="col-md-4">
                    <button id="singlebutton" name="singlebutton" class="btn btn-primary">Начать новую игру!</button>
                </div>
            </div>
        </fieldset>


    </form>
</div>
</body>
</html>