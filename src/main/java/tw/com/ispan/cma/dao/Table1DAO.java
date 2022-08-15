package tw.com.ispan.cma.dao;



import tw.com.ispan.cma.domain.Table1Bean;

import java.util.List;

public interface Table1DAO {
    public abstract Table1Bean select(Integer idx);

    public abstract List<Table1Bean> select();

    public abstract Table1Bean insert(Table1Bean bean);

    public abstract Table1Bean update(Table1Bean bean);

    public abstract boolean delete(Integer idx);

}
