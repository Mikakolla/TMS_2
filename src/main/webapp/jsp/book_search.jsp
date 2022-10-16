<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Поиск</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="d-flex justify-content-end" style="margin-right: 15px;">
    <a class="btn btn-info" style="margin-right: 10px" href="/book/book?lang=ru">Русский</a>
    <a class="btn btn-info" style="margin-right: 10px" href="/book/book?lang=en">English</a>
</div>
<div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 90vh;">
    <nav>
        <div class="nav nav-tabs" id="nav-tab" role="tablist">
            <button class="nav-link" id="nav-search-tab" data-bs-toggle="tab" data-bs-target="#nav-search" type="button" role="tab" aria-controls="nav-home" aria-selected="true"><s:message code="label.buttonSearch"/></button>
            <button class="nav-link active" id="nav-add-book-tab" data-bs-toggle="tab" data-bs-target="#nav-add-book" type="button" role="tab" aria-controls="nav-profile" aria-selected="false"><s:message code="label.addingBook"/></button>
        </div>
    </nav>
    <div class="tab-content" id="nav-tabContent">
        <div class="tab-pane fade" id="nav-search" role="tabpanel" aria-labelledby="nav-search-tab">
            <form class="form-control d-flex flex-column" style="width: 250px" action="/book/book/search">
                <input class="form-control" style="margin-bottom: 5px;" type="text" name="str" required>
                <button class="btn btn-primary" type="submit"><s:message code="label.buttonSearch"/></button>
            </form>
        </div>
        <div class="tab-pane fade show active" id="nav-add-book" role="tabpanel" aria-labelledby="nav-add-book-tab">
            <sf:form modelAttribute="book" class="form-control d-flex flex-column" style="width: 250px" method="post" action="/book/book/add">
                <label for="name"><s:message code="label.nameBook"/></label>
                <sf:input path="name" name="name" class="form-control" style="margin-bottom: 5px;" type="text"/>
                <sf:errors path="name"></sf:errors>
                <label for="name"><s:message code="label.authorBook"/></label>
                <sf:input path="author" name="author" class="form-control" style="margin-bottom: 5px;"/>
                <sf:errors path="author"></sf:errors>
                ${error}
                <button class="btn btn-primary" type="submit"><s:message code="label.addingBook"/></button>
            </sf:form>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
