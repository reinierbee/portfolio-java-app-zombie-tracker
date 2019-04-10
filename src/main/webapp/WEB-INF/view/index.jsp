<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Zombie tracker - keep track on all your favorite zombie's</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.red-amber.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/site.css">
</head>
<body>

<form action="#">
    <button id="dead-pix" formaction="/d"></button>
</form>

<div class="container">


    <div class="item">
        <img src="/resources/img/logo.png"/>
    </div>
</div>
<form action="#">
    <div class="container-row">
        <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" formaction="/register">
            I spotted a Zombie
        </button>
        <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" formaction="/sighting">
            Show me the Zombies!
        </button>
        <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" formaction="/d">
            I'm feeling lucky...
        </button>
    </div>
</form>
<div class="container footer">
    <a href="swagger-ui.html">Swekker, kookwekker</a>
</div>

</body>
</html>
