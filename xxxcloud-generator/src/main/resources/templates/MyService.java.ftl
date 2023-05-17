package ${package.Service};

import ${superServiceClassPackage};

/**
 * <p>
 * ${table.comment!}Service
 * </p>
 *
 * @author ${author}
 * @since ${.now}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

}
</#if>
