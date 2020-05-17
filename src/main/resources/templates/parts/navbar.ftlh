<#include "security.ftlh">
<#import "logintempl.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-primary">
    <a class="navbar-brand text-white" href="/">Train-In</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Занятия
                </a>
                <div class="dropdown-menu bg-primary" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="/main">Математика</a>
                    <a class="dropdown-item" href="/main">Русский язык</a>
                    <a class="dropdown-item" href="/main">Окружающий мир</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/main">Дополнительно</a>
                </div>
            </li>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link text-white" href="/user">Список пользователей</a>
                </li>
            </#if>
            <#if user??>    <#-- если это авторизованный user / приводим перем. user к булеву типу -->
                <li class="nav-item">
                    <a class="nav-link text-white" href="/user/profile">Мой профиль</a>
                </li>
            </#if>
        </ul>
        <div class="navbar-text mr-3 text-white">${name}</div> <#-- mr-3 - отступ вправо на 3 пункта -->
        <@l.logout />
    </div>
</nav>