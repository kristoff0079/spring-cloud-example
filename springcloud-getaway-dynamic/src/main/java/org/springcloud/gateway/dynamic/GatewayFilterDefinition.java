package org.springcloud.gateway.dynamic;

import java.util.LinkedHashMap;
import java.util.Map;

public class GatewayFilterDefinition {
    // 断言对应的Name
    private String name;

    // 对应的路由规则
    private Map<String, String> args = new LinkedHashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
    }
}
