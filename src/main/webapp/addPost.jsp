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

    <script type="text/javascript" src="js/getCarModels.js"></script>

    <script type="text/javascript" src="js/getBodyTypes.js"></script>

    <script type="text/javascript" src="js/validateAddPost.js"></script>

    <script>
        $(document).ready(
            getCarBrands(),
            getBodyTypes()
        );
    </script>

    <title>Добавить объявление о продаже автомобиля</title>

</head>
<body>
<div class="container">
    <div class="row justify-content-end">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp"> <c:out value="${user.name}"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/logout.do">Выйти</a>
            </li>
        </ul>
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <ul class="nav nav-pills nav-fill">
                    <li class="nav-item">
                        Новое объявление о продаже
                    </li>
                </ul>
            </div>
            <div class="card-body">
                <form id="form" name="form" action="<%=request.getContextPath()%>/addPost.do" method="post" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="carBrands">Марка автомобиля</label>
                                <select class="form-control" name="carBrand" id="carBrands" onchange="getCarModels(this)">
                                </select>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="carModels">Модель автомобиля</label>
                                <select class="form-control" name="carModel" id="carModels">
                                </select>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="bodyType">Тип кузова</label>
                                <select class="form-control" name="bodyType" id="bodyType">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="bodyColor">Цвет кузова</label>
                                <input type="text" class="form-control" name="bodyColor" id="bodyColor">
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="mileAge">Пробег, км</label>
                                <input type="number" class="form-control" name="mileAge" id="mileAge">
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="ageYears">Возраст авто, лет</label>
                                <input type="number" class="form-control" name="ageYears" id="ageYears">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description">Описание</label>
                        <textarea class="form-control" name="description" id="description" rows="2"></textarea>
                    </div>
                    <div class="checkbox">
                        <label for="photo">Выберите фото автомобиля</label>
                        <input type="file" name="photo" id="photo" accept="image/png, image/gif, image/jpeg">
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="return validateAddPost()">Добавить</button>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>