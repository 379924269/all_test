package com.huazai.test.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 把文件导出到本地（word、excel、pdf、html）
 *
 * @Author: 华仔
 * @Date: 2019/7/11
 */
public class ExportToLocalUitl {
    private static final String WORD = "word";
    private static final String EXCEL = "excel";
    private static final String PDF = "pdf";
    private static final String HTML = "html";

    /**
     * <p>
     * 生成jsperPrint对象，就是在填充数据。生成报表的过程.jrxml ===编译===> .jasper ===填充===>.jrprint
     * ===导出===>excel、pdf、html、excel。。。。
     *
     * @param jasperPath .jsper的路径
     * @param parameters jasper 中parameters（参数对象要的值，如：$P{title}）
     * @param datailData 这个是数据集合，就是detail你面的数据，循环显示他们的
     * @return jsperPrint对象
     * @throws JRException JRException
     */
    public static JasperPrint getJasperPrint(String jasperPath, Map<String, Object> parameters, List<Map<String, Object>> datailData) throws JRException {
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new File(jasperPath));
        JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource((List) datailData);
        return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
    }

    /**
     * <p>
     * description
     *
     * @param type         导出的文件类型。如excel、word。。。。
     * @param jasperPrint  生成的JasperPrint对象。调用该类中的getJasperPrint方法生成
     * @param savePathname 生成文件路径。如：H:\000\用户信息测试.html
     * @throws JRException JRException
     * @throws IOException IOException
     */
    public static void export(String type, JasperPrint jasperPrint, String savePathname) throws JRException, IOException {
        switch (type) {
            case WORD:
                exportWord(jasperPrint, savePathname);
                break;
            case EXCEL:
                exportExcel(jasperPrint, savePathname);
                break;
            case PDF:
                JasperExportManager.exportReportToPdfFile(jasperPrint, savePathname);
                break;
            case HTML:
                JasperExportManager.exportReportToHtmlFile(jasperPrint, savePathname);
                break;
            default:
                throw new IllegalArgumentException(String.format("无效文档类型:%s", type));
        }
    }

    /**
     * <p>
     * 导出word
     *
     * @param jasperPrint 调用getJasperPrint方法生成
     * @throws JRException JRException
     */
    public static void exportWord(JasperPrint jasperPrint, String savePathname) throws JRException {
        JRDocxExporter jrDocxExporter = new JRDocxExporter();
        jrDocxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        jrDocxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(savePathname)));
        jrDocxExporter.exportReport();
    }

    /**
     * <p>
     * 导出excel
     *
     * @param jasperPrint  调用getJasperPrint方法生成
     * @param savePathname 生成文件路径
     * @throws JRException JRException
     */
    public static void exportExcel(JasperPrint jasperPrint, String savePathname) throws JRException {
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(savePathname)));
        exporter.exportReport();
    }

    public static void main(String[] args) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("code", "参评代码：sss");
        parameter.put("created", DateFormatUtils.format(System.currentTimeMillis(), DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern()));
        parameter.put("creator", "创建人：华仔");
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Map<String, Object> detailMap = new HashMap<>();
            detailMap.put("userId", i + 1);
            detailMap.put("userName", String.format("小明%d", i));
            detailMap.put("password", "1d2s5d2g5d2" + i);
            detailMap.put("mobile", "136964778" + (i > 10 ? i : String.format("0%d", i)));
            list.add(detailMap);
        }
        try {
            //测试注意修改export的type和文件名称
            ExportToLocalUitl.export(HTML, WebExportUtil.getJasperPrint("G:\\my-git-project\\all_test\\ireport_test\\src\\main\\resources\\templates\\report3.jasper", parameter, list),
                    "H:\\000\\用户信息测试.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
