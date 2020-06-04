<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="mb-4 text-center">
        <h5>Измение пароля пользователя <b>${username!''}</b></h5>
    </div>
    <form class="col-sm-6 mx-auto" action="/user/profile/changepass" method="post">
        <div class="form-group row d-flex justify-content-center">
            <label class="col-sm-3 col-form-label">Новый пароль:</label>
            <div class="col-sm-6">
                <input type="password" name="newpassword" class="form-control ${(newpasswordError??)?string('is-invalid', '')}"
                       placeholder="Введите новый пароль" />
                <#if newpasswordError??>
                    <div class="invalid-feedback">
                        ${newpasswordError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row d-flex justify-content-center">
            <label class="col-sm-3 col-form-label">Новый пароль еще раз:</label>
            <div class="col-sm-6">
                <input type="password" name="newpassword2" class="form-control ${(newpassword2Error??)?string('is-invalid', '')}"
                       placeholder="Введите новый пароль еще раз" />
                <#if newpassword2Error??>
                    <div class="invalid-feedback">
                        ${newpassword2Error}
                    </div>
                </#if>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="d-flex justify-content-center mt-3">
            <button class="btn btn-primary" type="submit">Изменить</button>
        </div>
    </form>
</@c.page>