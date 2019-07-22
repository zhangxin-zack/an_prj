package com.scorer.feign._Spring;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorPageConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry errorPageRegistry) {
        ErrorPage e401 = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
        ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
        ErrorPage e500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
        errorPageRegistry.addErrorPages(e401, e404, e500);
    }

}