package com.allipper.rentme.bean;

/**
 * Created by allipper on 2015/10/16.
 */
public class Pagination {
    //当前页
    private int currentPage;
    //每页订单数量
    private int pageSize;
    //总页数
    private int numberOfPages;
    //订单总数
    private int totalNumberOfResults;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getTotalNumberOfResults() {
        return totalNumberOfResults;
    }

    public void setTotalNumberOfResults(int totalNumberOfResults) {
        this.totalNumberOfResults = totalNumberOfResults;
    }

    /**
     * 对比是否是最后一页
     *
     * @return
     */
    public boolean isLastPage() {
        return currentPage == numberOfPages - 1 || (currentPage == 0 && numberOfPages == 0);
    }
}
