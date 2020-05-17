<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row"> <#-- Bootstrap -->
            <label class="col-sm-2 col-form-label">Логин:</label> <#-- class означает,что ширина текущего эл-та - 2 колонки и данный эл-т явл. эл-том поля ввода формы -->
            <div class="col-sm-6">
                <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                       class="form-control ${(usernameError??)?string('is-invalid', '')}"
                       placeholder="Введите логин" />
                <#if usernameError??> <#-- выводим сообщение об ошибке если имеется usernameError -->
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row"> <#-- Bootstrap -->
            <label class="col-sm-2 col-form-label">Пароль:</label>
            <div class="col-sm-6"> <#-- ширина 10 колонок -->
                <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid', '')}"
                       placeholder="Введите пароль" />
                <#if passwordError??> <#-- выводим сообщение об ошибке если имеется passwordError -->
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group row"> <#-- Bootstrap -->
                <label class="col-sm-2 col-form-label">Пароль:</label>
                <div class="col-sm-6"> <#-- ширина 10 колонок -->
                    <input type="password" name="password2" class="form-control ${(password2Error??)?string('is-invalid', '')}"
                           placeholder="Повторите пароль" />
                    <#if password2Error??> <#-- выводим сообщение об ошибке если имеется password2Error -->
                        <div class="invalid-feedback">
                            ${password2Error}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row"> <#-- Bootstrap -->
                <label class="col-sm-2 col-form-label">Почтовый ящик:</label>
                <div class="col-sm-6"> <#-- ширина 10 колонок -->
                    <input type="email" name="email" value="<#if user??>${user.email}</#if>"
                           class="form-control ${(emailError??)?string('is-invalid', '')}"
                           placeholder="some@some.com"/>
                    <#if emailError??> <#-- выводим сообщение об ошибке если имеется emailError -->
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="col-sm-6">
                <#-- для капчи -->
                <div class="g-recaptcha" data-sitekey="6LfMO_UUAAAAAEg3DsNXJLjm60qnUy_Gae5wXrlc"></div>
                <#if captchaError??>
                    <div class="alert alert-danger" role="alert">
                        ${captchaError}
                    </div>
                </#if>
            </div>

        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if !isRegisterForm>
            <a href="/registration">Регистрация</a>
        </#if>
        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Создать<#else>Войти</#if></button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit">Выход</button>
    </form>
</#macro>