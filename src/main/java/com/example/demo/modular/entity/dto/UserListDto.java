package com.example.demo.modular.entity.dto;

/**
 * @version 1.0
 * @author: WangWei
 * @date: 2021/4/9 10:20:58
 */
public class UserListDto {

    private Integer pageNumber;
    private Integer pageSize;
    private String search;

    public UserListDto() {
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public UserListDto(Builder builder) {
        this.pageNumber = builder.pageNumber;
        this.pageSize = builder.pageSize;
        this.search = builder.search;
    }

    public static class Builder {
        private Integer pageNumber;
        private Integer pageSize;
        private String search;

        public Builder(Integer pageNumber, Integer pageSize) {
            this.pageNumber = pageNumber;
            this.pageSize = pageSize;
        }

        public Builder setSearch(String search) {
            this.search = search;
            return this;
        }

        public UserListDto build() {
            return new UserListDto(this);
        }
    }
}
