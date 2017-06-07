package com.balance.util.dic;

import com.balance.common.vo.ComboboxVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/4.
 */
public class DicUtil {

    /**
     * 项目阶段
     * 1前期准备阶段2入户调查阶段3预分方案制作阶段4动迁准备阶段5动迁阶段6收尾阶段
     *
     * @return
     */
    public static List<ComboboxVO> listProjectProgress() {
        List<ComboboxVO> list = new ArrayList<>();
        ComboboxVO vo1 = new ComboboxVO("1", "前期准备阶段");
        ComboboxVO vo2 = new ComboboxVO("2", "入户调查阶段");
        ComboboxVO vo3 = new ComboboxVO("3", "内业整理阶段");
        ComboboxVO vo4 = new ComboboxVO("4", "动迁准备阶段");
        ComboboxVO vo5 = new ComboboxVO("5", "动迁阶段");
        ComboboxVO vo6 = new ComboboxVO("6", "收尾阶段");
        list.add(vo1);
        list.add(vo2);
        list.add(vo3);
        list.add(vo4);
        list.add(vo5);
        list.add(vo6);
        return list;
    }
}
