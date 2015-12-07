<%-- 
    Document   : rezerwacja
    Created on : 2015-11-30, 00:23:47
    Author     : Mateusz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Rezerwacja</title>
        <!-- Bootstrap -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

        <link rel="stylesheet" type="text/css" href="css/css.css">
        <!-- JQuerry -->
        <script type="text/javascript" charset="utf-8" src="//cdn.datatables.net/1.10.10/js/jquery.dataTables.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

        <script>
            $(document).on("click", "#rez", function () {
                var action = 'select';
                var dataP = $('#dataP').val();
                var dataW = $('#dataW').val();
                $.get("rezerwacja", {
                    action: action,
                    dataP: dataP,
                    dataW: dataW
                }, function (responseText) {
                    $("#tabela").html(responseText);
                });
            });
            //lista dokonanych rezerwacji
            $(document).on("click", "#lRez", function () {
                //$(document).ready(function () {
                var action = 'obecne';
                $.get("rezerwacja", {
                    action: action
                }, function (responseText) {
                    $("#obecne").html(responseText);
                });
            });
            $(document).on("click", "#2Rez", function () {
                //$(document).ready(function () {
                var action = 'zakonczone';
                $.get("rezerwacja", {
                    action: action
                }, function (responseText) {
                    $("#zakonczone").html(responseText);
                });
            });
            function tab2() {
                $(document).ready(function () {
                    var action = 'obecne';
                    $.get("rezerwacja", {
                        action: action
                    }, function (responseText) {
                        $("#obecne").html(responseText);
                    });
                });
            }

            function tab() {
                $(document).ready(function () {

                    var action = 'select';
                    var dataP = $('#dataP').val();
                    var dataW = $('#dataW').val();
                    $.get("rezerwacja", {
                        action: action,
                        dataP: dataP,
                        dataW: dataW
                    }, function (responseText) {
                        $("#tabela").html(responseText);
                    });
                });
            }
            $(document).on("click", "#pRezerwuj", function () {
                var action = 'insert';
                var dataP = $('#dataP').val();
                var dataW = $('#dataW').val();
                var $pokoj = $(this).parent().parent();
                var nazwaO = $pokoj.find(".nazwa").text();
                var numerP = $pokoj.find(".numer").text();
                $.get("rezerwacja", {
                    action: action,
                    dataP: dataP,
                    dataW: dataW,
                    nazwaO: nazwaO,
                    numerP: numerP
                });
                tab();
            });
            $(document).on("click", "#pUsun", function () {
                var action = 'delete';
                var $pokoj = $(this).parent().parent();
                var nazwaO = $pokoj.find(".nazwa").text();
                var numerP = $pokoj.find(".numer").text();
                var dataP = $pokoj.find(".dPrzyjazdu").text();
                var dataW = $pokoj.find(".dWyjazdu").text();
                $.get("rezerwacja", {
                    action: action,
                    dataP: dataP,
                    dataW: dataW,
                    nazwaO: nazwaO,
                    numerP: numerP
                });
                tab2();
            });
            $(document).on("click", "#pPotwierdz", function () {
                var action = 'update';
                var $pokoj = $(this).parent().parent();
                var nazwaO = $pokoj.find(".nazwa").text();
                var numerP = $pokoj.find(".numer").text();
                var dataP = $pokoj.find(".dPrzyjazdu").text();
                var dataW = $pokoj.find(".dWyjazdu").text();
                $.get("rezerwacja", {
                    action: action,
                    dataP: dataP,
                    dataW: dataW,
                    nazwaO: nazwaO,
                    numerP: numerP
                });
                tab2();
            });
        </script>
    </head>
    <body>
        <div class="container">
            <%@include file="menu.jsp" %>
            <%
                if (userName == null) {
                    response.sendRedirect("index.jsp");
                }
            %>
            <div id="dialog-message" title="Rezerwacja">
                <p>
                    Czy na pewno chcesz dokonać rezerwacji?
                </p>
            </div>

            <div class="container-fluid">
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#nowa">Nowa</a></li>
                    <li><a id="lRez" data-toggle = "tab" href="#obecne">Obecne</a></li>
                    <li><a id="2Rez" data-toggle = "tab" href="#zakonczone">Zakończone</a></li>
                </ul>
                <div class ="tab-content">
                    <div id="nowa" class = "tab-pane fade in active">
                        <table class='table'>
                            <tr>
                                <td>Data Przyjazdu: <input type="date" id="dataP" /></td>
                                <td>Data Wyjazdu: <input type="date" id="dataW"/></td>
                                <td><input type = submit id="rez"/><td>
                            </tr>
                        </table>
                        <table id ="tabela" class = "table">
                            <thead>
                                <tr>
                                    <th>Grafika</th>
                                    <th>Nazwa Obiektu</th>
                                    <th>Numer uokoju</th>
                                    <th>Liczba osób</th>
                                    <th>Cena za dzień (zł)</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                    <div id ="obecne" class = "tab-pane fade">
                        <table id ="tabela" class = "table">
                            <thead>
                                <tr>
                                    <th>Grafika</th>
                                    <th>Nazwa Obiektu</th>
                                    <th>Numer uokoju</th>
                                    <th>Liczba osób</th>
                                    <th>Cena za dzień (zł)</th>
                                    <th>Data Przyjazdu</th>
                                    <th>Data Wyjazdu</th>
                                    <th>Potwierdzenie</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                    <div id ="zakonczone" class = "tab-pane fade">
                        <table id ="tabela" class = "table">
                            <thead>
                                <tr>
                                    <th>Grafika</th>
                                    <th>Nazwa Obiektu</th>
                                    <th>Numer uokoju</th>
                                    <th>Liczba osób</th>
                                    <th>Cena za dzień (zł)</th>
                                    <th>Data Przyjazdu</th>
                                    <th>Data Wyjazdu</th>
                                    <th>Potwierdzenie</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class = "section">
                Noclegi u Mateusza
            </div>
        </div>
    </body>
</html>

