package com.xxxcloud.generator.javafx;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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


    private String author = "Lin Hongli";  //注释：@author 作者
    private String projectPath = System.getProperty("user.dir");
    private String projectName = "GeneratorCode";//***子模块名
    private boolean isOverride = true;//是否覆盖

    private String driverName = "com.mysql.cj.jdbc.Driver";

    private String packageName = "";   //包名(组织名)
    private String modelName = "";  //功能模块名

    private String tablePre = "";  //***表前缀（去除后Entity名）


    /**
     * 1、全局配置
     */
    private GlobalConfig globalConfig() {
        return new GlobalConfig()
                .setActiveRecord(false)//是否让entity继承Model<T>使其拥有CRUD功能
                .setAuthor(author) //作者
                .setOutputDir(projectPath + "/" + projectName)
                //.setOutputDir("E:\\workspace\\MyBatis-demo\\src\\main\\java")  //生成路径
                .setOpen(false)
                .setDateType(DateType.ONLY_DATE)//表字段与实体属性对应的数据类型
                .setFileOverride(isOverride)//是否文件覆盖，如果多次
                .setServiceName("I%sService") //设置生成的service接口名首字母是否为I
                .setIdType(IdType.AUTO) //主键策略
                .setBaseResultMap(true)
                .setBaseColumnList(true);
        //.setSwagger2(true); //实体属性 Swagger2 注解
    }

    /**
     * 2、数据源配置
     */
    private DataSourceConfig dataSourceConfig(String url, String userName, String password) {
        return new DataSourceConfig()
                //.setSchemaName("public")
                .setDbType(DbType.MYSQL)
                .setUrl(url)
                .setDriverName(driverName)
                .setUsername(userName)
                .setPassword(password);
    }

    /**
     * 3、包名配置
     */
    private PackageConfig packageConfig() {

        return new PackageConfig()
                .setParent(packageName)//父包名（组织名）
                .setController("controller")
                .setEntity("entity")
                .setService("service")
                .setMapper("dao")
                .setXml("mapper")
                .setModuleName(modelName);
    }

    /**
     * 4、生成策略配置
     */
    private StrategyConfig strategyConfig(String table) {
        return new StrategyConfig()
                .setCapitalMode(true) // 全局大写命名
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                .setTablePrefix(tablePre)// 表前缀
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)//@RestController
                //.setSuperEntityClass("你自己的父类实体,没有就不用设置!") // Entity的公共父类
                //.setSuperControllerClass("你自己的父类控制器,没有就不用设置!") // Controller的公共父类
                .setEntityTableFieldAnnotationEnable(true) //@TableName("xxx") @TableField("xxx")
                //.setChainModel(true) //@Accessors(chain = true)
                // 写于父类中的公共字段
                .setSuperEntityColumns("id")
                .setInclude(table) //生成的表名，多个用逗号隔开
                .setControllerMappingHyphenStyle(true);
    }

    /**
     * 5、自定义配置
     */
    private InjectionConfig injectionConfig(final String entityName) {

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // String templatePath = "/templates/mapper.xml.ftl"; // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.vm"; // 如果模板引擎是 velocity

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        // 自定义配置会被优先输出

        focList.add(new FileOutConfig(templatePath) {

            @Override
            public String outputFile(TableInfo tableInfo) {
                tableInfo.setEntityName(entityName);
                tableInfo.setServiceName("I" + entityName + "Service");
                tableInfo.setServiceImplName(entityName + "ServiceImpl");
                tableInfo.setControllerName(entityName + "Controller");
                tableInfo.setMapperName(entityName + "Mapper");

                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                //return projectPath + "/src/main/resources/mapper/" + pkConfig.getModuleName()+ "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                return projectPath + "/" + projectName +"/mapper/"+ tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        return cfg;
    }


    /**
     * 6、自定义输出模板
     */
    private TemplateConfig templateConfig(String xml) {
        return new TemplateConfig().setXml(xml);
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();
    }


    /**
     * 这里的handleButtonAction方法为我们在FXML文件中声明的onAction的处理函数
     *
     * @param event
     */
    @FXML
    protected void generateAction(ActionEvent event) {
        try {
            deleteFile(System.getProperty("user.dir") + "/" + projectName);
            generateBtn.setText("生成中。。。");

            String url = "jdbc:mysql://" + localhost.getText() + ":" + port.getText() + "/" + dataBase.getText() + "?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT&allowPublicKeyRetrieval=true";

            //1. 全局配置
            GlobalConfig glConfig = globalConfig();
            //2. 数据源配置
            DataSourceConfig dsConfig = dataSourceConfig(url, userName.getText(), password.getText());
            //3.包名策略
            PackageConfig pkConfig = packageConfig();
            //4.策略配置
            StrategyConfig stConfig = strategyConfig(table.getText());
            //5、自定义配置
            InjectionConfig cfg = injectionConfig(entityName.getText());
            //6、自定义输出模板
            TemplateConfig templateConfig = templateConfig(null);

            //整合配置
            new AutoGenerator().setGlobalConfig(glConfig)
                    .setDataSource(dsConfig)
                    //.setTemplateEngine(new FreemarkerTemplateEngine())//设置模板引擎 //@EqualsAndHashCode(callSuper = true)
                    .setTemplateEngine(new VelocityTemplateEngine()) //@EqualsAndHashCode(callSuper = false)
                    .setPackageInfo(pkConfig)
                    .setStrategy(stConfig)
                    .setCfg(cfg)
                    .setTemplate(templateConfig)
                    .execute();
            generateBtn.setText("生成结束");
        } catch (Exception e) {
            e.printStackTrace();
            generateBtn.setText("生成失败");
        }
    }

    @FXML
    protected void openFileAction(ActionEvent event) {
        generateBtn.setText("生成");
        FileChooser file = new FileChooser();
        file.setTitle("所有生成的代码");
//        file.setInitialDirectory(new File(openFileBtn.getText()));
        file.setInitialDirectory(new File(System.getProperty("user.dir") + "/" + projectName));
        Stage stage = (Stage) openFileBtn.getScene().getWindow();
        File newFolder = file.showOpenDialog(stage);//这个file就是选择的文件夹了
    }



    public static void deleteFile(String filePath){
        File file = new File(filePath);//输入要删除文件目录的绝对路径
        //取得这个目录下的所有子文件对象
        File[] files = file.listFiles();
        if (file==null||!file.exists()){
            return;
        }
        //遍历该目录下的文件对象
        for (File f: files){
            //判断子目录是否存在子目录,如果是文件则删除
            if (f.isDirectory()){
                deleteFile(f.getPath());
            }else {
                f.delete();
            }
        }
        //删除空文件夹  for循环已经把上一层节点的目录清空。
        file.delete();
    }
}
