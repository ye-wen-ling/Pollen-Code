package com.yupi.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @author Yewenling
 * @create 2024-01-12-17:13
 */
public class StaticGenerator {
    public static void main(String[] args){
        //获取该项目的路径
        String property = System.getProperty("user.dir");
        System.out.println(property);
        //输入路径
        String inputPath = property + File.separator + "Pollen-Code-demo-projects"+ File.separator +"acm-template";
        System.out.println(inputPath);
        //输出路径
        String outputPath = property;
        copyFilesByRecursive(inputPath, outputPath);
    }

    /**
     * 通过Hutool工具将输入路径下的目录（或者文件）复制至输出路径下
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     */
    public static void copyFilesByHutool(String inputPath, String outputPath){
        FileUtil.copy(inputPath, outputPath ,false);
    }

    //

    /**
     * 递归拷贝文件
     * @param inputPath
     * @param outputPath
     */
    public static void copyFilesByRecursive(String inputPath, String outputPath){
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFileByRecursive(inputFile, outputFile);
        }catch (Exception e){
            System.out.println("文件复制失败");
            e.printStackTrace();
        }
    }


    /**
     * 若输入位置是目录则新建目录并获取该目录下的文件或目录，若输入位置是文件则直接拷贝
     * @param inputFile
     * @param outputFile
     * @throws IOException
     */
    private static void copyFileByRecursive(File inputFile, File outputFile) throws IOException {
        //判断是否存在目录
        if (inputFile.isDirectory()) {
            System.out.println(inputFile.getName());
            File destOutputFile = new File(outputFile, inputFile.getName());
            //若未存在该目录则先新建目录
            if(!destOutputFile.exists()){
                destOutputFile.mkdir();
            }
            //遍历目录下的所以文件和目录
            File[] files = inputFile.listFiles();
            //目录下无子文件或目录时直接退出
            if (ArrayUtil.isEmpty(files)) {
                return;
            }
            //递归拷贝下一层目录
            for (File file:
                 files) {
                copyFileByRecursive(file, destOutputFile);
            }
        }else{
            //为文件时直接拷贝文件到输出目录下
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
