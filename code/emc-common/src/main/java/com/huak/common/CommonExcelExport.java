/**
 * 通用的excel导出
 */
package com.huak.common;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明：
 *
 * @author yx
 *         创建时间：2012-12-27上午9:54:29
 */
public class CommonExcelExport {

    private static String c = "c";//实际
    private static String l = "l";//同期
    private static String p = "p";//计划

    public static HSSFWorkbook excelExport(Map<String, String> cellName, List<Map<String, Object>> cellValues) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCell cell;
        int i = 0;
        for (Map.Entry<String, String> entry : cellName.entrySet()) {
            cell = row.createCell(i);
            cell.setCellValue(entry.getValue());
            i++;
        }
        for (int j = 0; j < cellValues.size(); j++) {                                            //单元格值填充
            row = sheet.createRow(j + 1);
            Map<String, Object> ret = cellValues.get(j);
            int icell = 0;
            for (Map.Entry<String, String> entry : cellName.entrySet()) {
                Object value = ret.get(entry.getKey().toUpperCase());
                String cellValue = "";
                if (value != null) {
                    cellValue = value.toString();
                }
                row.createCell(icell).setCellValue(cellValue);
                icell++;
            }
        }

        return wb;

    }

    public static HSSFWorkbook excelExportCost(Map<String, String> cellName, List<Map<String, Object>> cellValues) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCell cell;
        int i = 0;
        for (Map.Entry<String, String> entry : cellName.entrySet()) {
            cell = row.createCell(i);
            cell.setCellValue(entry.getValue());
            i++;
        }
        for (int j = 0; j < cellValues.size(); j++) {                                            //单元格值填充
            row = sheet.createRow(j + 1);
            Map<String, Object> ret = cellValues.get(j);
            int icell = 0;
            for (Map.Entry<String, String> entry : cellName.entrySet()) {
                Object value = ret.get(entry.getKey());
                String cellValue = "";
                if (value != null) {
                    cellValue = value.toString();
                }
                row.createCell(icell).setCellValue(cellValue);
                icell++;
            }
        }

        return wb;

    }


    /**
     * 导出三级页面表格
     *
     * @param map Map<String, Object>
     * @return
     * @throws IOException
     */
    public static HSSFWorkbook excelExportThird(Map<String, Object> map) throws IOException {
        List<Map<String, Object>> datas = (List<Map<String, Object>>) map.get("list");
        List<String> titles = (List<String>) map.get("dates");
        Map<String, Object> feedTotal = (Map<String, Object>) map.get("feedTotal");
        Map<String, Object> netTotal = (Map<String, Object>) map.get("netTotal");
        Map<String, Object> stationTotal = (Map<String, Object>) map.get("stationTotal");
        Map<String, Object> lineTotal = (Map<String, Object>) map.get("lineTotal");
        Map<String, Object> roomTotal = (Map<String, Object>) map.get("roomTotal");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCell cell;
        CellRangeAddress cra1 = new CellRangeAddress(0, 1, 0, 0);

        int i = 1;//跳过第一列
        for (String title : titles) {
            CellRangeAddress cra = new CellRangeAddress(0, 0, i, i + 2);//合并第一行3列
            sheet.addMergedRegion(cra);
            cell = row.createCell(i);
            cell.setCellValue(title);
            i += 3;
        }
        i = 1;//跳过第一列
        row = sheet.createRow((int) 1);//第二行
        for (int j = 1; j <= titles.size() * 3; j++) {
            cell = row.createCell(i);
            cell.setCellValue(getModTitle(j));
            i++;
        }
        String flag = "0";
        int n = 0;
        for (int j = 0; j < datas.size(); j++) {                                            //单元格值填充

            Map<String, Object> ret = datas.get(j);
            String type = ret.get("unitType").toString();
            if (j == 0 || !flag.equals(type)) {
                flag = type;
                if ("1".equals(type)) {
                    createRow(feedTotal, sheet, j + n + 2, titles.size() * 3);
                } else if ("2".equals(type)) {
                    createRow(netTotal, sheet, j + n + 2, titles.size() * 3);
                } else if ("3".equals(type)) {
                    createRow(stationTotal, sheet, j + n + 2, titles.size() * 3);
                } else if ("4".equals(type)) {
                    createRow(lineTotal, sheet, j + n + 2, titles.size() * 3);
                } else if ("5".equals(type)) {
                    createRow(roomTotal, sheet, j + n + 2, titles.size() * 3);
                }
                n++;
            }

            createRow(ret, sheet, j + n + 2, titles.size() * 3);
        }
        sheet.addMergedRegion(cra1);
        return wb;

    }

    private static int imod(int i, int j) {
        int temp = i % j;
        return (temp < 0) ? -temp : temp;
        // Unary minus will succeed without overflow
        // because temp cannot be Integer.MIN_VALUE
    }

    /**
     * 公共方法
     *
     * @param ret
     * @param sheet
     */
    private static void createRow(Map<String, Object> ret, HSSFSheet sheet, int j, int size) {
        HSSFRow row = sheet.createRow(j);
        int icell = 0;
        if (ret.get("unitName") != null) {
            row.createCell(icell).setCellValue(ret.get("unitName").toString());
        } else {
            row.createCell(icell).setCellValue("");
        }

        for (int m = 1; m <= size; m++) {
            Object value = ret.get(getModValue(m));
            String cellValue = "";
            if (value != null) {
                cellValue = value.toString();
            }
            row.createCell(icell + 1).setCellValue(cellValue);
            icell++;
        }
    }

    private static String getModValue(int m) {
        int i = ((m - 1) / 3);
        if (imod(m,3) == 1) {
            return c + i;
        } else if (imod(m,3) == 2) {
            return p + i;
        } else {
            return l + i;
        }
    }

    private static String getModTitle(int j) {
        if (imod(j,3) == 1) {
            return "实际";
        } else if (imod(j,3) == 2) {
            return "计划";
        } else {
            return "同期";
        }
    }


    /**
     * 需要合并单元格 特殊的excel导出
     */
    public void mergeExcelExport(HttpServletResponse response,
                                 HSSFWorkbook wb,
                                 int startRow,
                                 List<Map<Integer, Object>> cellValues,
                                 String workBookName) throws IOException {
        OutputStream out = null;
        //获取工作簿中的第一个sheet
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row;
        for (int i = 0; i < cellValues.size(); i++) {
            row = sheet.createRow(startRow++);                                    //插入一行
            Map<Integer, Object> record = new HashMap<Integer, Object>();
            record = cellValues.get(i);
            for (int j = 0; j < record.size(); j++) {                            //填充一条记录
                Object value = record.get(j);
                String cellValue = "";
                if (value != null) {
                    cellValue = value.toString();
                }
                row.createCell(j).setCellValue(cellValue);
            }
        }

        //response输出流导出excel
        try {
            String mimetype = "application/vnd.ms-excel";
            response.setContentType(mimetype);
            response.setCharacterEncoding("UTF-8");
            String fileName = workBookName + ".xls";
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            out = response.getOutputStream();
            wb.write(out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }

        }
    }

    public static void main(String[] args) {
        for (int i = 1; i < 20; i++) {
            System.err.println(getModValue(i));
        }
    }
}

