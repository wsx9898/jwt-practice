package tw.com.ispan.cma.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tw.com.ispan.cma.dao.Table1DAO;
import tw.com.ispan.cma.domain.Table1Bean;


import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class Table1Service {
    @Autowired
    private Table1DAO table1DAO;

    private Base64 base64 = new Base64();


    public List<Table1Bean> select(Table1Bean bean){
        List<Table1Bean> result = null;
        List<Table1Bean> returnResult = new ArrayList<Table1Bean>();

        //!!!!如果只拿特定的還是會改到資料庫
        //這邊是指定拿到特定的一個bean
        if(bean!=null && bean.getIdx()!=null && !bean.getIdx().equals(0)){
            Table1Bean specificBean = table1DAO.select(bean.getIdx());
            if(specificBean!=null){
                var temp = new Table1Bean();
                //創立一個新的tempBean把specificBean所有值都複製過去
                temp.setIdx(specificBean.getIdx());
                temp.setAes256(specificBean.getAes256());
                //把加密過的先取出來解密再放回去
                temp.setBase64(new String(base64.decode(specificBean.getBase64().getBytes())));

                returnResult.add(temp);
            }
        //這邊會取得所有的beans
        }else {
            result = table1DAO.select();
            //在這層把base64解密，再把他取代原本的
            for(Table1Bean ele:result){
                String encryptedStr = ele.getBase64();
                var decryptedStr = new String(base64.decode(encryptedStr.getBytes()));
                //創立一個新的tempBean把ele所有值都複製過去
                Table1Bean tempBean = new Table1Bean();
                tempBean.setIdx(ele.getIdx());
                tempBean.setAes256(ele.getAes256());
                tempBean.setBase64(decryptedStr);

//                System.out.println("ele = " + ele);
//                System.out.println("tempBean = " + tempBean);
                returnResult.add(tempBean);
            }
        }
        return returnResult;
    }

    public Table1Bean insert(Table1Bean bean){
        Table1Bean result = null;
        if(bean!=null){
            //這邊設定為0
            if(bean.getIdx()==null) {
                bean.setIdx(0);
            }
            //encoding here
            if(bean.getBase64()!=null){
                var rawData = bean.getBase64();
                System.out.println("rawData = "+ rawData);
                String encodedData = new String(base64.encodeBase64(rawData.getBytes()));
//                System.out.println("encodedData = "+ encodedData);
                bean.setBase64(encodedData);

            }

            System.out.println(" from Table1 service, bean.getIdx() = " + bean.getIdx());
            result = table1DAO.insert(bean);
        }
        return result;
    }

}
