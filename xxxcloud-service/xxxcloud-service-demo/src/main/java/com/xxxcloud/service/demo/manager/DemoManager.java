package com.xxxcloud.service.demo.manager;

/**
 * Manager 层：
 * 通用业务处理层，它有如下特征：
 *  1）对第三方平台封装的层，预处理返回结果及转化异常信息；
 *  2）对 Service 层通用能力的下沉，如缓存方案、中间件通用处理；
 *  3）与 DAO 层交互，对多个 DAO 的组合复用。
 *
 *
 *  (在开发中，我们经常会遇到 AService 中的某个业务可以提供给 BService 调用，从而让 BService 调用 AService 的方法，认为是 Service 之间具有共同的业务。
 *  其实 Service 之间没有共同的业务，而是具备通用的逻辑，这时应该将其抽离出来放在 Manager 中。)
 *
 * @author LinHongli
 */
public class DemoManager {

}
