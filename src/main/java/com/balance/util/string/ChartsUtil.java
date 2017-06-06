package com.balance.util.string;

import com.balance.prj.vo.ChartsDataVO;
import com.balance.util.json.JsonUtil;

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
    public static String createChartsJson(ChartsDataVO vo) {
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
                "\"data\": [{\"value\": " + vo.getGuageData() + ", \"name\": \"完成率\"}]" +
                " }]}";
        return json;
    }
}
