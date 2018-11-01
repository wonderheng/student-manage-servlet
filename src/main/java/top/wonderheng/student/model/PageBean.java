package top.wonderheng.student.model;

/**
 * @BelongsProject: student-manage-servlet
 * @BelongsPackage: top.wonderheng.student.model
 * @Author: WonderHeng
 * @CreateTime: 2018-09-27 22:40
 */
public class PageBean {

    private int page;
    private int rows;

    public PageBean(int page, int rows) {
        super();
        this.page = page;
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getRows() {
        return rows;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
    public int getStart() {
        return (page-1)*rows;
    }
}
