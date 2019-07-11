package com.huazai.test.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Description: web导出excel、word、pdf和html， 需要自定义JasperPrint中的一些数据
 *
 * @Author: 华仔
 * @Date: 2019/7/11
 */
public class WebExportUtil {

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
     * @param dataDetail 这个是数据集合，就是detail你面的数据字段，循环显示他们的
     * @return jsperPrint对象
     * @throws JRException JRException
     */
    public static JasperPrint getJasperPrint(String jasperPath, Map<String, Object> parameters, List<Map<String, Object>> dataDetail) throws JRException {
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new File(jasperPath));
        JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource((List) dataDetail);
        return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
    }

    /**
     * <p>
     * description
     *
     * @param type           导出的文件类型
     * @param jasperPrint    生成的JasperPrint对象： 通过调用该类信用的getJasperPrint方法
     * @param exprotFileName 导出的文件名称
     * @param response       HttpServletResponse
     * @throws JRException JRException
     * @throws IOException IOException
     */
    public static void export(String type, JasperPrint jasperPrint, String exprotFileName, HttpServletResponse response) throws JRException, IOException {
        switch (type) {
            case WORD:
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(exprotFileName + ".doc", "utf-8"));
                response.setContentType("application/msword;charset=utf-8");
                export(new JRDocxExporter(), jasperPrint, response);
                break;
            case EXCEL:
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(exprotFileName + ".xls", "utf-8"));
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                export(new JRXlsExporter(), jasperPrint, response);
                break;
            case PDF:
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(exprotFileName + ".pdf", "utf-8"));
                response.setContentType("application/pdf;charset=utf-8");
                export(new JRPdfExporter(), jasperPrint, response);
                break;
            case HTML:
                response.setContentType("text/html;charset=utf-8");
                export(new HtmlExporter(), jasperPrint, response);
                break;
            default:
                throw new IllegalArgumentException(String.format("无效文档类型:%s", type));
        }
    }

    private static void export(Exporter exporter, JasperPrint jasperPrint, HttpServletResponse response) throws IOException, JRException {
        try {
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            if (response.getContentType().contains("text/html")) {
                //html导出不一样
                exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getOutputStream()));
            } else {
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            }
            exporter.exportReport();
            response.getOutputStream().flush();
        } finally {
            if (!response.isCommitted() && response.getOutputStream() != null)
                response.getOutputStream().flush();
        }
    }


    public static String exportHtmlCode(JasperPrint jasperPrint) throws JRException, IOException {
        return JasperExportManager.exportReportToXml(jasperPrint);
    }
}
