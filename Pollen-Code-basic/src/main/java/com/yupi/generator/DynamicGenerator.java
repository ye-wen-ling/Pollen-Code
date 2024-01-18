package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author Yewenling
 * @create 2024-01-17-18:05
 */
public class DynamicGenerator {

    public static void main(String[] args) throws IOException, TemplateException {
        //创建Configuration对象，参数为当前引入的freemarker版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        //指定模板文件所在的路径

        String inputPath = "Pollen-Code-basic"+ File.separator +"src/main/resources/template/"+ File.separator + "MainTemplate.java.ftl";
        String outputPath = "MainTemplate.java";


        //数据模型
        MainTemplateConfig dataModel = new MainTemplateConfig();
        dataModel.setAuthor("yewenling");
        dataModel.setLoop(false);
        dataModel.setOutputText("总和为：");

        doGenerator(inputPath, outputPath, dataModel);
    }

    public static void doGenerator(String inputPath, String outputPath, Object model) throws IOException, TemplateException {
        //创建Configuration对象，参数为当前引入的freemarker版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        //指定模板文件所在的路径
        File file = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(file);
        //指定字符集
        configuration.setDefaultEncoding("UTF-8");
        //设置数字格式（处理数字输出时带，的问题）
        configuration.setNumberFormat("0.######");
        //指定模板文件
        String fileName = new File(inputPath).getName();
        Template template = configuration.getTemplate(fileName);


        Writer out = new FileWriter(outputPath);
        template.process(model, out);
        out.close();
    }
}
