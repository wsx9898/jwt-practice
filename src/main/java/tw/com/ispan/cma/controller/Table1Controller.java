package tw.com.ispan.cma.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.ispan.cma.domain.Table1Bean;
import tw.com.ispan.cma.service.Table1Service;


@RestController
@CrossOrigin
public class Table1Controller {
    @Autowired
    private Table1Service table1Service;


    @GetMapping(path = {"/getAllData"})
    public ResponseEntity getAllData() {
        System.out.println("Controller有被呼叫到");
        Table1Bean bean = new Table1Bean();
        bean.setIdx(0);

        var res = table1Service.select(bean);
        if (res != null) {
            return ResponseEntity.ok(res.toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = {"/getOneData"})
    public ResponseEntity getOneData() {
        System.out.println("Controller getOneData()有被呼叫到");
        Table1Bean bean = new Table1Bean();
        bean.setIdx(1);

        var res = table1Service.select(bean);
        if (res != null) {
            return ResponseEntity.ok(res.toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = {"/insertData"})
    public ResponseEntity insertData(@RequestBody String body) {
        System.out.println("Controller insertData()有被呼叫到");

        Table1Bean bean = new Table1Bean();
        String key = "";
        String value = "";

        try {
            JSONObject jsonObject = new JSONObject(body);
            key = jsonObject.getString("key");
//            System.out.println("key = " + key);
            value = jsonObject.getString("value");
//            System.out.println("value = " + value);
        } catch (Exception e) {
            System.out.println("Key,Value轉換錯了，錯誤碼為：" + e);
        }

//        System.out.println("body.toString() = "+body.toString());
        bean.setBase64(body.toString());

        var result = table1Service.insert(bean);
        if (result != null) {
            return ResponseEntity.ok(result.toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
