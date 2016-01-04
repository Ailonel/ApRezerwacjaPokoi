<%-- 
    Document   : menu
    Created on : 2015-11-30, 00:21:57
    Author     : Mateusz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    $(document).on("click", "#rejestracja", function ()
    {
        $("#Rejestracja").modal();
    });

    $(document).on("click", "#logowanie1", function ()
    {
        $("#Logowanie").modal();
    });

    $(document).on("click", "#logowanie2", function ()
    {
        $("#Wylogowanie").modal();
    });
    
    $(document).on("click", "#logowanieSubmit", function () {
        var action = 'logowanie';
        var email = $('#emailL').val();
        var haslo = $('#hasloL').val();
        $.get("logowanie", {
            action: action,
            email: email,
            haslo: haslo
        }, function (responseText) {
            var logowanie = $.trim(responseText);
            if (logowanie != '0')
                $("#komunikatL").html(responseText);
            else 
                location.reload();
        });
        event.preventDefault();
    });
    
    $(document).on("click", "#rejestracjaSubmit", function () {
        var email = $('#emailR').val();
        var haslo = $('#hasloR').val();
        var imie = $('#imieR').val();
        var nazwisko = $('#nazwiskoR').val();
        var nrTel =$('#nrTelR').val();
        
        $.get("rejestracja", {

            email: email,
            haslo: haslo,
            imie: imie,
            nazwisko: nazwisko,
            nrTel: nrTel
            
        }, function (responseText) {
            var rejestracja = $.trim(responseText);
            if (rejestracja != '0')
                $("#komunikatR").html(responseText);
            else 
                location.reload();
        });
        event.preventDefault();
    });


</script>
<%
    String userName = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                userName = cookie.getValue();
            }
        }
    }
%>
<div id="menu">
    <nav class="navbar navbar-inverse">
        <a class="navbar-brand">Noclegi u Mateusza</a>
        <ul class = "nav navbar-nav">
            <li><a href = "index.jsp">Strona Główna</a></li>
            <li><a href = "kontakt.jsp">Kontakt</a></li>
            <li><a href = "oferta.jsp">Oferta<br/></a></li>
                    <%if (userName != null) {%>
            <li><a href = "rezerwacja.jsp">Rezerwacja</a></li>
                <% } %>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <%if (userName == null) {%>
            <li><a id="rejestracja" data-toggle= "modal" data-target="#Rejestracja"><span class="glyphicon glyphicon-user"></span> Rejestracja</a></li>
            <li><a id="logowanie1" data-toggle= "modal" data-target="#Logowanie"><span class="glyphicon glyphicon-log-in data-"></span> Logowanie </a></li>
                <% } else {%>
            <li><a><span class="glyphicon glyphicon-user"></span> <%= userName%> </a></li>
            <li><a id="logowanie2" data-toggle= "modal" data-target="#Wylogowanie"><span class="glyphicon glyphicon-log-in"></span> Wyloguj </a></li>
                <% }%>
        </ul>
    </nav>
    <div id="Rejestracja" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3> Rejestracja </h3>
                </div>
                <div class="modal-body">
                    <form id = "rejestracjaForm" role="form" method="post" action="rejestracja">
                        <div class="form-group" id="komunikatR"></div>
                        <div class="form-group">
                            <label for="email"><span class="glyphicon glyphicon-envelope"></span> E-mail (wymagane):</label> 
                            <input type="text" id="emailR" class = "form-control" name="email" placeholder="E-mail" />
                        </div>
                        <div class="form-group">
                            <label for="haslo"><span class="glyphicon glyphicon-eye-open"></span> Hasło (wymagane):</label>
                            <input type="password" id = "hasloR" class = "form-control" name="haslo" placeholder="Hasło"/>
                        </div>
                        <div class="form-group">
                            <label for="imie"><span class="glyphicon glyphicon-user"></span> Imię:</label> 
                            <input type="text" id="imnieR" class = "form-control" name="imie" placeholder="Imię" />
                        </div>                           
                        <div class="form-group">
                            <label for="nazwisko"><span class="glyphicon glyphicon-user"></span> Nazwisko:</label> 
                            <input type="text" id="nazwiskoR" class = "form-control" name="nazwisko" placeholder="Nazwisko" />
                        </div>
                        
                        <div class="form-group">
                            <label for="nrTel"><span class="glyphicon glyphicon-earphone"></span> Nr. Tefenou:</label> 
                            <input type="text" id="nrTelR" class = "form-control" name="nrTel" placeholder="Nr. Telefonu" />
                        </div> 
                        <button type="submit" id = "rejestracjaSubmit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Rejestracja </button>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-danger btn-block" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Anuluj</button>
                </div>
            </div>
        </div>
    </div>
    <div id="Logowanie" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3> Logowanie </h3>
                </div>
                <div class="modal-body">
                    <form id="logowanieForm" method="post" action="logowanie">
                        <div class="form-group" id="komunikatL"></div>
                        <div class="form-group">
                            <label for="email"><span class="glyphicon glyphicon-user"></span> Email:</label> 
                            <input type="text" id="emailL"class = "form-control" name="email" placeholder="E-mail" />
                        </div>
                        <div class="form-group">
                            <label for="haslo"><span class="glyphicon glyphicon-eye-open"></span> Haslo:</label>
                            <input type="password" id = "hasloL" class = "form-control" name="haslo" placeholder="Hasło"/>
                        </div>
                        <button type="submit" id="logowanieSubmit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Zaloguj </button>  
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-danger btn-block" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Anuluj</button>
                </div>
            </div>
        </div>
    </div>
    <div id="Wylogowanie" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3> Wylogowanie </h3>
                </div>
                <div class="modal-body">
                    <form method="post" action="wylogowanie">
                        Czy chcesz się wylogować?
                        <button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Wyloguj </button>  
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-danger btn-block" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Anuluj</button>
                </div>
            </div>
        </div>
    </div>
    <div id="log2" title = "Wylogowanie">
        <form id="fWyl" method="post" action="wylogowanie">
            Czy chcesz się wylogować?
        </form>
    </div>
</div>

