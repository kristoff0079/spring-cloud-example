package org.springcloud.web1.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/apollo")
public class ApolloConfigController {
    @ApolloConfig
    private Config config;

    @GetMapping("showConfig")
    public Map<String, String> showApolloConfig() {
        HashMap<String, String> propMap = new HashMap<>();
        Set<String> set = config.getPropertyNames();
        for (String key : set) {
            String value = config.getProperty(key, null);
            propMap.put(key, value);
        }
        return propMap;
    }
}
