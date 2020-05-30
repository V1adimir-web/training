<#macro edit formTitle buttonTitle path>
    <div class="mb-4 text-center">
        <h5>${formTitle}</h5>
    </div>
    <form class="col-sm-6 mx-auto" action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-4 col-form-label" for="topicSelect">Выберете упражнение для задачи:</label>
            <div class="col-sm-7">
                <select class="form-control" id="topicSelect" name="topictitle">
                    <option selected><#if topictitle??>${topictitle}<#else>Упражнения...</#if></option>
                    <#list topics as topic>
                        <#if topictitle??>
                            <#if !topic.title?matches(topictitle)>
                                <option>${topic.title}</option>
                            </#if>
                        <#else>
                            <option>${topic.title}</option>
                        </#if>
                    </#list>
                </select>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-4 col-form-label">Условие задачи:</label>
            <div class="col-sm-7">
                <input type="text" name="content" value="<#if content??>${content}</#if>"
                       class="form-control ${(contentError??)?string('is-invalid', '')}" placeholder="Введите условие задачи" />
                <#if contentError??>
                    <div class="invalid-feedback">
                        ${contentError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-4 col-form-label">Правильный ответ:</label>
            <div class="col-sm-7">
                <input type="text" name="rightanswer" value="<#if rightanswer??>${rightanswer}</#if>"
                       class="form-control ${(rightanswerError??)?string('is-invalid', '')}" placeholder="Введите правильный ответ" />
                <#if rightanswerError??>
                    <div class="invalid-feedback">
                        ${rightanswerError}
                    </div>
                </#if>
            </div>
        </div>
        <input type="hidden" name="taskId" value="<#if taskid??>${taskid!}</#if>" />
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-outline-primary" type="submit">${buttonTitle}</button>
    </form>
</#macro>