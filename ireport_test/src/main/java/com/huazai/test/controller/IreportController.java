package com.huazai.test.controller;

import com.huazai.test.util.WebExportUtil;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 华仔
 * @date 2018/7/3 15:52
 */
@RestController
@RequestMapping("/ireport")
public class IreportController {

    @RequestMapping("")
    public void exprot(HttpServletResponse response, @RequestParam String type) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("code", "参评代码：12352");
        parameter.put("created", DateFormatUtils.format(System.currentTimeMillis(), DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern()));
        parameter.put("creator", "创建人：华仔");
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Map<String, Object> detailMap = new HashMap<>();
            detailMap.put("userId", i);
            detailMap.put("userName", String.format("小明%d", i));
            detailMap.put("password", "1d2s5d2g5d2" + i);
            detailMap.put("mobile", "136964778" + (i > 10 ? i : String.format("0%d", i)));
            list.add(detailMap);
        }
        try {
            WebExportUtil.export(type, WebExportUtil.getJasperPrint("G:\\my-git-project\\all_test\\ireport_test\\src\\main\\resources\\templates\\report3.jasper", parameter, list),
                    "用户信息测试", response);
        } catch (JRException | IOException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    @RequestMapping("/report1")
    public void exprot1(HttpServletResponse response, @RequestParam String type) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("startTime", "2019");
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("userId", 11111111);
        detailMap.put("username", "55555555555555555555555555555555555555555555555555555555555555555555");
        detailMap.put("password", "1d2s5d2g5d2");
        list.add(detailMap);

        for (int i = 0; i < 100; i++) {
             detailMap = new HashMap<>();
            detailMap.put("userId", i);
            detailMap.put("username", String.format("小明%d", i));
            detailMap.put("password", "1d2s5d2g5d2" + i);
            list.add(detailMap);
        }

        try {
            WebExportUtil.export(type, WebExportUtil.getJasperPrint("G:\\my-git-project\\all_test\\ireport_test\\src\\main\\resources\\templates\\report2.jasper", parameter, list),
                    "用户信息测试", response);
        } catch (JRException | IOException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }
}
