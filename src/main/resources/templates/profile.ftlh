<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="mb-4 text-center">
        <h5>Привет, ${username!'anonim'}</h5>
    </div>
    <#if message??>
        <div class="alert alert-success d-flex justify-content-center" role="alert">
            ${message}
        </div>
    </#if>
    <div class="mx-auto col-sm-5">
        <div class="card">
            <div class="card-header text-center">
                <h5>Личные данные</h5>
            </div>
            <div class="card-body">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                        <small id="firstnameTitleBlock" class="form-text text-muted">
                            Имя
                        </small>
                        <#if firstname??>${firstname}<#else>Не заполнено</#if>
                        <small id="firstnameHelpBlock" class="form-text text-muted">
                            Имя используется для обращения к Вам на сайте
                        </small>
                    </li>
                    <li class="list-group-item">
                        <small id="lastnameTitleBlock" class="form-text text-muted">
                            Фамилия
                        </small>
                        <#if lastname??>${lastname}<#else>Не заполнено</#if>
                        <small id="firstnameHelpBlock" class="form-text text-muted">
                            Фамилия используется для обращения к Вам на сайте
                        </small>
                    </li>
                    <li class="list-group-item">
                        <small id="loginTitleBlock" class="form-text text-muted">
                            Логин
                        </small>
                        <#if username??>${username}<#else>Не заполнено</#if>
                        <small id="loginHelpBlock" class="form-text text-muted">
                            Логин используется для входа на сайт
                        </small>
                    </li>
                    <li class="list-group-item">
                        <small id="emailTitleBlock" class="form-text text-muted">
                            Email
                        </small>
                        <#if email??>${email}<#else>Не заполнено</#if>
                    </li>
                </ul>
                <div class="d-flex justify-content-center mt-3">
                    <a href="#" class="btn btn-outline-primary">Изменить личные данные</a>
                    <a href="/user/profile/changepass" class="btn btn-outline-primary ml-3">Сменить пароль</a>
                </div>
            </div>
            <div class="card-footer text-muted d-flex justify-content-center">
                Дата последнего изменения: <#if lastdatechange??>${lastdatechange}<#else>не указано</#if>
            </div>
        </div>
    </div>
</@c.page>