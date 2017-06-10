package com.balance.api.dto;

import com.balance.util.string.StringUtil;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.AssertTrue;

/**
 * Author  孔垂云
 * Date  2017/6/10.
 */
public class ChangePassDTO {
    @NotBlank(message = "原密码不能为空")
    @Length(min = 6, max = 32, message = "原密码长度应在6~32位之间")
    private String old_pass;//原密码
    @NotBlank(message = "新密码不能为空")
    @Length(min = 6, max = 32, message = "新密码长度应在6~32位之间")
    private String new_pass;//新密码
    @NotBlank(message = "确认新密码不能为空")
    @Length(min = 6, max = 32, message = "确认新密码长度应在6~32位之间")
    private String confirm_new_pass;//确认新密码

    @AssertTrue(message = "确认新密码必须和原密码一致")
    private boolean confirm_new_pass_equal_new_pass() {
        return !(StringUtil.isNullOrEmpty(this.confirm_new_pass) ||
                StringUtil.isNullOrEmpty(this.new_pass)) && this.confirm_new_pass.equals(this.new_pass);
    }

    public String getOld_pass() {
        return old_pass;
    }

    public void setOld_pass(String old_pass) {
        this.old_pass = old_pass;
    }

    public String getNew_pass() {
        return new_pass;
    }

    public void setNew_pass(String new_pass) {
        this.new_pass = new_pass;
    }

    public String getConfirm_new_pass() {
        return confirm_new_pass;
    }

    public void setConfirm_new_pass(String confirm_new_pass) {
        this.confirm_new_pass = confirm_new_pass;
    }
}
