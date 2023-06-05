package com.xxxcloud.generator.core;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Properties;

public class GenerateController {

    /**
     * 这里的Button对象有需要加@FXML注解，然后变量的名称为你刚才在FXML文件中声明的Button的id属性
     */
    @FXML
    private Button generateBtn;

    @FXML
    private Button openFileBtn;
    /**
     * 表名，多个英文逗号分割
     */
    @FXML
    private TextField table;

    /**
     * 账号
     */
    @FXML
    private TextField userName;

    /**
     * 密码
     */
    @FXML
    private TextField password;

    /**
     * 数据库
     */
    @FXML
    private TextField dataBase;

    /**
     * 数据库地址
     */
    @FXML
    private TextField localhost;

    /**
     * 端口号
     */
    @FXML
    private TextField port;

    /**
     * 实体名
     */
    @FXML
    private TextField entityName;

    /**
     * 作者
     */
    @FXML
    private TextField author;

    /**
     * 模块名
     */
    @FXML
    private TextField moduleName;

    /**
     * 组织
     */
    @FXML
    private TextField organization;

    private static Properties properties;

    public void init() {
        try {
            InputStream in;
            //InputStream in = ClassLoader.getSystemResourceAsStream(System.getProperty("user.dir")+"/generator.config");
//            InputStream in = Main.class.getClassLoader().getResourceAsStream("D:\\IdeaProjects\\generatorApp\\");
            File file = new File(System.getProperty("user.dir") + "/xxxcloud-generator/generator.config");
            if (file.exists()){
                in = new FileInputStream(file);
            }else{
                file.createNewFile();
                in = new FileInputStream(file);
            }
            properties = new Properties();// 将输入流加载到配置对象,以使配置对象可以读取config.propertis信息
            properties.load(in);
            localhost.setText(StringUtils.isEmpty(properties.getProperty("location"))? Constant.location:properties.getProperty("location"));
            port.setText(StringUtils.isEmpty(properties.getProperty("port"))? Constant.port:properties.getProperty("port"));
            dataBase.setText(properties.getProperty("dataBase"));
            table.setText(properties.getProperty("table"));
            userName.setText(StringUtils.isEmpty(properties.getProperty("userName"))? Constant.userName:properties.getProperty("userName"));
            password.setText(StringUtils.isEmpty(properties.getProperty("password"))? Constant.password:properties.getProperty("password"));
            organization.setText(properties.getProperty("organization"));
            moduleName.setText(properties.getProperty("moduleName"));
            entityName.setText(properties.getProperty("entityName"));
            author.setText(StringUtils.isEmpty(properties.getProperty("author"))? Constant.author:properties.getProperty("author"));
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void backUp(){
        try {
            FileOutputStream out = new FileOutputStream(System.getProperty("user.dir") + "/xxxcloud-generator/generator.config", false);//true表示追加打开
            properties.setProperty("location",StringUtils.isEmpty(localhost.getText())?"":localhost.getText());
            properties.setProperty("port",StringUtils.isEmpty(port.getText())?"":port.getText());
            properties.setProperty("dataBase",StringUtils.isEmpty(dataBase.getText())?"":dataBase.getText());
            properties.setProperty("table",StringUtils.isEmpty(table.getText())?"":table.getText());
            properties.setProperty("userName",StringUtils.isEmpty(userName.getText())?"":userName.getText());
            properties.setProperty("password",StringUtils.isEmpty(password.getText())?"":password.getText());
            properties.setProperty("organization",StringUtils.isEmpty(organization.getText())?"":organization.getText());
            properties.setProperty("moduleName",StringUtils.isEmpty(moduleName.getText())?"":moduleName.getText());
            properties.setProperty("entityName",StringUtils.isEmpty(entityName.getText())?"":entityName.getText());
            properties.setProperty("author", StringUtils.isEmpty(author.getText())?System.getProperty("user.name"):author.getText());
            properties.store(out,"##配置文件，放在软件同级目录下");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 这里的handleButtonAction方法为我们在FXML文件中声明的onAction的处理函数
     *
     * @param event
     */
    @FXML
    protected void generateAction(ActionEvent event) {
        try {
            deleteFile(CodeGenerator.outPutDir);
            backUp();//保存生成信息
            generateBtn.setText("\u751f\u6210\u4e2d。。。");
            CodeGenerator.table = table.getText();
            CodeGenerator.dataBase = dataBase.getText();
            CodeGenerator.location = localhost.getText();
            CodeGenerator.port = port.getText();
            CodeGenerator.userName = userName.getText();
            CodeGenerator.password = password.getText();
            CodeGenerator.entityName = entityName.getText();
            CodeGenerator.author = author.getText();
            CodeGenerator.moduleName = moduleName.getText();
            CodeGenerator.organization = organization.getText();

            CodeGenerator.run();
            generateBtn.setText("\u751f\u6210\u7ed3\u675f");
        } catch (Exception e) {
            e.printStackTrace();
            generateBtn.setText("\u751f\u6210\u5931\u8d25");
        }
    }

    @FXML
    protected void openFileAction(ActionEvent event) {
        generateBtn.setText("\u751f\u6210");
        FileChooser file = new FileChooser();
        file.setTitle("\u6240\u6709\u751f\u6210\u7684\u4ee3\u7801");
//        file.setInitialDirectory(new File(openFileBtn.getText()));
        file.setInitialDirectory(new File(CodeGenerator.projectPath));
        Stage stage = (Stage) openFileBtn.getScene().getWindow();
        File newFolder = file.showOpenDialog(stage);//这个file就是选择的文件夹了
    }

    public static void deleteFile(String filePath) {
        File file = new File(filePath);//输入要删除文件目录的绝对路径
        //取得这个目录下的所有子文件对象
        File[] files = file.listFiles();
        if (file == null || !file.exists()) {
            return;
        }
        //遍历该目录下的文件对象
        for (File f : files) {
            //判断子目录是否存在子目录,如果是文件则删除
            if (f.isDirectory()) {
                deleteFile(f.getPath());
            } else {
                f.delete();
            }
        }
        //删除空文件夹  for循环已经把上一层节点的目录清空。
        file.delete();
    }


}
