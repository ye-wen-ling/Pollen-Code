import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @author Yewenling
 * @create 2024-01-17-17:18
 */
public class FreeMakerTest {

    @Test
    public void test1() throws IOException, TemplateException {
        //创建Configuration对象，参数为当前引入的freemarker版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        //指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/template/"));
        //指定字符集
        configuration.setDefaultEncoding("UTF-8");
        //设置数字格式（处理数字输出时带，的问题）
        configuration.setNumberFormat("0.######");
        //指定模板文件
        Template template = configuration.getTemplate("MyWeb.htm.ftl");

        //设置数据模型
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("currentYear", 2024);
        List<Map<String, Object>> menuItems = new ArrayList<>();
        Map<String,Object> menuItem1 = new HashMap<>();
        menuItem1.put("url","https://www.bilibili.com/");
        menuItem1.put("label","bilibili");
        Map<String, Object> menuItem2 = new HashMap<>();
        menuItem2.put("url","https://baidu.com");
        menuItem2.put("label","百度");
        menuItems.add(menuItem1);
        menuItems.add(menuItem2);
        dataModel.put("menuItems", menuItems);

        Writer out = new FileWriter("myweb.html");
        template.process(dataModel, out);
        out.close();
    }
}
