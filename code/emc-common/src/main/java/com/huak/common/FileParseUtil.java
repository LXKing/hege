package com.huak.common;

import com.huak.common.utils.ColumUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by MR-BIN on 2017/9/8.
 */
public class FileParseUtil {

    /**
     * 后台-上传入库-数据解析
     * @param xssfRow
     * @param classz
     * @return
     * @throws java.lang.reflect.InvocationTargetException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     */
    public static Object digitData(Row xssfRow ,Map<String,String> cellMap, Class<?> classz) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        Object obj = null;
        obj =Class.forName(classz.getName()).newInstance();
        Method[] methods = classz.getMethods();
        Field[] fields = classz.getClass().getDeclaredFields();
        Map<String,Object> data = new HashMap<>();
        List<String> cells = new ArrayList<>();
           /*自动化匹配*/
//           for (Field field : fields){
//               if(!Constants.CELL_NAME.containsKey(ColumUtil.getColumn(field.getName()))){
//                   Constants.CELL_NAME.put( ColumUtil.getColumn(field.getName()),field.getName());
//               }
//           }
        Iterator iter = cellMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key =  entry.getKey().toString();
            cells.add(key);
        }
        for(int i = 0; i < cells.size();i++){
            Object tag = getValue(xssfRow.getCell(i));

            String cellname = cells.get(i);
            for (Method method : methods) {
                String methodName = method.getName();
                // 如果对象的方法以set开头
                if (methodName.startsWith("set")) {
                    // 根据方法名字得到数据表格中字段的名字
                    String columnName = methodName.substring(3, methodName.length());
                    columnName = ColumUtil.getColumn(columnName);

                    if(cellname.equals(columnName)){

                        // 得到方法的参数类型
                        Class[] parmts = method.getParameterTypes();

                        if (parmts[0] == String.class) {
                            if(tag.toString().endsWith(".0")){
                                tag =  tag.toString().substring(0, tag.toString().length() - 2);
                            }
                            if(tag != null && tag !=""){
                                method.invoke(obj,tag.toString());
                            }else{
                                method.invoke(obj,"");
                            }
                        }
                        if (parmts[0] == byte.class || parmts[0] == Byte.class) {
                            System.out.println(columnName+"----type:"+parmts[0]+"-----------value:"+tag);
                            if(tag.toString().endsWith(".0")){
                                tag =  new Byte(tag.toString().substring(0, tag.toString().length() - 2));
                            }
                            if(tag != null ){
                                method.invoke(obj, Byte.valueOf(tag.toString()));
                            }else{
                                method.invoke(obj,0);
                            }
                        }
                        if (parmts[0] == int.class || parmts[0] == Integer.class) {
                            if(tag != null ){
                                method.invoke(obj, Integer.parseInt(tag.toString()));
                            }else{
                                method.invoke(obj,0);
                            }
                        }
                        if ( parmts[0] == BigInteger.class) {
                            if(tag != null ){
                                method.invoke(obj, new BigInteger(tag.toString()));
                            }else{
                                method.invoke(obj,0);
                            }
                        }
                        if (parmts[0] == Double.class ) {
                            if(tag != null ){
                                method.invoke(obj, Double.parseDouble(tag.toString()));
                            }else{
                                method.invoke(obj,0.0);
                            }
                        }
                        if (parmts[0] == Float.class ) {
                            if(tag != null ){
                                method.invoke(obj, Float.parseFloat(tag.toString()));
                            }else{
                                method.invoke(obj,0.0);
                            }
                        }
                        if (parmts[0] == BigDecimal.class ) {
                            if(tag != null ){
                                BigDecimal bigDecimal = new BigDecimal(tag.toString());
                                method.invoke(obj, bigDecimal.intValue());
                            }else{
                                method.invoke(obj,0.0);
                            }
                        }
                        if (parmts[0] == Date.class) {
                            method.invoke(obj,tag);
                        }

                    }
                }
            }
        }
        return obj;
    }

    private static Object getValue(Cell hssfCell){
        if(hssfCell==null){
            return "";
        }else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC){
            return String.valueOf( hssfCell.getNumericCellValue());
        }else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_STRING){
            return String.valueOf( hssfCell.getStringCellValue());
        }else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN){
            return String.valueOf( hssfCell.getBooleanCellValue());
        }else{
            return String.valueOf( hssfCell.getStringCellValue());
        }

    }


}
