package com.erp.bean.search;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DefaultSearch {

    private String searchKey;

    private String searchValue;

    private Integer pageNum = 1;

    private Integer pageSize = 20;

    private String startDate;

    private String endDate;

    private int totalSize = 0;

    private String sort;

    private Integer sortIndex;

    private boolean desc = true;

    private Integer pageInterval = 10;

    private Integer adminId;

    private Integer id;

    public DefaultSearch(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        if (pageNum == null || pageSize == null) {
            return null;
        }
        return (pageNum - 1) * pageSize;
    }

    public int getMinPage() {
        return ((pageNum - 1) / pageInterval) * pageInterval + 1;
    }

    public int getLastPage() {
        return totalSize / pageSize + (totalSize % pageSize != 0 ? 1 : 0);
    }

    public int getMaxPage() {
        return Math.min(getMinPage() + pageInterval - 1, getLastPage());
    }

}
