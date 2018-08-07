<%--<jsp:useBean id="array" scope="re" type="com.company.Servlet"/>--%>
<html>
<head>
    <title>page</title>
    <style>
        <%@include file="/WEB-INF/Style.css" %>
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js">
    </script>
</head>
<body style="background: bisque">
<center>
    <form method="post" style="alignment: right">
        <input id="in" type="text" name="enter"/>
        <button class="buttons font" type="submit" name="button" value="buttonOne">add</button>
        <button class="buttons font" type="submit" name="button" value="buttonTwo">remove</button>
        <button class="buttons font" type="submit" name="button" value="buttonTree">clear list</button>
        <input class="buttons font" type="reset" value="clear field"/>
    </form>

    <div hidden="hidden" id="list">${array}</div>
    <div id="renderList" class="font"></div>

    <script type="text/javascript">
        <%@include file="/WEB-INF/Scripts.js"%>
    </script>
</center>
</body>
</html>
