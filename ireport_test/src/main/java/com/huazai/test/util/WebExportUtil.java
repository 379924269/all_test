package com.huazai.test.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class WebExportUtil {

    public static final String WORD = "word";
    public static final String EXCEL = "excel";
    public static final String PDF = "pdf";
    public static final String HTML = "html";

    /**
     * <p>
     * 生成jsperPrint对象，就是在填充数据。生成报表的过程.jrxml ===编译===> .jasper ===填充===>.jrprint
     * ===导出===>excel、pdf、html、excel。。。。
     *
     * @param jasperPath .jsper的路径
     * @param parameters jasper 中parameters（参数对象要的值，如：$P{title}）
     * @param dataDetail 这个是数据集合，就是detail你面的数据，循环显示他们的
     * @return jsperPrint对象
     * @throws JRException
     */
    public static JasperPrint getJasperPrint(String jasperPath, Map<String, Object> parameters, List<Map<String, Object>> dataDetail) throws JRException {
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new File(jasperPath));
        @SuppressWarnings({"unchecked", "rawtypes"})
        JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource((List) dataDetail);
        return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
    }

    /**
     * <p>
     * description
     *
     * @param type           导出的文件类型
     * @param jasperPrint    生成的JasperPrint对象
     * @param exprotFileName 导出的文件名称
     * @param response
     * @throws JRException
     * @throws IOException
     */
    public static void export(String type, JasperPrint jasperPrint, String exprotFileName, HttpServletResponse response) throws JRException, IOException {
        if (WORD.equals(type)) {

            exprotFileName = exprotFileName + ".doc";
            exportWord(jasperPrint, exprotFileName, response);
        } else if (EXCEL.equals(type)) {
            exprotFileName = exprotFileName + ".xls";
            exportExcel(jasperPrint, exprotFileName, response);
        } else if (PDF.equals(type)) {
            exprotFileName = exprotFileName + ".pdf";
            exportPdf(jasperPrint, exprotFileName, response);
        } else {
            if (HTML.equals(type))
                exportHtml(jasperPrint, response);
            else
                throw new IllegalArgumentException("invalid parameter!");
        }
    }

    public static void exportWord(JasperPrint jasperPrint, String fileName, HttpServletResponse response) throws JRException, IOException {
        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            response.setContentType("application/msword;charset=utf-8");
            JRDocxExporter jrDocxExporter = new JRDocxExporter();
            jrDocxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            jrDocxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            jrDocxExporter.exportReport();
            response.getOutputStream().flush();
        } finally {
            if (!response.isCommitted() && response.getOutputStream() != null)
                response.getOutputStream().flush();
        }
    }

    public static void exportExcel(JasperPrint jasperPrint, String fileName, HttpServletResponse response) throws JRException, IOException {
        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            exporter.exportReport();
            response.getOutputStream().flush();
        } finally {
            if (!response.isCommitted() && response.getOutputStream() != null)
                response.getOutputStream().flush();
        }
    }

    public static void exportPdf(JasperPrint jasperPrint, String fileName, HttpServletResponse response) throws JRException, IOException {
        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            response.setContentType("application/pdf;charset=utf-8");
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            JRPdfExporter jrPdfExporter = new JRPdfExporter();
            jrPdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            jrPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            jrPdfExporter.exportReport();
            response.getOutputStream().flush();
        } finally {
            if (!response.isCommitted() && response.getOutputStream() != null)
                response.getOutputStream().flush();
        }
    }

    public static void exportHtml(JasperPrint jasperPrint, HttpServletResponse response) throws JRException, IOException {
        try {
            response.setContentType("text/html;charset=utf-8");
            HtmlExporter htmlExporter = new HtmlExporter();
            htmlExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getOutputStream(), "UTF-8"));
            htmlExporter.exportReport();
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
