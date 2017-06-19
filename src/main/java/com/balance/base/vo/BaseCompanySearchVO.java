package com.balance.base.vo;

import com.balance.util.page.PageSearchVO;

/**
 * Author  孔垂云
 * Date  2017/6/3.
 */
public class BaseCompanySearchVO extends PageSearchVO {

	private Integer prj_base_info_id;//项目id

	public Integer getPrj_base_info_id() {
		return prj_base_info_id;
	}

	public void setPrj_base_info_id(Integer prj_base_info_id) {
		this.prj_base_info_id = prj_base_info_id;
	}

	@Override
	public String toString() {
		return "BaseCompanySearchVO [prj_base_info_id=" + prj_base_info_id + "]";
	}
	

}
