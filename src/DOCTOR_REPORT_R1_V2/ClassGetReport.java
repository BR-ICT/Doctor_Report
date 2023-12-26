/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOCTOR_REPORT_R1_V2;

/**
 *
 * @author aticha /* To change this license header, choose License Headers in
 * Project Properties. To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static DOCTOR_REPORT_R1_V2.ReportDoctor.year;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.Document;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.PrintPart;
import net.sf.jasperreports.engine.base.JRBasePrintPage;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author aticha
 */
public class ClassGetReport {

    JRPdfExporter exporter = new JRPdfExporter();
    List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();

    public String GetDoctorRunningNo(String OAORNO) {
        try {

            String RunningNo = "";
            Connection connect = ConnectDB2.ConnectionDB();
            Statement sta = connect.createStatement();
            String Sql = " SELECT * ";
            Sql += "       FROM BRLDTA0100.PRODOCTORAUTO   ";
            Sql += "       WHERE UBORNO='" + OAORNO + "'";

            ResultSet rs1 = sta.executeQuery(Sql);

            if (rs1.next()) {
                RunningNo = rs1.getString("NODOCUMENT");
            }

            if (RunningNo.trim() != "") {
                return RunningNo.trim();
            } else {
                Sql = " INSERT INTO BRLDTA0100.PRODOCTORAUTO (UBORNO,NODOCUMENT,YEARDOCTOR,ESTCOMPANY,RUNNINGNO,DATERUNREPORT ) ";
                Sql += "      SELECT h.UAORNO,d.ESTCOMPANY ||'/'|| d.YEARDOCTOR ||'/'|| RIGHT(dd.RUNNINGNO,6) AS   NODOCUMENT ";
                Sql += "        ,d.YEARDOCTOR,d.ESTCOMPANY ,RIGHT(dd.RUNNINGNO,6),'" + GetDateDecmalCurrenttime() + "'    ";
                Sql += "       FROM M3FDBPRD.ODHEAD AS h ";
                Sql += "       LEFT JOIN BRLDTA0100.PRODOCTOREST14 AS d ON h.UAIVDT BETWEEN d.STARTDATE AND d.ENDDATE ";
                Sql += "       LEFT JOIN (SELECT CASE WHEN  MAX(runningno+1)<100000 THEN '00000'|| MAX(runningno+1) ";
                Sql += "         ELSE CAST(MAX(runningno+1)AS CHAR(100) )  END AS RUNNINGNO ,YEARDOCTOR       FROM BRLDTA0100.PRODOCTORAUTO  ";
                Sql += "        WHERE   ESTCOMPANY=14 GROUP BY YEARDOCTOR ) AS dd  ON dd.yeardoctor=d.YEARDOCTOR     ";
                Sql += "       WHERE h.UAORNO='" + OAORNO + "' AND h.UACONO=10 ";
                Sql += "  GROUP BY  h.UAORNO,d.ESTCOMPANY , d.YEARDOCTOR ,dd.RUNNINGNO";
                //  Sql += "        LIMIT 0,1 ";
                try {
                    PreparedStatement UpdatePrvNo = connect.prepareStatement(Sql);
                    UpdatePrvNo.executeUpdate(Sql);
                } catch (Exception e) {
                }

                return "COMPLETE";

            }

        } catch (Exception e) {
            return "";
        }

    }

