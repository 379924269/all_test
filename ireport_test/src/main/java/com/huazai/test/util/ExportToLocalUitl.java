package com.huazai.test.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExportToLocalUitl {

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
     * @param datailData 这个是数据集合，就是detail你面的数据，循环显示他们的
     * @return jsperPrint对象
     * @throws JRException
     */
    public static JasperPrint getJasperPrint(String jasperPath, Map<String, Object> parameters, List<Map<String, Object>> datailData) throws JRException {
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new File(jasperPath));
        @SuppressWarnings({"unchecked", "rawtypes"})
        JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource((List) datailData);
        return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
    }

    /**
     * <p>
     * description
     *
     * @param type         导出的文件类型
     * @param jasperPrint  生成的JasperPrint对象
     * @param savePathname 存放文件的位置
     * @throws JRException
     * @throws IOException
     */
    public static void export(String type, JasperPrint jasperPrint, String savePathname) throws JRException, IOException {
        if (WORD.equals(type))
            exportWord(jasperPrint, savePathname);
        else if (EXCEL.equals(type))
            exportExcel(jasperPrint, savePathname);
        else if (PDF.equals(type))
            exportPdf(jasperPrint, savePathname);
        else if (HTML.equals(type))
            exportHtml(jasperPrint, savePathname);
        else
            throw new IllegalArgumentException("invalid parameter!");
    }

    /**
     * <p>
     * 导出word
     *
     * @param jasperPrint
     * @throws JRException
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
     * @param jasperPrint
     * @param savePathname
     * @throws JRException
     */
    public static void exportExcel(JasperPrint jasperPrint, String savePathname) throws JRException {
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(savePathname)));
        exporter.exportReport();
    }

    /**
     * <p>
     * 导出pdf
     *
     * @param jasperPrint
     * @param savePathname
     * @throws JRException
     */
    public static void exportPdf(JasperPrint jasperPrint, String savePathname) throws JRException {
        JasperExportManager.exportReportToPdfFile(jasperPrint, savePathname);
    }

    /**
     * <p>
     * 导出html
     *
     * @param jasperPrint
     * @param savePathname
     * @throws JRException
     */
    public static void exportHtml(JasperPrint jasperPrint, String savePathname) throws JRException {
        HtmlExporter htmlExporter = new HtmlExporter();
        htmlExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(new File(savePathname), "UTF-8"));
        htmlExporter.exportReport();
    }

    /**
     * <p>
     * 导出html代码字符串
     *
     * @param jasperPrint
     * @return
     * @throws JRException
     */
    public static String exportHtmlCode(JasperPrint jasperPrint) throws JRException {
        return JasperExportManager.exportReportToXml(jasperPrint);
    }
}
