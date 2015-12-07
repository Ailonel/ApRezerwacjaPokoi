<%-- 
    Document   : menu
    Created on : 2015-11-30, 00:21:57
    Author     : Mateusz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    $(document).on("click", "#rejestracja", function ()
    {
        $("#rej").dialog({
            modal: true,
            buttons: {
                "Zaloguj": function () {
                    $("#fRej").ajaxSubmit(function (responseText) {
                        $("#fRej").html(responseText);
                        $("#fRej").dialog({
                            buttons: {
                                "OK": function () {
                                    $(this).dialog("close");
                                }
                            }
                        });
                    });
                },
                "Anuluj": function () {
                    $(this).dialog("close");
                }
            }
        });
    });

    //$(document).on("click", "#logowanie1", function ()
    //{
    //  $("#log1").dialog({
    // });
    // });
    $(document).on("click", "#logowanie1", function ()
    {
        $("#Logowanie").modal();
    });

    $(document).on("click", "#logowanie2", function ()
    {
        $("#log2").dialog({
            modal: true,
            buttons: {
                "OK": function () {
                    $("form#fWyl").submit();
                },
                "Anuluj": function () {
                    $(this).dialog("close");
                }
            }
        });
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
            <li><a href = "rezerwacja.jsp">Rezerwacja</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <%if (userName == null) {%>
            <li><a id="rejestracja"><span class="glyphicon glyphicon-user"></span> Rejestracja</a></li>
            <li><a id="logowanie1" data-toggle= "modal" data-target="#Logowanie"><span class="glyphicon glyphicon-log-in data-"></span> Logowanie </a></li>
                <% } else {%>
            <li><a><span class="glyphicon glyphicon-user"></span> <%= userName%> </a></li>
            <li><a id="logowanie2"><span class="glyphicon glyphicon-log-in"></span> Wyloguj </a></li>
                <% }%>
        </ul>
    </nav>
    <div id="rej" title = "Rejestracja">
        <form id="fRej" method="post" action="rejestracja">
            <label>Imie:</label> <br>
            <input type="text" name="imie" />
            <label>Nazwisko:</label> <br>
            <input type="text" name="nazwisko" />
            <label>Email:</label> <br>
            <input type="text" name="email" />
            <label>NrTelefonu:</label> <br>
            <input type="text" name="nrTel" />
            <label>Haslo:</label> <br>
            <input type="password" name="haslo" />
            <input type ="submit" value="rejestracja" />
        </form>
    </div>
    <div id="log1" title = "Logowanie">
        <form id="fLog" method="post" action="logowanie">
            <label>Email:</label> 
            <input type="text" name="email" />
            <label>Haslo:</label>
            <input type="password" name="haslo" />
        </form>
    </div>

    <div id="Logowanie" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3> Logowanie </h3>
                    </div>
                    <div class="modal-body">
                        <form method="post" action="logowanie">
                            <div class="form-group">
                                <label for="email"><span class="glyphicon glyphicon-user"></span>Email:</label> 
                                <input type="text" name="email" placeholder="E-mail" />
                            </div>
                            <div class="form-group">
                                <label for="haslo"><span class="glyphicon glyphicon-eye-open"></span>Haslo:</label>
                                <input type="password" name="haslo" placeholder="Hasło"/>
                            </div>
                            <button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Zaloguj </button>
                        </form>
                    </div>
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

