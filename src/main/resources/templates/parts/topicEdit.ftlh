<#macro edit formTitle buttonTitle path>
    <div class="mb-4 text-center">
        <h5>${formTitle}</h5>
    </div>
    <form class="col-sm-6 mx-auto" action="${path}" method="post">  <#-- action="/subject/update" -->
        <div class="form-group row d-flex justify-content-center">
            <label class="col-sm-4 col-form-label" for="subjectSelect">Выберете предмет:</label>
            <div class="col-sm-7">
                <select class="form-control" id="subjectSelect" name="subjectname">
                    <option selected><#if subjectname??>${subjectname}<#else>Предметы...</#if></option>
                    <#list subjects as subject>
                        <#if subjectname??>
                            <#if !subject.subjectname?matches(subjectname)>
                                <option>${subject.subjectname}</option>
                            </#if>
                        <#else>
                            <option>${subject.subjectname}</option>
                        </#if>
                    </#list>
                </select>
            </div>
        </div>
        <div class="form-group row d-flex justify-content-center">
            <label class="col-sm-4 col-form-label">Наименование упражнения:</label>
            <div class="col-sm-7">
                <input type="text" name="title" value="<#if title??>${title}</#if>"
                       class="form-control ${(titleError??)?string('is-invalid', '')}" placeholder="Введите наименование упражнения" />
                <#if titleError??>
                    <div class="invalid-feedback">
                        ${titleError}
                    </div>
                </#if>
            </div>
        </div>
        <input type="hidden" name="topicId" value="<#if topicid??>${topicid!}</#if>" />
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="d-flex justify-content-center mt-3">
            <button class="btn btn-outline-primary" type="submit">${buttonTitle}</button>
        </div>
    </form>
</#macro>