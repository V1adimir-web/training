<#import "parts/common.ftlh" as c>
<#import "parts/logintempl.ftlh" as l>

<@c.page>
    <div class="mb-4">
        Регистрация нового пользователя
    </div>
    <div>
        ${message!} <#-- воскл. знак в конце добавлен на случай, если message будет пустым (иначе страница падает) от ifExists deprecated -->
    </div>
    <@l.login "/registration" true/>
</@c.page>