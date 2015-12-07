<%-- 
    Document   : oferta
    Created on : 2015-11-30, 21:32:38
    Author     : Mateusz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <!-- JQuerry -->
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

    <link rel="stylesheet" type="text/css" href="css/css.css">
    <script>
        $(document).ready(function () {
            var action = 'oferta';
            $.get("oferta", {action: action}, function (responseText) {
                $("#tabela").html(responseText);
            });
        });
        $(document).on("click", "#pGaleria", function () {
            var action = 'galeria';
            var $pokoj = $(this).parent().parent();
            var nazwaO = $pokoj.find(".nazwa").text();
            var numerP = $pokoj.find(".numer").text();
            $.get("oferta", {
                action: action,
                nazwaO: nazwaO,
                numerP: numerP
            }, function (responseText) {
                $("#gal").html(responseText);
            });
        });
    </script>
</head>
<body>
    <div class = "container">

        <div> <%@include file ="menu.jsp" %> </div>

        <div>
            <div>
                <h1>Oferta</h1>
            </div>
            <div id = "tabela" class ="table-responsive" ></div>

            <div class="modal fade" id="Galeria" role="dialog">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-body" id="gal"></div>
                        <div class="modal-footer">
                            <button type="button" class="close" data-dismiss="modal">Zamknij</button>
                        </div>
                    </div>
                </div>
            </div>

            <div id="footer">
                Noclegi u Mateusza
            </div>
        </div>
</body>
</html>
