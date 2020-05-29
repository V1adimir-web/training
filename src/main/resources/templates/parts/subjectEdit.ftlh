<#macro edit formTitle buttonTitle path>
    <div class="mb-4">
        <h5>${formTitle}</h5>
    </div>
    <form action="${path}" method="post">  <#-- action="/subject/update" -->
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Предмет:</label>
            <div class="col-sm-6">
                <input type="text" name="subjectname" value="<#if subjectname??>${subjectname}</#if>"
                       class="form-control ${(subjectnameError??)?string('is-invalid', '')}" placeholder="Введите предмет" />
                <#if subjectnameError??>
                    <div class="invalid-feedback">
                        ${subjectnameError}
                    </div>
                </#if>
            </div>
        </div>
        <input type="hidden" name="subjectId" value="<#if subjectid??>${subjectid!}</#if>" />
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">${buttonTitle}</button>
    </form>
</#macro>