<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Show all sighting</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.red-amber.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/site.css">
    <script>
        var map;
        var markers = [];

        var features = [];

        var iconBase = '/resources/img/';
        var icons = {
            MALE: {
                icon: iconBase + 'zombie-male-small.png'
            },
            FEMALE: {
                icon: iconBase + 'zombie-female-small.png'
            },
            TRANSGENDER: {
                icon: iconBase + 'zombie-transgender-small.png'
            },
            DRONE: {
                icon: iconBase + 'drone.png'
            }
        };

        function initMap() {
            map = new google.maps.Map(document.getElementById('map'), {
                zoom: 4,
                center: {lat: 52.066, lng: 5.087},
                mapTypeId: 'roadmap'
            });

            <c:forEach var="sightings" items="${sightings}">
            features[${sightings.zombie.id}] = {
                position: new google.maps.LatLng(${sightings.lat}, ${sightings.lng}),
                lat: ${sightings.lat},
                lng: ${sightings.lng},
                type: 'zombie',
                id: '${sightings.zombie.id}',
                desc: '${sightings.description}',
                zombieType: '${sightings.zombie.type}',
                zombieGender: '${sightings.zombie.gender}',
            };
            </c:forEach>

            loadMarkers();
        }

        function loadMarkers() {

            var infowindow = new google.maps.InfoWindow();

            // Create markers.
            features.forEach(function (feature) {
                var mak = new google.maps.Marker({
                    position: feature.position,
                    icon: icons[feature.zombieGender].icon,
                    map: map,
                    title: feature.id
                });
                mak.addListener('click', function () {
                    infowindow.setContent(contentString(mak.title));
                    infowindow.open(map, mak);
                });

                markers[feature.id] = mak;
            });

        }


        function killZombie(id, position, delay) {
            sendDrone(position, delay);
            markers[id].setMap(null);
            deleteZombie(id);
        }

        function deleteZombie(id) {
            var xmlhttp = new XMLHttpRequest();

            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == XMLHttpRequest.DONE) {
                    if (xmlhttp.status == 200) {
                        document.getElementById("myDiv").innerHTML = xmlhttp.responseText;
                    }
                    else if (xmlhttp.status == 400) {
                        alert('There was an error 400');
                    }
                    else {
                        alert('something else other than 200 was returned');
                    }
                }
            };

            xmlhttp.open("DELETE", "/api/zombieservice/"+id, true);
            xmlhttp.send();
        }

        function sendDrone(position, timeout) {
            window.setTimeout(function () {
                markers.push(new google.maps.Marker({
                    position: position,
                    map: map,
                    animation: google.maps.Animation.DROP,
                    icon: icons.DRONE.icon,
                }));
            }, timeout);
        }

        function contentString(id) {
            return '<div id="content" id="info-' + features[id].id + '">' +
                '<div id="siteNotice">' +
                '</div>' +
                '<h1 id="firstHeading" class="firstHeading">' + features[id].id + '</h1>' +
                '<div id="bodyContent">' +
                '<p>' +
                '<b>Gender:</b>' + features[id].zombieGender + '<br />' +
                '<b>Type:</b>' + features[id].zombieType + '<br />' +
                '<b>Description:</b>' + features[id].desc + '<br />' +
                '<button name="kill" onclick="killZombie(' + features[id].id + ',new google.maps.LatLng(' + features[id].lat + ',' + features[id].lng + '),500);">Kill</button>' +
                '</p>' +
                '</div>' +
                '</div>';
        }
    </script>
</head>
<body>
<div class="container">
    <div class="item">
        <img src="/resources/img/logo.png"/>
    </div>
</div>

<div id="map-wrapper">
    <div id="myDiv"></div>
    <div id="map"></div>
</div>

<script>
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCuAItvpTBXE1rS0-fLcXEfw8gXYLGoWIw&callback=initMap">
</script>

</body>
</html>
