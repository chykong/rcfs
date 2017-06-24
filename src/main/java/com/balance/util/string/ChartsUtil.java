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
    public static String createChartsJson(ChartsDataVO vo, float total) {
        String json = "{\"categories\": " + JsonUtil.toStr(vo.getBarCategories()) + ", \"barData\": [{" +
                "\"name\":\"" + vo.getBarTitle1() + "\"," +
                "\"type\":\"bar\"," +
                "\"data\":" + JsonUtil.toStr(vo.getBarData1()) + "" +
                "}," +
                "{" +
                "\"name\":\"" + vo.getBarTitle2() + "\"," +
                "\"type\":\"bar\"," +
                "\"data\":" + JsonUtil.toStr(vo.getBarData2()) + "" +
                "}],\"guageData\":[{" +
                "\"name\": \"" + vo.getGuageTitle() + "\"," +
                "\"type\": \"gauge\"," +
                "\"detail\": {formatter:'{value}%'}," +
                "\"data\": [{\"value\":\" " + vo.getGuageData() + "\", \"name\": \"" + WebTag.getChartTitleByType(vo.getType()) + ":" + createTotal(vo.getType(), total) + "\"}]" +
                " }]}";
        return json;
    }

    private static String createTotal(int type, float value) {
        if (type == 1)
            return String.valueOf(value) + "户";
        else return formatFloat(value / 10000) + "万m²";
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
}
