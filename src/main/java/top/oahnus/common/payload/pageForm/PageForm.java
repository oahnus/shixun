package top.oahnus.common.payload.pageForm;

import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


/**
 * Created by oahnus on 2018/5/13
 * 19:49.
 */
@Data
public class PageForm implements Pageable {

    private static final long serialVersionUID = 1232825278693716871L;

    private int page;
    private int size = 10;

    private Sort sort;

    public PageForm () {}

    public PageForm(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public PageForm(int page, int size, Sort sort) {

        if (page < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }

        if (size < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }

        this.page = page;
        this.size = size;
        this.sort = sort;

    }

    @Override
    public int getPageNumber() {
        return page;
    }

    @Override
    public int getPageSize() {
        return size;
    }

    @Override
    public int getOffset() {
        return page * size;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new PageForm(getPageNumber() + 1, getPageSize(), getSort());
    }

    @Override
    public Pageable previousOrFirst() {
        return getPageNumber() == 0 ? this : new PageForm(getPageNumber() - 1, getPageSize(), getSort());

    }

    @Override
    public Pageable first() {
        return new PageForm(0, getPageSize(), getSort());
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PageForm)) {
            return false;
        }

        PageForm that = (PageForm) obj;

        boolean sortEqual = this.sort == null ? that.sort == null : this.sort.equals(that.sort);

        return super.equals(that) && sortEqual;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + (null == sort ? 0 : sort.hashCode());
    }

}
