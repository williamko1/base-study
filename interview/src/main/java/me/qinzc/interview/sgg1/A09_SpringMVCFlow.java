package me.qinzc.interview.sgg1;

/**
 * desc : springMVC处理流程
 *
 *
 * 1. 发送请求 -> DispatcherServlet
 * 2. 调用处理器映射器找到处理器 getHandler(request) -> HandlerMapping 遍历
 * 3. 返回HandlerExecutionChain (包含处理器拦截器HandlerInterceptor,Handler处理器对象）-> DispatcherServlet
 * 4. 通过处理器找到处理器适配器handlerAdapter getHandlerAdapter(mappedHandler.getHandler)
 * 5. 通过处理器适配器HandlerAdapter调用具体的处理器Handler(Controller) ha.handler() 返回ModelAndView -> DispatcherServlet
 * 6. 返回ModelAndView给DispatcherServlet
 * 7. 视图解析 ViewResolver DispatcherServlet.render.resolveViewName
 * 8. 返回view -> DispatcherServlet
 * 9. 渲染视图 view.render
 * 10. 响应用户
 * @author Zane Qin
 * creatTime : 15:57 2023/3/14
 * modifier:
 * modifyTime:
 */
public class A09_SpringMVCFlow {
}
