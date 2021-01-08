package ru.javalab;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"ru.javalab.HtmlForm", "ru.javalab.HtmlInput"})
public class HtmlProcessor extends AbstractProcessor {

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        for (Element element : annotatedElements) {
            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = path.substring(1) + element.getSimpleName().toString() + ".html";
            Path out = Paths.get(path);

            HtmlForm annotation = element.getAnnotation(HtmlForm.class);

            Map<String, Object> root = new HashMap<>();
            root.put("action", annotation.action());
            root.put("method", annotation.method());
            List<? extends Element> inputs = element.getEnclosedElements();
            root.put("inputs", inputs.stream().filter(elem -> elem.getKind().isField()).map(elem -> elem.getAnnotation(HtmlInput.class)).collect(Collectors.toList()));
            HtmlCreator.createHtml("form.ftl", root, out);

        }
        return true;
    }


}
