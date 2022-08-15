package tw.com.ispan.cma.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Table1")
public class Table1Bean {
    @Id
    @Column(name = "idx")
    private Integer idx;

    @Column(name = "c_aes256")
    private String aes256;

    @Column(name = "c_base64")
    private String base64;


    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getAes256() {
        return aes256;
    }

    public void setAes256(String aes256) {
        this.aes256 = aes256;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    @Override
    public String toString() {
        return "{" +
                "\"idx\" : " + '\"' + idx + '\"' +
                ", \"aes256\" : " + '\"' + aes256 + '\"'+
                ", \"base64\" : " +  base64 +
                "}";
    }
}
