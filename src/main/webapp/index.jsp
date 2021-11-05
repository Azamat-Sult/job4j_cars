<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>

    <script type="text/javascript" src="js/getCarBrands.js"></script>

    <script type="text/javascript" src="js/getPosts.js"></script>

    <script type="text/javascript" src="js/changePostStatus.js"></script>

    <script>
        $(document).ready(
            getPosts(0),
            getCarBrands()
        );
    </script>

    <title>Купить продать автомобиль</title>

</head>
<body>
<div class="container">
    <div class="row justify-content-end">
        <ul class="nav">
            <c:if test="${user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Войти</a>
                </li>
            </c:if>
            <c:if test="${user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp"> <c:out value="${user.name}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/addPost.jsp">Добавить объявление</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/logout.do">Выйти</a>
                </li>
            </c:if>
        </ul>
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <div class="row">
                    <div class="col">
                        Каталог автомобилей для покупки
                    </div>
                    <div class="col">
                        Фильтр по марке авто
                        <select class="form-control" name="carBrand" id="carBrands" onchange="getPosts(this.value)">
                            <option value="0">Все</option>
                        </select>
                    </div>
                    <div class="col">
                        <input class="form-check-input" type="checkbox" id="showTodayPosts" onclick="getPosts(document.getElementById('carBrands').value)">
                        <label class="form-check-label" for="showTodayPosts">
                            Показать объявления только за сегодня
                        </label>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Фото</th>
                        <th scope="col">Марка</th>
                        <th scope="col">Модель</th>
                        <th scope="col">Кузов</th>
                        <th scope="col">Цвет</th>
                        <th scope="col">Пробег</th>
                        <th scope="col">Возраст</th>
                        <th scope="col">Описание</th>
                        <th scope="col">В продаже с</th>
                        <th scope="col">Телефон</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody id="posts">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>