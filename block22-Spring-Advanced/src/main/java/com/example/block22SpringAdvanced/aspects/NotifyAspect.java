package com.example.block22SpringAdvanced.aspects;

import com.example.block22SpringAdvanced.util.SpringAdvancedUtil;
import com.example.block22SpringAdvanced.util.annotations.Notify;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@Aspect
public class NotifyAspect {

    // El aspecto será interceptado por un punto de intercepcion. @Before @After o @Around
    @After(value = "@annotation(com.example.block22SpringAdvanced.util.annotations.Notify)")
    public void notifyInFile(JoinPoint joinPoint) throws IOException {
        var args = joinPoint.getArgs();
        var size = args[1];
        var order = args[2] == null ? "None" : args[2];
        var signature = (MethodSignature) joinPoint.getSignature();
        var method = signature.getMethod();
        var annotation = method.getAnnotation(Notify.class);
        var text = String.format(LINE_FORMAT, LocalDateTime.now(), annotation.value(), size.toString(), order.toString());

        SpringAdvancedUtil.writeNotification(text, PATH);
    }

    private static final String LINE_FORMAT = "At %s new request %S, with size page %s and order %s";
    private static final String PATH = "files/notify.txt";
}
