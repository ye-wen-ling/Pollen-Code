package com.yupi.model;

import lombok.Data;

/**
 * @author Yewenling
 * @create 2024-01-17-17:58
 */
@Data
public class MainTemplateConfig {
    /**
     * 是否循环
     */
    private boolean loop = false;
    /**
     * 作者注释
     */
    private String author = "yewenling";
    /**
     * 输出信息
     */
    private String outputText = "输出结果";
}
