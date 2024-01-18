package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @author Yewenling
 * @create 2024-01-17-19:43
 */
public class MainGenerator {

    public static void main(String[] args) throws TemplateException, IOException {
        //生成静态文件
        //输入路径
        String inputPath = "Pollen-Code-demo-projects"+ File.separator +"acm-template";
        //输出路径
        String outputPath = ".";
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);

        //生成动态文件
        //创建Configuration对象，参数为当前引入的freemarker版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        //指定模板文件所在的路径

        String dynamicInputPath = "Pollen-Code-basic"+ File.separator +"src/main/resources/template/"+ File.separator + "MainTemplate.java.ftl";
        String dynamicOutputPath = "acm-template/src/com/yupi/acm/MainTemplate.java";


        //数据模型
        MainTemplateConfig dataModel = new MainTemplateConfig();
        dataModel.setAuthor("yewenling");
        dataModel.setLoop(false);
        dataModel.setOutputText("总和为：");

        DynamicGenerator.doGenerator(dynamicInputPath, dynamicOutputPath, dataModel);
    }
}
