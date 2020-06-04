<#macro edit formTitle buttonTitle path>
    <div class="mb-4 text-center">
        <h5>${formTitle}</h5>
    </div>
    <form class="col-sm-6 mx-auto" action="${path}" method="post">  <#-- action="/subject/update" -->
        <div class="d-flex justify-content-center">
            <label class="col-sm-2 col-form-label ml-5">Предмет:</label>
            <div class="col-sm-7">
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
        <div class="d-flex justify-content-center mt-3">
            <button class="btn btn-primary" type="submit">${buttonTitle}</button>
        </div>
    </form>
</#macro>