<#assign
known = Session.SPRING_SECURITY_CONTEXT??   <#-- ?? означает приведение к булеву типу -->
>

<#if known> <#-- если user авторизован, то получаем его данные -->
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    isAdmin = user.isAdmin()
    >
<#else>
    <#assign
    name = "Гость"
    isAdmin = false
    >
</#if>