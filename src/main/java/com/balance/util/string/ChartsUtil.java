package com.balance.util.string;

import com.balance.prj.vo.ChartsDataVO;
import com.balance.util.json.JsonUtil;
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
                "\"data\": [{\"value\": " + vo.getGuageData() + ", \"name\": \"总" + WebTag.getChartTitleByType(vo.getType()) + ":" + formatFloat(total) + "\"}]" +
                " }]}";
        return json;
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
        } else return String.valueOf(total);
    }
}
