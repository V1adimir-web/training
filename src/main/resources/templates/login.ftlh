<#import "parts/common.ftlh" as c>
<#import "parts/logintempl.ftlh" as l>

<@c.page>
    <#--  для вывода Bad credentials (и в UserService изменен метод loadUserByUsername) -->
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??> <#-- если сессия открыта -->
        <div class="alert alert-danger" role="alert">
            Ошибка авторизации<#-- ${Session.SPRING_SECURITY_LAST_EXCEPTION.message} -->
        </div>
    </#if>

    <div class="mb-4 text-center">
        <h5>Перед началом обучения необходимо войти в программу или зарегистрироваться</h5>
    </div>

    <#if message??>
        <div class="alert alert-${messageType}" role="alert">
            ${message}
        </div>
    </#if>

    <#if registered??>
        <div class="alert alert-success" role="alert">
            ${registered}
        </div>
    </#if>

    <@l.login "/login" false />
</@c.page>