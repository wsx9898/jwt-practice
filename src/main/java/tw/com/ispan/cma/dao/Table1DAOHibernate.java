package tw.com.ispan.cma.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import tw.com.ispan.cma.domain.Table1Bean;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Repository
public class Table1DAOHibernate implements Table1DAO {
    @PersistenceContext
    private Session session = null;

    public Session getSession() {
        return session;
    }

    @Override
    public Table1Bean select(Integer idx) {
        System.out.println("Hibernate select(idx)有被呼叫到");
        if (idx != null) {
            return this.getSession().get(Table1Bean.class, idx);
        }
        return null;
    }

    @Override
    public List<Table1Bean> select() {
//        System.out.println("Hibernate select()有被呼叫到");

        CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
        CriteriaQuery<Table1Bean> criteriaQuery = criteriaBuilder.createQuery(Table1Bean.class);
        //!!!!這行不能少!!!
        Root<Table1Bean> root = criteriaQuery.from(Table1Bean.class);

        TypedQuery<Table1Bean> typedQuery = this.getSession().createQuery(criteriaQuery);
        List<Table1Bean> result = typedQuery.getResultList();
        if(result!=null && !result.isEmpty()){
            return result;
        }
        return null;
    }

    @Override
    public Table1Bean insert(Table1Bean bean) {
        if (bean != null ) {
            System.out.println("if (bean != null )");
            Table1Bean temp = this.getSession().get(Table1Bean.class, bean.getIdx());
            //如果id有東西就不要在這裡新增覆蓋
            if (temp == null) {
                System.out.println("這個id目前是空值");
                if (bean.getIdx() == null) {
                    bean.setIdx(0);
                }
                if (bean.getAes256() == null) {
                    bean.setAes256("");
                }
                if (bean.getBase64() == null) {
                    bean.setBase64("");
                }
                this.getSession().save(bean);
                return bean;
            }else{
                System.out.println("此id已經有值");
                return null;
            }
        }
        return null;
    }

    @Override
    public Table1Bean update(Table1Bean bean) {
        return null;
    }

    @Override
    public boolean delete(Integer idx) {
        return false;
    }
}
