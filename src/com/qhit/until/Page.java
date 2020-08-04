package com.qhit.until;

/**
 * Created by ASUS on 2019/11/18.
 */
public class Page {
    private int pageCount ;//总页数
    private int pageSize = 5;//每页的记录数
    private int count ;//总记录数
    private int nowPage =1;//当前页

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        //计算总页数
        pageCount = this.count % pageSize ==0?(this.count/pageSize):(this.count/pageSize+1);
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }
}
