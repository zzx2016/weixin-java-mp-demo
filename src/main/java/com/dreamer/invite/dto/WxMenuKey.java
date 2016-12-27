package com.dreamer.invite.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 菜单的dto对象
 *
 * @author Binary Wang
 */
public class WxMenuKey {
    private String type;
    private String content;

    public WxMenuKey() {

    }

    public WxMenuKey(String type, String content) {
        this.type = type;
        this.content = content;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
