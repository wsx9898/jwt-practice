package tw.com.ispan.cma.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tw.com.ispan.cma.domain.MembersBean;
import tw.com.ispan.cma.service.MembersService;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@CrossOrigin
public class KevinLoginController {

    @Autowired
    private MembersService membersService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = {"/kevinLogin"})
    public ResponseEntity<?> login(Locale locale, Model model, String username, String password, HttpSession session) {
        System.out.println("login有被呼叫到");
//接收資料
//驗證資料
        Map<String, String> errors = new HashMap<String, String>();
        model.addAttribute("errors", errors);

        if (username == null || username.length() == 0) {
            System.out.println("需要輸入帳號");
            errors.put("username", messageSource.getMessage("login.username.required", null, locale));
        }
        if (password == null || password.length() == 0) {
            System.out.println("需要輸入密碼");
            errors.put("password", messageSource.getMessage("login.password.required", null, locale));
        }
        if (errors != null && !errors.isEmpty()) {
            System.out.println("errors!=null有錯誤");
            errors.put("error", messageSource.getMessage("errors!=null有錯誤", null, locale));
//			return "私服器回應：有錯誤";

            //這是回傳404失敗
            ResponseEntity<?> entity = ResponseEntity.notFound().build();
            return entity;
        }
//呼叫model
        MembersBean bean = membersService.login(username, password);

//根據model執行結果，導向view
        if (bean == null) {
            System.out.println("可能我改寫memberDAO/Service/Hibernate其中有問題才拿不到bean");
            errors.put("password", messageSource.getMessage("login.failed", null, locale));
//return "私服器回應：查不到此帳號或密碼有錯誤";

            //這是回傳404失敗
            ResponseEntity<?> entity = ResponseEntity.notFound().build();
            return entity;
        } else {
            System.out.println("有成功驗證比對帳號密碼！！");
            System.out.println("session.getId() = " + session.getId());
            session.removeAttribute("user");
            session.setAttribute("user", bean);
//			return "私服器回應：有成功驗證比對帳號密碼！！，session.setAttribute";

            //這是回傳204成功
            ResponseEntity<?> entity = ResponseEntity.noContent().build();
            return entity;
        }
    }

    @PostMapping(path = {"/loginJson"})
    public ResponseEntity<?> loginByJson(@RequestBody String body, Locale locale, Model model, HttpSession session) {
        System.out.println("loginByJson有被呼叫到");
        String memberAccount = "";
        String memberPassword = "";
//接收資料
//驗證資料
        Map<String, String> errors = new HashMap<String, String>();
        model.addAttribute("errors", errors);


        try {
            JSONObject jsonObject = new JSONObject(body);
            memberAccount = jsonObject.getString("memberAccouunt");
            System.out.println("memberAccouunt = " + memberAccount);
            memberPassword = jsonObject.getString("memberPassword");
            System.out.println("memberPassword = " + memberPassword);
        } catch (Exception e) {
            System.out.println("轉換帳號密碼有錯誤");
            e.printStackTrace();
            errors.put("AccountPassword", "帳號跟密碼其中一個有錯誤");
        }

        //呼叫model
        MembersBean bean = membersService.login(memberAccount, memberPassword);

        //根據model執行結果，導向view
        if (bean == null) {
            System.out.println("可能我改寫memberDAO/Service/Hibernate其中有問題才拿不到bean");
//			return "私服器回應：查不到此帳號或密碼有錯誤";

            errors.put("beanNull", "bean == null");

            //這是回傳404失敗
            ResponseEntity<?> entity = ResponseEntity.notFound().build();
            return entity;
        } else {
            System.out.println("有成功驗證比對帳號密碼！！");
            System.out.println("session.getId() = " + session.getId());
            //把先前登入的帳密刪掉
            session.removeAttribute("user");
            //新增現在登入的這組帳密
            session.setAttribute("user", bean);
//			return "私服器回應：有成功驗證比對帳號密碼！！";


            //這是回傳201成功，並回傳成功resource的URI
            URI uri = URI.create("再來要跳轉的網址放這邊");
            ResponseEntity<MembersBean> entity = ResponseEntity.created(uri).body(bean);
            return entity;
        }
    }

    //這個給dashboard跨越session要id再跟MemberRestController要會員所有資料
    @PostMapping(path = {"/dashboardGetMemberId"})
    public ResponseEntity<String> dashboardGetMemberId(Model model, HttpSession session) {
        URI uri = URI.create("再來要跳轉的網址放這邊");
        System.out.println("session.getId() = " + session.getId());
        MembersBean bean = (MembersBean) session.getAttribute("user");
        bean.setMemberPassword("");

        //這是回傳201成功，並回傳成功bean.getMemberId()+""
        if (bean != null) {
            System.out.println("bean.getMemberId() = " + bean.getMemberId());
            ResponseEntity<String> entityString = ResponseEntity.created(uri).body(bean.getMemberId() + "");
            return (entityString);
            //這是回傳404失敗
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

//		成功：200 (OK)、message body包含1個resource的資料
//ResponseEntity<ProductBean> entity = ResponseEntity.ok().body(product);
//		失敗(resource不存在)：404 (Not Found)
//		失敗(resource id格式錯誤)：400 (Bad Request)

//204不代表成功或失敗
//		成功：204 (No Content)、message body空白(不包含已經刪除的resource資料)
//		失敗：204 (No Content)
//ResponseEntity<?> entity = ResponseEntity.notFound().build();

//ResponseEntity<?> entity = ResponseEntity.noContent().build();