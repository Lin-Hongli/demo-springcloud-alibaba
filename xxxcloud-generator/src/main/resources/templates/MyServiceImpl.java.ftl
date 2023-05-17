package ${package.ServiceImpl};

import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!}ServiceImpl
 * </p>
 *
 * @author ${author}
 * @since ${.now}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

}
</#if>
