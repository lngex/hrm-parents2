package cn.lngex.utils;

import cn.lngex.search.doc.vo.AggVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//分页对象：easyui只需两个属性，total(总数),datas（分页数据）就能实现分页
public class PageList<T> {
    private long total;
    private List<T> rows = new ArrayList<>();
    private HashMap<String, ArrayList<AggVo>> map;

    public HashMap<String, ArrayList<AggVo>> getMap() {
        return map;
    }

    public PageList<T> setMap(HashMap<String, ArrayList<AggVo>> map) {
        this.map = map;
        return this;
    }

    public long getTotal() {
        return total;
    }

    public PageList<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    public List<T> getRows() {
        return rows;
    }

    public PageList<T> setRows(List<T> rows) {
        this.rows = rows;
        return this;
    }

    @Override
    public String toString() {
        return "PageList{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }

    //提供有参构造方法，方便测试
    public PageList(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
    //除了有参构造方法，还需要提供一个无参构造方法
    public PageList() {
    }
}
