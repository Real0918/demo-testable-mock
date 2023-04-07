package com.example.demo.modular.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @version 1.0
 * @author: WangWei
 * @date: 2021/4/9 10:59:20
 */
@ApiModel(description = "用户更新信息")
public class UserUpdateDto {

    @ApiModelProperty(value = "名字", name = "name")
    private String name;
    @ApiModelProperty(value = "权限状态", name = "permissionStatus")
    private Integer permissionStatus;
    @ApiModelProperty(value = "描述信息", name = "description")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPermissionStatus() {
        return permissionStatus;
    }

    public void setPermissionStatus(Integer permissionStatus) {
        this.permissionStatus = permissionStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
