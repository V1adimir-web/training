<#import "logintempl.ftlh" as l>

<nav class="navbar sticky-top navbar-expand-lg navbar-light bg-primary">
    <a class="navbar-brand text-white" href="/">Train-In</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link text-white" href="/user">Список пользователей</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="/subject">Предметы</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="/topic">Темы</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="/user/profile">Мой профиль</a>
            </li>
        </ul>
        <div class="navbar-text mr-3 text-white">${name}</div> <#-- mr-3 - отступ вправо на 3 пункта -->
        <@l.logout />
    </div>
</nav>