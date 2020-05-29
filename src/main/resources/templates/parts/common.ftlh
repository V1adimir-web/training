<#include "security.ftlh">
<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Train-In</title>
        <link rel="stylesheet" href="/static/style.css">

        <#-- Bootstrap: данная строка сообщает браузеру, что он должен считывать плотность пикселей на экране устройства и
         и делать крупнее шрифт и эл-ты инрерфейса (для моб. устройств и ноутбуков), т.е. нормально отображать: -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS ; здесь мы подключаем стили Bootstrap: -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <!-- для капчи -->
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body>
    <#if isAdmin>
        <#include "adminnavbar.ftlh">
    <#else>
        <#include "navbar.ftlh">
    </#if>
    <div class="d-flex bd-highlight">
        <#if !isAdmin>
            <div class="p-2 bd-highlight mt-1">
                <#include "leftnavbar.ftlh">
            </div>
        </#if>
        <div class="p-2 flex-fill bd-highlight mt-1 mb-5">  <#-- grow-1    text-center -->
            <#nested>
        </div>
    </div>
    <#include "footer.ftlh">
    <!-- Bootstrap: Optional JavaScript -->
    <!-- jQuery first, then Popper.js - для отрисовки всплывающих ToolTip, then Bootstrap JS - jQuery плагины, обеспечивающие функциональность -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </body>
    </html>
</#macro>