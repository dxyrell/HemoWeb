package com.example.hemoweb.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Aspect
@Component
public class ApplicationLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationLoggingAspect.class);

    // Pointcut para interceptar Controllers
    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void controllersPointcut() {
    }

    // Pointcut para interceptar Services
    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void servicesPointcut() {
    }

    // Interceptação de métodos nos Controllers
    @Around("controllersPointcut()")
    public Object logAroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        logMethodName(joinPoint);
        logParametersReceived(joinPoint);
        Object result = joinPoint.proceed();
        String viewName = getViewName(result);

        if (viewName != null) {
            if (viewName.startsWith("redirect:")) {
                logger.trace("Redirecionando para a URL: {}", viewName.substring(9));
            } else {
                logger.trace("Encaminhando para a view: {}", viewName);
            }
        }

        return result;
    }

    // Interceptação de métodos nos Services
    @Around("servicesPointcut()")
    public Object logAroundService(ProceedingJoinPoint joinPoint) throws Throwable {
        logMethodName(joinPoint);
        logParametersReceived(joinPoint);
        Object result = joinPoint.proceed();

        if (result instanceof byte[]) {
            logger.trace("Retornando: byte[] do relatório PDF");
        } else {
            logger.trace("Retornando: {}", result);
        }

        return result;
    }

    // Método para capturar parâmetros do método interceptado
    private Map<String, Object> getParameters(JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        HashMap<String, Object> map = new HashMap<>();
        String[] parameterNames = signature.getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            map.put(parameterNames[i], joinPoint.getArgs()[i]);
        }
        return map;
    }

    // Método para logar o nome do método interceptado
    private void logMethodName(ProceedingJoinPoint joinPoint) {
        String fullClassName = joinPoint.getSignature().getDeclaringTypeName();
        int lastDotIndex = fullClassName.lastIndexOf(".");
        String className = (lastDotIndex != -1) ? fullClassName.substring(lastDotIndex + 1) : fullClassName;
        logger.trace("Entrou no método: {}.{}", className, joinPoint.getSignature().getName());
    }

    // Método para logar os parâmetros recebidos
    private void logParametersReceived(ProceedingJoinPoint joinPoint) {
        Map<String, Object> parameters = getParameters(joinPoint);
        if (!parameters.isEmpty()) {
            logger.debug("Parâmetros recebidos:");
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                // Não logar objetos do tipo Model ou BindingResult
                if (!entry.getKey().equals("model") &&
                    !entry.getKey().equals("org.springframework.validation.BindingResult.model")) {
                    logger.debug("\t{}: {}", entry.getKey(), entry.getValue());
                }
            }
        }
    }

    // Método para capturar o nome da view, se aplicável
    private String getViewName(Object result) {
        String viewName = null;
        if (result instanceof ModelAndView) {
            ModelAndView modelAndView = (ModelAndView) result;
            viewName = modelAndView.getViewName();
        } else if (result instanceof String) {
            viewName = (String) result;
        }
        return viewName;
    }
}
