package com.balance.util.string;

import com.balance.prj.vo.ChartsDataVO;
import com.balance.util.json.JsonUtil;
import com.balance.util.number.NumberUtil;
import com.balance.util.web.WebTag;

/**
 * Author  孔垂云
 * Date  2017/6/6.
 */
public class ChartsUtil {
    /**
     * 生成图标的json数据
     *
     * @param vo
     * @return
     */
    public static String createChartsJson(ChartsDataVO vo, float total,int area_type) {
        String json2 = "{" + createBarStr(vo) + "," + createBarSectionStr(vo) + "," + createGuageStr(vo, total,area_type) + "}";
        return json2;
    }

    /**
     * 生成分组柱状图字符串
     *
     * @param vo
     * @return
     */
    private static String createBarStr(ChartsDataVO vo) {
        String str = "\"categories\": " + JsonUtil.toStr(vo.getBarCategories()) + ", \"barData\": [{" +
                "\"name\":\"" + vo.getBarTitle1() + "\"," +
                "\"type\":\"bar\"," +
                "\"data\":" + JsonUtil.toStr(vo.getBarData1()) + "" +
                "}," +
                "{" +
                "\"name\":\"" + vo.getBarTitle2() + "\"," +
                "\"type\":\"bar\"," +
                "\"data\":" + JsonUtil.toStr(vo.getBarData2()) + "" +
                "}]";
        return str;
    }

    /**
     * 生成按标段字符串
     *
     * @return
     */
    private static String createBarSectionStr(ChartsDataVO vo) {
        String str = "\"sectionCategories\": " + JsonUtil.toStr(vo.getBarSectionCategories()) + ",\"barSetionData\":" +
                "[{\"name\": \"已" + ChartsUtil.getGuageName(vo.getSearch_type()) + "\",\"type\": \"bar\",\"stack\": \"总量\",\"label\": {\"normal\": {\"show\": true,\"position\": \"insideRight\"}},\"data\": " + JsonUtil.toStr(vo.getBarSectionData1()) + "}," +
                "{\"name\": \"未" + ChartsUtil.getGuageName(vo.getSearch_type()) + "\",\"type\": \"bar\",\"stack\": \"总量\",\"normal\": {\"show\": true,\"position\": \"insideRight\"},\"data\": " + JsonUtil.toStr(vo.getBarSectionData2()) + "}]";
        return str;
    }

    private static String createGuageStr(ChartsDataVO vo, float total, int area_type) {
        String str = "\"guageData\":[{" +
                "\"name\": \"" + vo.getGuageTitle() + "\"," +
                "\"type\": \"gauge\"," +
                "\"detail\": {formatter:'{value}%'}," +
                "\"data\": [{\"value\":\" " + vo.getGuageData() + "\", \"name\": \"总" + WebTag.getChartTitleByType(vo.getType()) + ":" + createTotal(vo.getType(), area_type, total) + "\\r\\n" + overStr(vo, area_type) + "\"}]" +
                " }]";
        return str;
    }

    /**
     * 处理已完成数字符串
     *
     * @return
     */
    public static String overStr(ChartsDataVO vo, int area_type) {
        String str = "已" + getGuageName(vo.getSearch_type()) + ":" + createTotal(vo.getType(), area_type, vo.getGuageData2());
        return str;
    }

    public static String createTotal(int type, int area_type, float value) {
        if (type == 1) {
            return String.valueOf((int) value) + "户";
        } else {
            if (area_type == 0) {
                return formatFloat(value / 10000) + "万m²";
            } else {
                return formatFloat(value * 3 / 2000) + "亩";
            }
        }
    }

    /**
     * 把后缀为.0的去掉，即户数为整数
     *
     * @param total
     * @return
     */
    private static String formatFloat(float total) {
        if (String.valueOf(total).endsWith(".0")) {
            return String.valueOf((int) total);
        } else return String.valueOf(NumberUtil.formatNumber(total));
    }

    /**
     * 根据类型返回类型
     *
     * @param search_type
     * @return
     */
    public static String getGuageName(int search_type) {
        if (search_type == 1) return "入户";
        else if (search_type == 2) return "签约";
        else if (search_type == 3) return "交房";
        else if (search_type == 4) return "放款";
        else return "";
    }
}
