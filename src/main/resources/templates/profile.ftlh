<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>${username}</h5>
    ${message!} <#-- -->
    <form method="post">
        <div class="form-group row"> <#-- Bootstrap -->
            <label class="col-sm-2 col-form-label">Пароль:</label>
            <div class="col-sm-6"> <#-- ширина 10 колонок -->
                <input type="password" name="password" class="form-control" placeholder="Введите пароль" />
            </div>
        </div>
        <div class="form-group row"> <#-- Bootstrap -->
            <label class="col-sm-2 col-form-label">Email:</label>
            <div class="col-sm-6"> <#-- ширина 10 колонок -->
                <input type="email" name="email" class="form-control" placeholder="some@some.com" value="${email!''}" />
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit">Сохранить</button>
    </form>
</@c.page>