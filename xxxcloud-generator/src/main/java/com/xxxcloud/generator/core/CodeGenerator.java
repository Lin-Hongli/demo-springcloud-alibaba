package com.xxxcloud.generator.core;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 * mybatis代码生成器
 *
 * @author LinHongli
 */
// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator extends Constant {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void run(){
        //整合配置
        new AutoGenerator()
                .setGlobalConfig(CodeGenerator.globalConfig())
                .setDataSource(CodeGenerator.dataSourceConfig())
                .setPackageInfo(CodeGenerator.packageConfig())
                .setStrategy(CodeGenerator.strategyConfig())
                .setCfg(CodeGenerator.injectionConfig(entityName))
                .setTemplate(CodeGenerator.templateConfig())
                //设置模板引擎
                .setTemplateEngine(new FreemarkerTemplateEngine()) //@EqualsAndHashCode(callSuper = true)
                //.setTemplateEngine(new VelocityTemplateEngine()) //@EqualsAndHashCode(callSuper = false)
                .execute();
    }

    /**
     * 1、全局配置
     */
    public static GlobalConfig globalConfig() {
        return new GlobalConfig()
                .setOutputDir(outPutDir)//生成路径 projectPath + "/src/main/java"
                .setAuthor(author) //作者
                .setSwagger2(true) //实体属性 Swagger2 注解
                .setFileOverride(true)//是否文件覆盖，如果多次

                .setServiceName("I%sService")//service 命名方式
                .setServiceImplName("%sServiceImpl")
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")

                .setOpen(false)

                .setDateType(DateType.ONLY_DATE)//表字段与实体属性对应的数据类型
                .setIdType(IdType.AUTO) //主键策略
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setActiveRecord(false);//是否让entity继承Model<T>使其拥有CRUD功能
    }


    /**
     * 2、数据源配置
     */
    public static DataSourceConfig dataSourceConfig() {
        return new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setDriverName(driverName)
                .setUsername(userName)
                .setPassword(password)
                //.setSchemaName("public")
                .setUrl("jdbc:mysql://"+location+":"+port+"/"+dataBase+"?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT&allowPublicKeyRetrieval=true");
    }

    /**
     * 3、包名配置
     */
    public static PackageConfig packageConfig() {

        return new PackageConfig()
                .setModuleName(moduleName)//scanner("模块名")
                .setParent(organization)//父包名（组织com.xxxcloud）
                .setController(controller)
                .setService(service)
                .setEntity(entity)
                .setMapper(mapper);
                //.setXml(xml)
    }

    /**
     * 4、生成策略配置
     */
    public static StrategyConfig strategyConfig() {
        return new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setSuperEntityClass(BaseEntity.class) //实体继承自 // Entity的公共父类
                //.setSuperControllerClass("你自己的父类控制器,没有就不用设置!") // Controller的公共父类
                .setRestControllerStyle(true)//@RestController
                .setInclude(table)//scanner("表名，多个英文逗号分割").split(",")//生成的表名，多个用逗号隔开
                .setEntityTableFieldAnnotationEnable(true) //@TableName("xxx") @TableField("xxx")
                .setControllerMappingHyphenStyle(true)
                //.setSuperEntityColumns("id")// 写于父类中的公共字段
                //.setChainModel(true) //@Accessors(chain = true)
                .setCapitalMode(true); // 全局大写命名
                //.setTablePrefix(pc.getModuleName() + "_");// 表前缀

    }


    /**
     * 5、自定义配置
     */
    public static InjectionConfig injectionConfig(final String entityName) {

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
                Map<String, Object> map = new HashMap<>();
                map.put("Organization", organization);
                map.put("ModuleName", moduleName);
                map.put("EntityVO", entityName+"VO");
                map.put("EntityQuery", entityName+"Query");
                map.put("PackageVO", organization+"."+moduleName+"."+vo);
                map.put("PackageQuery", organization+"."+moduleName+"."+query);
                this.setMap(map);
            }
        };

        //String templatePath = "/templates/MyBaseEntity.java.ftl"; // 如果模板引擎是 freemarker
        //String templatePath = "/templates/mapper.xml.vm"; // 如果模板引擎是 velocity

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig("/templates/MyMapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                tableInfo.setEntityName(entityName);
                /*
                tableInfo.setServiceName("I" + entityName + "Service");
                tableInfo.setServiceImplName(entityName + "ServiceImpl");
                tableInfo.setControllerName(entityName + "Controller");
                tableInfo.setMapperName(entityName + "Mapper");*/
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                //return projectPath + "/src/main/resources/mapper/" + pkConfig.getModuleName()+ "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                return outPutDir + "/mapper/"+ tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                //return projectPath +"/"+  tableInfo.getEntityName() + "Mapper" + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig("/templates/MyBaseEntity.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //tableInfo.setEntityName(entityName);
                return outPutDir + "/"+organization.replace(".","/")+"/"+moduleName+"/core/"+ "BaseEntity" + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig("/templates/MyEntityVO.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return outPutDir + "/"+organization.replace(".","/")+"/"+moduleName+"/domain/vo/"+ entityName+"VO" + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig("/templates/MyEntityQuery.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //tableInfo.setEntityName(entityName);
                return outPutDir + "/"+organization.replace(".","/")+"/"+moduleName+"/domain/query/"+ entityName+"Query" + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig("/templates/MyResult.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return outPutDir + "/"+organization.replace(".","/")+"/"+moduleName+"/core/R" + StringPool.DOT_JAVA;
            }
        });

        focList.add(new FileOutConfig("/templates/MyBusinessErrorCodeEnum.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return outPutDir + "/"+organization.replace(".","/")+"/"+moduleName+"/core/BusinessErrorCodeEnum" + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig("/templates/MyBusinessException.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return outPutDir + "/"+organization.replace(".","/")+"/"+moduleName+"/core/BusinessException" + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig("/templates/MyErrorCodeEnum.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return outPutDir + "/"+organization.replace(".","/")+"/"+moduleName+"/core/ErrorCodeEnum" + StringPool.DOT_JAVA;
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
        });*/
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    /**
     * 6、自定义输出模板
     */
    public static TemplateConfig templateConfig() {
        return new TemplateConfig()
                .setEntity("templates/MyEntity.java")
                .setController("templates/MyController.java")
                .setService("templates/MyService.java")
                .setServiceImpl("templates/MyServiceImpl.java")
                .setMapper("templates/MyMapper.java")
                .setXml(null);
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
    }

}
