<#include "security.ftlh">
<#import "logintempl.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Train-In</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">На главную</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Главная</a>
            </li>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">Список пользователей</a>
                </li>
            </#if>
            <#if user??>    <#-- если это авторизованный user / приводим перем. user к булеву типу -->
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile">Мой профиль</a>
                </li>
            </#if>
        </ul>
        <div class="navbar-text mr-3">${name}</div> <#-- mr-3 - отступ вправо на 3 пункта -->
        <@l.logout />
    </div>
</nav>