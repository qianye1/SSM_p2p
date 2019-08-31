package controller;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.gxa.cdut.domain.Detail;
import com.gxa.cdut.domain.UserInfo;
import com.gxa.cdut.service.IAccountService;
import com.gxa.cdut.service.IDetailService;
import com.gxa.cdut.service.IUserInfoService;
import com.gxa.cdut.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.alipay.api.AlipayConstants.*;

@Controller
public class RechargeController {

    @Autowired
    IAccountService accountService;
    @Autowired
    IDetailService tblUserService;

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    private final static String app_id = "2016101000651952";

    // 商户私钥，您的PKCS8格式RSA2私钥
    private final static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCFAJfMep2cu4SRpxwpKYhi3/Qy/W0dOOXkkB1izh7ZkEtEw9r4KGadnJaWR1Yzz2jTOA1d8bW+uDJ6gOx2Fgp+GhvQrc2/TpY6/VL4gkTj2w58qH+7OXoMXTOM6taB7QxiiLtr17a4KncKTQ1sHxwOc6GyCDSg8urhIWOSyxEEfff8j4EZuwzaBTA9AB9qssCIE5s19UxUL4HFgyQsCufoAJt6c8Jull8rsJ1a67fL87ebROizbhvAEFDfub+3/5Si+ssJAL3mj23jqpAzIXim45N/l/pzPgCNeYfpRA1M1qqhDZY3ELz3DELC8HCt1RmuS9giJXdUuB7jFIqqk+ZnAgMBAAECggEAeUdBJEPq1LBpAifPlfk5VG/CzXwpsyve9bw0D5TErL7W4610TjUI3U+cQQxrnnLSs0e5aKCviWmNHgfRlxwiFTrZgj/6DSgr9T5qGhEvoFZvr1w2nRKcKAoUp7q8P11YncDwpjXdd7HBOrNoOd9kRglKuHyjAOCBLSi1E3X33o9tNgGY5RtlNi5bolv6e7iiOkYT8W4rDyZ3UaIJ8mNMrnImaR+9EPtHuFLJN71/sLPRZ74oDzDUb2ZesP/LhskZ89ZOfQnRDBlin75lnQ+fQ3wW9ffs4H5FUeKaWGVufsyIXV7JhhxslmCUKiyA6JQ2Ocv65gfs2yLf5jFtlFHwAQKBgQDK9XgQhe6WJ1YmH6kOeQJxIWiLbeDUWODhIL01OuJQtayq9sGwPs/cxagtjcUBCY00V7Bn71Cl9AGJEWKZcToAwm/9yax0AUhLYKNx9u8//t3d7AHOKbEzqPoAcJLmtzKlrkNGv1gmNXAAJZdu1DRJ/aUNCHSFvT/BtUmG7om5gQKBgQCnwtR65fX0iD3e7knZ8P1taNNwmPu9j+Wp7jV33h2yVM8WKeF5iwk7GcVCln7TWT5oRyVmLwAtfExoKPU4NLnJE/JbYwDh9l5OnO6OuufeyTdJwMKBuL53shYytTbps8BdUtqX9n8AxuZx3JumZRY6Eu38tx4MYMaXICXQpaED5wKBgDbqppsp8j3U1P2p0W+mFKAR3xzjFVO/3NFtr2So0zXtgpQcApqy1EGzhecVljnKoNeJ5qSo35SX6pZxCtHkDkLIWc8885nMAwjKVE9w9pnFV7lFs9NZ+qW00jiuPUGKTn0AIxoQikSEbxLLGSeW6bMfm7pkEEtjws2iRQvqnX+BAoGADMqRMvG3yDZfKVFX9jMmz6HPhWVUxIZlR4ivMP2RnBhVVeZJl9/fC0+St9oLv4etd3v9QQZSaCVzGfoNgIScqdVIhGlPFuuXEz1p/x4HMfh7q7bz3bo9Y5kDkkg48KbRAwyfODkGRAPmbEYa5BwPDsyEsuEvfFggrEM6PudyGH8CgYEAuGRd5r3eMuizUAFxIay8jsAGXBBWQKiIUI281CNnWKoJ99UYEbEkxy56KQPdjxZjECt9wCutNk5B8fOpRmGCewg60cNpXfaHHGIC9owHRkg6AjZeSGVfvxWaBV8Oe1JQDjt9NGsHvqqVOnNKoHP/hCJbEW2ooY1VBzh4WxXxdkM=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private final static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhQCXzHqdnLuEkaccKSmIYt/0Mv1tHTjl5JAdYs4e2ZBLRMPa+ChmnZyWlkdWM89o0zgNXfG1vrgyeoDsdhYKfhob0K3Nv06WOv1S+IJE49sOfKh/uzl6DF0zjOrWge0MYoi7a9e2uCp3Ck0NbB8cDnOhsgg0oPLq4SFjkssRBH33/I+BGbsM2gUwPQAfarLAiBObNfVMVC+BxYMkLArn6ACbenPCbpZfK7CdWuu3y/O3m0Tos24bwBBQ37m/t/+UovrLCQC95o9t46qQMyF4puOTf5f6cz4AjXmH6UQNTNaqoQ2WNxC89wxCwvBwrdUZrkvYIiV3VLge4xSKqpPmZwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private final static String notify_url = "http://localhost:8080/notifyUrl";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private final static String return_url = "http://localhost:8080/notify_url.do";

    // 签名方式
    private final static String sign_type = "RSA2";

    // 字符编码格式
    private final static String CHARSET = "UTF-8";

    // 支付宝网关正式地址路径为https://openapi.alipay.com/gateway.do
    private final static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    private final static String FORMAT = "JSON";



    @RequestMapping("recharge")
    public String recharge(){
        return "Recharge";
    }


    @RequestMapping("alipay")
    public String alipay(String money, Model model,HttpServletResponse httpResponse) throws IOException {
        if (Integer.parseInt(money)>0){
            UserInfo userInfo = UserContext.getLoginInfo();
            Detail tblUser = tblUserService.selectById(userInfo.getId());
            AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, app_id, merchant_private_key, FORMAT, CHARSET,
                    alipay_public_key, sign_type);
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            // 在公共参数中设置回跳和通知地址
            request.setReturnUrl(return_url);
            request.setNotifyUrl(notify_url);
            // 付款金额
            String price = money;
            Double payPrice = Double.parseDouble(price);
            // 商户订单号，商户网站订单系统中唯一订单号，必填
            // 生成随机Id
            String out_trade_no = UUID.randomUUID().toString();
            // 付款金额，必填
            String total_amount = Double.toString(payPrice);
            // 订单名称，必填
            String subject = "RechargeMoney";
            // 商品描述，可空
            String body ="cc";
            //插入订单信息
            try {
//                productService.insert(money);
                accountService.updateById(userInfo.getId(),money);
            }catch(RuntimeException e){
                return "rechargefaild";
            }
            request.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
                    + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
            String form = "";
            try {
                form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
                System.out.println(form);
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
            httpResponse.setContentType("text/html;charset=" + CHARSET);
            httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
            httpResponse.getWriter().flush();
            httpResponse.getWriter().close();
        }
            return "rechargefaild";

    }

    //更新个人信息页面
    @RequestMapping("/notify_url")
    public String Notify(HttpServletResponse response, HttpServletRequest request,Model model) throws Exception {
        UserInfo userInfo = UserContext.getLoginInfo();
        model.addAttribute("account", accountService.selectById(userInfo.getId()));
        Detail tblUser = tblUserService.selectById(userInfo.getId());
        model.addAttribute("userinf",tblUser);
        return "personal";
    }
}