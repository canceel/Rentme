package com.allipper.rentme.bean;

/**
 * Created by allipper on 2015/10/16.
 */
public class Pagination {
    //当前页
    public int currentPage;
    //每页订单数量
    public int pageSize;
    //总页数
    public int numberOfPages;
    //订单总数
    public int totalNumberOfResults;

    /**
     * 对比是否是最后一页
     *
     * @return
     */
    public boolean isLastPage() {
        return currentPage == numberOfPages - 1 || (currentPage == 1 && numberOfPages == 1);
    }
}
