# traceId透传

dubbo单次请求的traceId透传，方便业务日志追踪问题。

实现方式：dubbo spi 自定义filter + slf4j MDC。