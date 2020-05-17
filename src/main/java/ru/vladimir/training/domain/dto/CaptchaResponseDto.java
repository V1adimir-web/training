package ru.vladimir.training.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptchaResponseDto {

    private boolean success;

    // т.к. google будет передавать нам переменную с дефисом,а java не поддерживает такие переменные,то исп-ем аннотацию JsonAlias
    @JsonAlias("error-codes")
    private Set<String> errorCodes;
    //==================================================================================================================
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    //==================================================================================================================
    public Set<String> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(Set<String> errorCodes) {
        this.errorCodes = errorCodes;
    }
}