    public String GetDateDecmalCurrenttime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        String formatted = format1.format(cal.getTime());
        return formatted;
    }

    public void GetReportDoctorSummary(String Years, String Month) {
        // TODO add your handling code here:
        String PathFile = System.getProperty("user.dir").toString() + "\\Report\\";
        String name = PathFile + "rptSummaryDoctorMonth.jasper";
        String name2 = PathFile + "rptSummaryDoctorMonthlocal.jasper";

        try {

            Connection conn = ConnectDB2.ConnectionDB();

            Map parameterss2 = new HashMap();
            parameterss2.put("PMonthNumber", Month);
            parameterss2.put("PYearNumber", Years);
            parameterss2.put("PMonthName", GetMonthName(Month));
            JasperPrint print2 = JasperFillManager.fillReport(name, parameterss2, conn);
            JasperPrint print3 = JasperFillManager.fillReport(name2, parameterss2, conn);

            JasperViewer view2 = new JasperViewer(print2, false);
            JasperViewer view3 = new JasperViewer(print3, false);
            view2.setVisible(true);
            view3.setVisible(true);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void GetReportDoctorSummaryExcel(String Years, String Month) {
        // TODO add your handling code here:
        String PathFile = System.getProperty("user.dir").toString() + "\\Report\\";
//        String name = PathFile + "SummaryDoctorMonthExcell.jasper";
//        String name = PathFile + "rptSummaryDoctorMonthXLSX.jasper";
        String name2 = PathFile + "rptSummaryDoctorMonthXLSXLocal.jasper";
//        String name3 = PathFile + "rptSummaryDoctorMonthXLSX_farmpra.jasper";

        try {

            Connection conn = ConnectDB2.ConnectionDB();

            Map parameterss2 = new HashMap();
            parameterss2.put("PMonthNumber", Month);
            parameterss2.put("PYearNumber", Years);
            parameterss2.put("PMonthName", GetMonthName(Month));
//            JasperPrint print2 = JasperFillManager.fillReport(name, parameterss2, conn);
            JasperPrint print3 = JasperFillManager.fillReport(name2, parameterss2, conn);
//           JasperPrint print4 = JasperFillManager.fillReport(name3, parameterss2, conn);

//            JasperViewer view2 = new JasperViewer(print2, false);
            JasperViewer view3 = new JasperViewer(print3, false);
//            JasperViewer view4 = new JasperViewer(print4, false);
//            view2.setVisible(true);
            view3.setVisible(true);
//            view4.setVisible(true);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String GetMonthName(String MonthNo) {

        if (MonthNo.equals("01")) {
            return "มกราคม ";
        }
        if (MonthNo.equals("02")) {
            return "กุมพาพันธ์ ";
        }
        if (MonthNo.equals("03")) {
            return "มีนาคม ";
        }
        if (MonthNo.equals("04")) {
            return "เมษายน ";
        }
        if (MonthNo.equals("05")) {
            return "พฤษภาคม ";
        }
        if (MonthNo.equals("06")) {
            return "มิถุนายน ";
        }
        if (MonthNo.equals("07")) {
            return "กรกฎาคม ";
        }
        if (MonthNo.equals("08")) {
            return "สิงหาคม ";
        }
        if (MonthNo.equals("09")) {
            return "กันยายน ";
        }
        if (MonthNo.equals("10")) {
            return "ตุลาคม ";
        }
        if (MonthNo.equals("11")) {
            return "พฤศจิกายน ";
        }
        if (MonthNo.equals("12")) {
            return "ธันวาคม ";
        }

        return "";

    }

    public void GetReportDoctor(String GRNNUMBER) {
        // TODO add your handling code here:
        File myFile = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\doctorreport.pdf");
        String PathFile = System.getProperty("user.dir").toString() + "\\Report\\";
        String name = PathFile + "rptCerDoctorHead.jasper";

        try {
            String sumThaiBaht = "";
            Connection connect = ConnectDB2.ConnectionDB();
            Statement sta = connect.createStatement();
            String Sql = " SELECT B.UBORNO,A.UAIVNO,A.UACUNO,E.OKCUNM,RTRIM(E.OKCUA1)||' '||RTRIM(E.OKCUA2)||' '||RTRIM(E.OKCUA3) ADDR,SUM(C.MTTRQT*-1) MTTRQT   ";
            Sql += " FROM M3FDBPRD.ODHEAD A,M3FDBPRD.ODLINE B,M3FDBPRD.MITTRA C,M3FDBPRD.MITMAS D, M3FDBPRD.OCUSMA E  ";
            Sql += " WHERE B.UBORNO = C.MTRIDN AND B.UBCONO = C.MTCONO AND C.MTTTYP = 31  AND B.UBITNO = C.MTITNO ";
            Sql += " AND A.UAORNO = B.UBORNO AND A.UACONO = B.UBCONO AND A.UACONO = 10  AND B.UBORNO =" + GRNNUMBER;
            Sql += " AND C.MTCONO = D.MMCONO  AND C.MTITNO = D.MMITNO AND D.MMITGR BETWEEN 'SH01' AND 'SH07' ";
            Sql += " AND A.UACONO = E.OKCONO AND A.UACUNO = E.OKCUNO ";
            Sql += " GROUP BY B.UBORNO,A.UAIVNO,A.UACUNO,E.OKCUNM,RTRIM(E.OKCUA1)||' '||RTRIM(E.OKCUA2)||' '||RTRIM(E.OKCUA3) ";

            ResultSet rs1 = sta.executeQuery(Sql);

            if (rs1.next()) {
                sumThaiBaht = ThaiBaht(rs1.getString("MTTRQT"));
            }

            Connection conn = ConnectDB2.ConnectionDB();
            //JRResultSetDataSource resultSetDataSource = new   JRResultSetDataSource(Rs1);

            /////////////////////////////////////////////////////////ORIGINAL//////////////////////////////////////////////////
            Map parameterss = new HashMap();
            parameterss.put("ThaiBaht", sumThaiBaht);
            parameterss.put("POAORNO", GRNNUMBER);

            JasperPrint print = JasperFillManager.fillReport(name, parameterss, conn);

            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
            /////////////////////////////////////////////////////////ORIGINAL//////////////////////////////////////////////////
            /////////////////////////////////////////////////////////COPY 1//////////////////////////////////////////////////
            String name2 = PathFile + "rptCerDoctorDetail.jasper";
            Map parameterss2 = new HashMap();
            //  parameterss2.put("ThaiBaht",sumThaiBaht);
            parameterss2.put("POAORNO", GRNNUMBER);

            JasperPrint print2 = JasperFillManager.fillReport(name2, parameterss2, conn);

            JasperViewer view2 = new JasperViewer(print2, false);
            view2.setVisible(true);
            /////////////////////////////////////////////////////////COPY1//////////////////////////////////////////////////
//export เป็น pdf for get report doctor
//            jasperPrints.add(print);
//            jasperPrints.add(print2);
//            exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrints));
//            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(myFile));
//            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
//            configuration.setPermissions(PdfWriter.AllowCopy | PdfWriter.AllowPrinting);
//            exporter.setConfiguration(configuration);
//            exporter.exportReport();
            //เปิดไฟล์เมื่อ render เสร็จ
//            if (Desktop.isDesktopSupported()) {
//                try {
//                    Desktop.getDesktop().open(myFile);
//                    System.out.println(myFile);
//                } catch (Exception e) {
//
//                    JOptionPane.showMessageDialog(null, "Close the PDF file first!");
//                    // no application registered for PDFs
//                }
//            }
        } catch (Exception e) {
            System.out.println("e");
        }
    }

    public void GetReportDoctorheadbigC(String GRNNUMBER) {
        // TODO add your handling code here:
        File myFile = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\doctorheadBigC.pdf");
        String PathFile = System.getProperty("user.dir").toString() + "\\Report\\";
        String name = PathFile + "rptCerDoctorHeadbigc.jasper";

        try {
            String sumThaiBaht = "";
            Connection connect = ConnectDB2.ConnectionDB();
            Statement sta = connect.createStatement();
            String Sql = " SELECT B.UBORNO,A.UAIVNO,A.UACUNO,E.OKCUNM,RTRIM(E.OKCUA1)||' '||RTRIM(E.OKCUA2)||' '||RTRIM(E.OKCUA3) ADDR,SUM(C.MTTRQT*-1) MTTRQT   ";
            Sql += " FROM M3FDBPRD.ODHEAD A,M3FDBPRD.ODLINE B,M3FDBPRD.MITTRA C,M3FDBPRD.MITMAS D, M3FDBPRD.OCUSMA E  ";
            Sql += " WHERE B.UBORNO = C.MTRIDN AND B.UBCONO = C.MTCONO AND C.MTTTYP = 31  AND B.UBITNO = C.MTITNO ";
            Sql += " AND A.UAORNO = B.UBORNO AND A.UACONO = B.UBCONO AND A.UACONO = 10  AND B.UBORNO =" + GRNNUMBER;
            Sql += " AND C.MTCONO = D.MMCONO  AND C.MTITNO = D.MMITNO AND D.MMITGR BETWEEN 'SH01' AND 'SH07' ";
            Sql += " AND A.UACONO = E.OKCONO AND A.UACUNO = E.OKCUNO ";
            Sql += " GROUP BY B.UBORNO,A.UAIVNO,A.UACUNO,E.OKCUNM,RTRIM(E.OKCUA1)||' '||RTRIM(E.OKCUA2)||' '||RTRIM(E.OKCUA3) ";

            ResultSet rs1 = sta.executeQuery(Sql);

            if (rs1.next()) {
                sumThaiBaht = ThaiBaht(rs1.getString("MTTRQT"));
            }

            Connection conn = ConnectDB2.ConnectionDB();
            //JRResultSetDataSource resultSetDataSource = new   JRResultSetDataSource(Rs1);

            /////////////////////////////////////////////////////////ORIGINAL//////////////////////////////////////////////////
            Map parameterss = new HashMap();
            parameterss.put("ThaiBaht", sumThaiBaht);
            parameterss.put("POAORNO", GRNNUMBER);

            JasperPrint print = JasperFillManager.fillReport(name, parameterss, conn);

            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
            /////////////////////////////////////////////////////////ORIGINAL//////////////////////////////////////////////////
            /////////////////////////////////////////////////////////COPY 1//////////////////////////////////////////////////
            String name2 = PathFile + "rptCerDoctorDetail.jasper";
            Map parameterss2 = new HashMap();
            //  parameterss2.put("ThaiBaht",sumThaiBaht);
            parameterss2.put("POAORNO", GRNNUMBER);

            JasperPrint print2 = JasperFillManager.fillReport(name2, parameterss2, conn);

            JasperViewer view2 = new JasperViewer(print2, false);
            view2.setVisible(true);
            /////////////////////////////////////////////////////////COPY1//////////////////////////////////////////////////
//PDF Export process here for doctor report
//            jasperPrints.add(print);
//            jasperPrints.add(print2);
//            exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrints));
//            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(myFile));
//            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
//            configuration.setPermissions(PdfWriter.AllowCopy | PdfWriter.AllowPrinting);
//            exporter.setConfiguration(configuration);
//            exporter.exportReport();
//            //เปิดไฟล์เมื่อ render เสร็จ
//            if (Desktop.isDesktopSupported()) {
//                try {
//                    Desktop.getDesktop().open(myFile);
//                    System.out.println(myFile);
//                } catch (Exception e) {
//
//                    JOptionPane.showMessageDialog(null, "Close the PDF file first!");
//                    // no application registered for PDFs
//                }
//            }

        } catch (Exception e) {
            System.out.println("e");
        }
    }

    public void GetReportDoctorV2(String GRNNUMBER) {
        // TODO add your handling code here:
        String PathFile = System.getProperty("user.dir").toString() + "\\Report\\";
        String name = PathFile + "rptCerDoctorHeaddatenow2.jasper";
        System.out.println("test");
        try {
            String sumThaiBaht = "";

            Connection connect = ConnectDB2.ConnectionDB();
            Statement sta = connect.createStatement();
            String Sql = " SELECT B.UBORNO,A.UAIVNO,A.UACUNO,E.OKCUNM,RTRIM(E.OKCUA1)||' '||RTRIM(E.OKCUA2)||' '||RTRIM(E.OKCUA3) ADDR,SUM(C.MTTRQT*-1) MTTRQT   ";
            Sql += " FROM M3FDBPRD.ODHEAD A,M3FDBPRD.ODLINE B,M3FDBPRD.MITTRA C,M3FDBPRD.MITMAS D, M3FDBPRD.OCUSMA E  ";
            Sql += " WHERE B.UBORNO = C.MTRIDN AND B.UBCONO = C.MTCONO AND C.MTTTYP = 31  AND B.UBITNO = C.MTITNO ";
            Sql += " AND A.UAORNO = B.UBORNO AND A.UACONO = B.UBCONO AND A.UACONO = 10  AND B.UBORNO =" + GRNNUMBER;
            Sql += " AND C.MTCONO = D.MMCONO  AND C.MTITNO = D.MMITNO AND D.MMITGR BETWEEN 'SH01' AND 'SH07' ";
            Sql += " AND A.UACONO = E.OKCONO AND A.UACUNO = E.OKCUNO ";
            Sql += " GROUP BY B.UBORNO,A.UAIVNO,A.UACUNO,E.OKCUNM,RTRIM(E.OKCUA1)||' '||RTRIM(E.OKCUA2)||' '||RTRIM(E.OKCUA3) ";

            ResultSet rs1 = sta.executeQuery(Sql);

            if (rs1.next()) {
                sumThaiBaht = ThaiBaht(rs1.getString("MTTRQT"));

            }

            Connection conn = ConnectDB2.ConnectionDB();
            //JRResultSetDataSource resultSetDataSource = new   JRResultSetDataSource(Rs1);

            /////////////////////////////////////////////////////////ORIGINAL//////////////////////////////////////////////////
            Map parameterss = new HashMap();
            parameterss.put("ThaiBaht", sumThaiBaht);
            parameterss.put("POAORNO", GRNNUMBER);
            parameterss.put("year", year);
            JasperPrint print = JasperFillManager.fillReport(name, parameterss, conn);

            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
            /////////////////////////////////////////////////////////ORIGINAL//////////////////////////////////////////////////

            /////////////////////////////////////////////////////////COPY 1//////////////////////////////////////////////////
            String name2 = PathFile + "rptCerDoctorDetaidatenowl.jasper";
            Map parameterss2 = new HashMap();
            //  parameterss2.put("ThaiBaht",sumThaiBaht);
            parameterss2.put("POAORNO", GRNNUMBER);
            parameterss2.put("year", year);
            JasperPrint print2 = JasperFillManager.fillReport(name2, parameterss2, conn);

            JasperViewer view2 = new JasperViewer(print2, false);
            view2.setVisible(true);
            /////////////////////////////////////////////////////////COPY1//////////////////////////////////////////////////

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void GetReportDoctorLocal(String GRNNUMBER, String subYear, boolean lastrow) {
        // TODO add your handling code here:
        File myFile = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\duckhealthcheckup.pdf");

        String PathFile = System.getProperty("user.dir").toString() + "\\Report\\";
        String name = PathFile + "rptCerDoctorHeadLocal.jasper";

        try {
            String sumThaiBaht = "";

            Connection connect = ConnectDB2.ConnectionDB();
            Statement sta = connect.createStatement();
            String Sql = " SELECT B.UBORNO,A.UAIVNO,A.UACUNO,E.OKCUNM,RTRIM(E.OKCUA1)||' '||RTRIM(E.OKCUA2)||' '||RTRIM(E.OKCUA3) ADDR,SUM(C.MTTRQT*-1) MTTRQT   ";
            Sql += " FROM M3FDBPRD.ODHEAD A,M3FDBPRD.ODLINE B,M3FDBPRD.MITTRA C,M3FDBPRD.MITMAS D, M3FDBPRD.OCUSMA E  ";
            Sql += " WHERE B.UBORNO = C.MTRIDN AND B.UBCONO = C.MTCONO AND C.MTTTYP = 31  AND B.UBITNO = C.MTITNO ";
            Sql += " AND A.UAORNO = B.UBORNO AND A.UACONO = B.UBCONO AND A.UACONO = 10  AND B.UBORNO =" + GRNNUMBER.toString();
            Sql += " AND C.MTCONO = D.MMCONO  AND C.MTITNO = D.MMITNO AND D.MMITGR BETWEEN 'SH01' AND 'SH07' ";
            Sql += " AND A.UACONO = E.OKCONO AND A.UACUNO = E.OKCUNO ";
            Sql += " GROUP BY B.UBORNO,A.UAIVNO,A.UACUNO,E.OKCUNM,RTRIM(E.OKCUA1)||' '||RTRIM(E.OKCUA2)||' '||RTRIM(E.OKCUA3) ";

            ResultSet rs1 = sta.executeQuery(Sql);

            if (rs1.next()) {
                sumThaiBaht = ThaiBaht(rs1.getString("MTTRQT"));

            }

            Connection conn = ConnectDB2.ConnectionDB();
            //JRResultSetDataSource resultSetDataSource = new   JRResultSetDataSource(Rs1);

            /////////////////////////////////////////////////////////ORIGINAL//////////////////////////////////////////////////
            Map parameterss = new HashMap();
            parameterss.put("ThaiBaht", sumThaiBaht);
            parameterss.put("POAORNO", GRNNUMBER);
            parameterss.put("year", subYear);
            JasperPrint print = JasperFillManager.fillReport(name, parameterss, conn);

            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
            /////////////////////////////////////////////////////////ORIGINAL//////////////////////////////////////////////////
            /////////////////////////////////////////////////////////COPY 1//////////////////////////////////////////////////
            String name2 = PathFile + "rptCerDoctorDetaiLocal.jasper";
            Map parameterss2 = new HashMap();
//  parameterss2.put("ThaiBaht",sumThaiBaht);
            parameterss2.put("POAORNO", GRNNUMBER);
            parameterss2.put("year", subYear);

            JasperPrint print2 = JasperFillManager.fillReport(name2, parameterss2, conn);
            JasperViewer view2 = new JasperViewer(print2, false);
            view2.setVisible(true);

//           List pages = print2.getPages();
//              JRPrintPage objectpage = (JRPrintPage) pages.get(0);
//              System.out.println(objectpage);
//              print3.addPage((JRPrintPage)pages.get(0));
//              System.out.println(print3);
//           JasperViewer view3 = new JasperViewer(print3, false);
//           System.out.println(view3);
//           System.out.println(view2);
//           view3.setVisible(true);   
//            JasperViewer view3 = new JasperViewer(print2 , false);
//            view3.add(view, print2, year);
//            view3.setVisible(true);
//            JRPrintPage printall;
//            printall.
//            System.out.println(printall.getPages());
//
//
//           JasperViewer view3 = new JasperViewer(printall,false);
//           view3.setVisible(true);
            //printall.addPage(page2);
            //exportไฟล์เป็น pdf
            jasperPrints.add(print);
            jasperPrints.add(print2);
            exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrints));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(myFile));
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            configuration.setPermissions(PdfWriter.AllowCopy | PdfWriter.AllowPrinting);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            //เปิดไฟล์เมื่อ render เสร็จ
//            if (lastrow) {
//                if (Desktop.isDesktopSupported()) {
//                    try {
//                        Desktop.getDesktop().open(myFile);
//                        System.out.println(myFile);
//                    } catch (Exception e) {
//
//                        JOptionPane.showMessageDialog(null, e);
//                        // no application registered for PDFs
//                    }
//                }
//            }

//            //TESTING
//            try {
//                Runtime.getRuntime().exec(Path);
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "Check file detail");
//            }
//
//            System.out.println(System.getProperty("user.dir").toString() + "\\Report.pdf");
            /////////////////////////////////////////////////////////COPY1//////////////////////////////////////////////////
        } catch (Exception e) {
            System.out.println("err: " + e);
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Kokotest(String Program) {
        // TODO add your handling code here:
        String PathFile = System.getProperty("user.dir").toString() + "\\Report\\";

        String name = PathFile + "rptCerDoctorDetail.jasper";
        try {

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void GetReportDoctorBigc(String GRNNUMBER) {
        // TODO add your handling code here:
        String PathFile = System.getProperty("user.dir").toString() + "\\Report\\";
        String name = PathFile + "rptCerDoctorBigC.jasper";

        try {
            String sumThaiBaht = "";

            Connection connect = ConnectDB2.ConnectionDB();
            Statement sta = connect.createStatement();
            String Sql = " SELECT B.UBORNO,A.UAIVNO,A.UACUNO,E.OKCUNM,RTRIM(E.OKCUA1)||' '||RTRIM(E.OKCUA2)||' '||RTRIM(E.OKCUA3) ADDR,SUM(C.MTTRQT*-1) MTTRQT   ";
            Sql += " FROM M3FDBPRD.ODHEAD A,M3FDBPRD.ODLINE B,M3FDBPRD.MITTRA C,M3FDBPRD.MITMAS D, M3FDBPRD.OCUSMA E  ";
            Sql += " WHERE B.UBORNO = C.MTRIDN AND B.UBCONO = C.MTCONO AND C.MTTTYP = 31  AND B.UBITNO = C.MTITNO ";
            Sql += " AND A.UAORNO = B.UBORNO AND A.UACONO = B.UBCONO AND A.UACONO = 10  AND B.UBORNO =" + GRNNUMBER;
            Sql += " AND C.MTCONO = D.MMCONO  AND C.MTITNO = D.MMITNO AND D.MMITGR BETWEEN 'SH01' AND 'SH07' ";
            Sql += " AND A.UACONO = E.OKCONO AND A.UACUNO = E.OKCUNO ";
            Sql += " GROUP BY B.UBORNO,A.UAIVNO,A.UACUNO,E.OKCUNM,RTRIM(E.OKCUA1)||' '||RTRIM(E.OKCUA2)||' '||RTRIM(E.OKCUA3) ";

            ResultSet rs1 = sta.executeQuery(Sql);

            if (rs1.next()) {
                sumThaiBaht = ThaiBaht(rs1.getString("MTTRQT"));
            }

            Connection conn = ConnectDB2.ConnectionDB();
            //JRResultSetDataSource resultSetDataSource = new   JRResultSetDataSource(Rs1);

            /////////////////////////////////////////////////////////ORIGINAL//////////////////////////////////////////////////
            Map parameterss = new HashMap();
            parameterss.put("ThaiBaht", sumThaiBaht);
            parameterss.put("POAORNO", GRNNUMBER);
            parameterss.put("year", year);
            JasperPrint print = JasperFillManager.fillReport(name, parameterss, conn);

            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
            /////////////////////////////////////////////////////////ORIGINAL//////////////////////////////////////////////////

            /////////////////////////////////////////////////////////COPY 1//////////////////////////////////////////////////
            String name2 = PathFile + "rptCerDoctorDetaidatenowl.jasper";
            Map parameterss2 = new HashMap();
            //  parameterss2.put("ThaiBaht",sumThaiBaht);
            parameterss2.put("POAORNO", GRNNUMBER);
            parameterss2.put("year", year);
            JasperPrint print2 = JasperFillManager.fillReport(name2, parameterss2, conn);

            JasperViewer view2 = new JasperViewer(print2, false);
            view2.setVisible(true);
            /////////////////////////////////////////////////////////COPY1//////////////////////////////////////////////////

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String ThaiBaht(String txt) {

        String bahtTxt, n, bahtTH = "";
        Double amount;
        try {
            amount = Double.parseDouble(txt);
        } catch (NumberFormatException ex) {
            amount = 0.00;
        }

        try {
            DecimalFormat df = new DecimalFormat("####.00");
            bahtTxt = df.format(amount);
            String[] num = {"ศูนย์", "หนึ่ง", "สอง", "สาม", "สี่", "ห้า",
                "หก", "เจ็ด", "แปด", "เก้า", "สิบ"};
            String[] rank = {"", "สิบ", "ร้อย", "พัน", "หมื่น", "แสน", "ล้าน"};
            String[] temp = bahtTxt.split("[.]");
            String intVal = temp[0];
            String decVal = temp[1];
            if (Double.parseDouble(bahtTxt) == 0) {
                bahtTH = "";
            } else {
                for (int i = 0; i < intVal.length(); i++) {
                    n = intVal.substring(i, i + 1);
                    if (!"0".equals(n)) {
                        if ((i == (intVal.length() - 1)) && ("1".equals(n))) {
                            bahtTH += "เอ็ด";
                        } else if ((i == (intVal.length() - 2)) && ("2".equals(n))) {
                            bahtTH += "ยี่";
                        } else if ((i == (intVal.length() - 2)) && ("1".equals(n))) {
                            bahtTH += "";
                        } else {
                            bahtTH += num[Integer.parseInt(n)];
                        }
                        bahtTH += rank[(intVal.length() - i) - 1];
                    }
                }
                //  bahtTH += "กิโล";
                if ("00".equals(decVal)) {
                    bahtTH += "";
                } else {
                    bahtTH += "จุด";
                    for (int i = 0; i < decVal.length(); i++) {
                        n = decVal.substring(i, i + 1);
                        if (!"0".equals(n)) {
                            if ((i == decVal.length() - 1) && ("1".equals(n))) {
                                bahtTH += "เอ็ด";
                            } else if ((i == (decVal.length() - 2)) && ("2".equals(n))) {
                                bahtTH += "ยี่";
                            } else if ((i == (decVal.length() - 2)) && ("1".equals(n))) {
                                bahtTH += "";
                            } else {
                                bahtTH += num[Integer.parseInt(n)];
                            }
                            bahtTH += rank[(decVal.length() - i) - 1];
                        }
                    }
                    // bahtTH += "กรัม";
                }
            }

        } catch (NumberFormatException exe) {

            System.out.print(exe.getMessage());
        }
        return bahtTH;

    }

    public String getcustomer(String orno) {

        String cuno = null;
        try {

            Connection connect = ConnectDB2.ConnectionDB();
            Statement sta = connect.createStatement();

            String Sql = " SELECT B.UBORNO,A.UAIVNO,A.UACUNO,E.OKCUNM,RTRIM(E.OKCUA1)||' '||RTRIM(E.OKCUA2)||' '||RTRIM(E.OKCUA3) ADDR,SUM(C.MTTRQT*-1) MTTRQT   ";
            Sql += " FROM M3FDBPRD.ODHEAD A,M3FDBPRD.ODLINE B,M3FDBPRD.MITTRA C,M3FDBPRD.MITMAS D, M3FDBPRD.OCUSMA E  ";
            Sql += " WHERE B.UBORNO = C.MTRIDN AND B.UBCONO = C.MTCONO AND C.MTTTYP = 31  AND B.UBITNO = C.MTITNO ";
            Sql += " AND A.UAORNO = B.UBORNO AND A.UACONO = B.UBCONO AND A.UACONO = 10  AND B.UBORNO ='" + orno + "' ";
//          Sql += " AND C.MTCONO = D.MMCONO  AND C.MTITNO = D.MMITNO AND D.MMITGR BETWEEN 'SH01' AND 'SH07' ";
            Sql += " AND C.MTCONO = D.MMCONO  AND C.MTITNO = D.MMITNO AND (D.MMITGR BETWEEN 'SH01' AND 'SH07' OR D.MMITGR = 'MO00') ";
            Sql += " AND A.UACONO = E.OKCONO AND A.UACUNO = E.OKCUNO ";
            Sql += " GROUP BY B.UBORNO,A.UAIVNO,A.UACUNO,E.OKCUNM,RTRIM(E.OKCUA1)||' '||RTRIM(E.OKCUA2)||' '||RTRIM(E.OKCUA3) ";

            ResultSet rs1 = sta.executeQuery(Sql);

            if (rs1.next()) {
                cuno = rs1.getString("UACUNO").trim();
            }

            return cuno;
        } catch (Exception e) {
        }
        return null;

    }

    public void getReportSetSeries(String Program) {
        // TODO add your handling code here:
        String PathFile = System.getProperty("user.dir").toString() + "\\Report\\";

        String name = PathFile + "SetSeries.jasper";

        try {

//          Connection connect = ConnectDB2.ConnectionDB();
            Connection conn = ConnectDB2.ConnectionDB();
            //JRResultSetDataSource resultSetDataSource = new   JRResultSetDataSource(Rs1);

            /////////////////////////////////////////////////////////ORIGINAL//////////////////////////////////////////////////
            Map parameterss = new HashMap();
            parameterss.put("Program", Program);
            parameterss.put("User", LoginMain.LoginUsername);
//          parameterss.put("POAORNO", GRNNUMBER);

            JasperPrint print = JasperFillManager.fillReport(name, parameterss, conn);

            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);

        } catch (Exception e) {
            System.out.println("e");
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getReportSetSeriesV2(String Program) {
        // TODO add your handling code here:
        String PathFile = System.getProperty("user.dir").toString() + "\\Report\\";

        String name = PathFile + "SetSeriesV2.jasper";

        try {

//          Connection connect = ConnectDB2.ConnectionDB();
            Connection conn = ConnectDB2.ConnectionDB();
            //JRResultSetDataSource resultSetDataSource = new   JRResultSetDataSource(Rs1);

            /////////////////////////////////////////////////////////ORIGINAL//////////////////////////////////////////////////
            Map parameterss = new HashMap();
            parameterss.put("Program", Program);
            parameterss.put("User", LoginMain.LoginUsername);
//            parameterss.put("POAORNO", GRNNUMBER);

            JasperPrint print = JasperFillManager.fillReport(name, parameterss, conn);

            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);

        } catch (Exception e) {
            System.out.println("e");
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getReportTHMAX(String sav_no, String[] data, String day, String month, String year) {
        // TODO add your handling code here:
        String PathFile = System.getProperty("user.dir").toString() + "\\Report\\";
        String name = PathFile + "rptCerDoctorHead_THMAX.jasper";
        try {
            Connection conn = ConnectMsSql.ConnectionDB();
//            System.out.println(day);
//            System.out.println(month);
//            System.out.println(year);
            //JRResultSetDataSource resultSetDataSource = new   JRResultSetDataSource(Rs1);
//            System.out.println(date);
            /////////////////////////////////////////////////////////ORIGINAL//////////////////////////////////////////////////
            Map parameterss = new HashMap();
            parameterss.put("sav_no", sav_no);
            parameterss.put("WHS_Name", data[0]);
            parameterss.put("WHS_Code", data[1]);
            parameterss.put("WHS_Address", data[2]);
            parameterss.put("DAY", day);
            parameterss.put("MONTH", month);
            parameterss.put("YEAR", year);
//            JasperPrint print = JasperFillManager.fillReport(name, parameterss, conn);
//            JasperViewer view = new JasperViewer(print, false);
//            view.setVisible(true);

            try {
//                JOptionPane.showMessageDialog(null, name);
//                JOptionPane.showMessageDialog(null, parameterss);
//                JOptionPane.showMessageDialog(null, conn);
                JasperPrint print = JasperFillManager.fillReport(name, parameterss, conn);
                JasperViewer view = new JasperViewer(print, false);
                view.setVisible(true);
            } catch (JRException ex) {
                ex.printStackTrace();
//                return getErrorJRPrint(ex);
                JOptionPane.showMessageDialog(null, ex);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getReportFarmPra(String CONumber, Integer subyear) {
        // TODO add your handling code here:
        File myFile = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\farmprareport.pdf");
        String PathFile = System.getProperty("user.dir").toString() + "\\Report\\";
        String name = PathFile + "rptCerDoctorHead_FarmPra.jasper";
        try {
//          Connection conn = ConnectMsSql.ConnectionDB();
            Connection conn = ConnectDB2.ConnectionDB();
            //JRResultSetDataSource resultSetDataSource = new   JRResultSetDataSource(Rs1);
            /////////////////////////////////////////////////////////ORIGINAL//////////////////////////////////////////////////
            Map parameterss = new HashMap();
            parameterss.put("POAORNO", CONumber);
            JasperPrint print = JasperFillManager.fillReport(name, parameterss, conn);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);

            /////////////////////////////////////////////////////////COPY 1//////////////////////////////////////////////////
            String name2 = PathFile + "rptCerFishFarm.jasper";
            Map parameterss2 = new HashMap();
//          parameterss2.put("ThaiBaht",sumThaiBaht);
            parameterss2.put("POAORNO", CONumber);
            parameterss2.put("year", subyear);
            JasperPrint print2 = JasperFillManager.fillReport(name2, parameterss2, conn);

            JasperViewer view2 = new JasperViewer(print2, false);
            view2.setVisible(true);
//          export pdf file for farmpra
            jasperPrints.add(print);
            jasperPrints.add(print2);
            exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrints));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(myFile));
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            configuration.setPermissions(PdfWriter.AllowCopy | PdfWriter.AllowPrinting);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            //เปิดไฟล์เมื่อ render เสร็จ
//            if (Desktop.isDesktopSupported()) {
//                try {
//                    Desktop.getDesktop().open(myFile);
//                    System.out.println(myFile);
//                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(null, e);
//                    // no application registered for PDFs
//                }
//            }
        } catch (Exception e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, e);

        }

    }

}
