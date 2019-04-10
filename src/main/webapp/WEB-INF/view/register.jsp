<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Zombie tracker - keep track on all your favorite zombie's</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.red-amber.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/site.css">
</head>
<body>

<div class="container">


    <div class="item">
        <img src="/resources/img/logo.png"/>
    </div>
</div>

<form action="/registersubmit" method="post">
    <div class="container">
        <div class="item mdl-textfield mdl-js-textfield">
            <input class="mdl-textfield__input" type="text" id="name" name="name">
            <label class="mdl-textfield__label" for="name">Description...</label>
        </div>
        <div class="container-column">
            <c:forEach var="type" items="${zombie.typeAsList}">
                <div class="item">
                    <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="option-${type}">
                        <input type="radio" id="option-${type}" class="mdl-radio__button" name="type" value="${type}">
                        <span class="mdl-radio__label">${type}</span>
                    </label>
                </div>
            </c:forEach>
        </div>
        <div class="container-column">
            <c:forEach var="gender" items="${zombie.genderAsList}">
                <div class="item">
                    <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="option-${gender}">
                        <input type="radio" id="option-${gender}" class="mdl-radio__button" name="gender"
                               value="${gender}">
                        <span class="mdl-radio__label">${gender}</span>
                    </label>
                </div>
            </c:forEach>
        </div>
        <div class="item">
            <button class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored">
                <input type="submit" name="submit" value="" name="submit"/>
                <i class="material-icons">add</i>
            </button>
        </div>
        <input type="hidden" id="newLat" name="latitude"/>
        <input type="hidden" id="newLng" name="longitude"/>
    </div>

</form>
<div class="container">
    <h3>Location of zombie</h3>
    <div id="map"></div>
</div>
<script>

    var markers = [];

    function initMap() {
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 4,
            center: {lat: 52.066, lng: 5.087}
        });

        map.addListener('click', function (e) {
            placeMarkerAndPanTo(e.latLng, map);
        });
    }

    function placeMarkerAndPanTo(latLng, map) {
        clearMarkers();
        markers.push(new google.maps.Marker({
            position: latLng,
            map: map
        }));
        document.getElementById('newLat').value = latLng.lat();
        document.getElementById('newLng').value = latLng.lng();
        map.panTo(latLng);
    }

    function clearMarkers() {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
        markers = [];
    }
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCuAItvpTBXE1rS0-fLcXEfw8gXYLGoWIw&callback=initMap">
</script>
</body>
</html>
