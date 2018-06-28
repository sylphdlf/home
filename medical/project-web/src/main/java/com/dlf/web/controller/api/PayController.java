//package com.dlf.web.controller.api;
//
//import com.dlf.business.manager.pay.PayService;
//import com.dlf.web.controller.BaseController;
//import com.pingplusplus.Pingpp;
//import com.pingplusplus.exception.PingppException;
//import com.pingplusplus.model.Charge;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/pay")
//public class PayController extends BaseController {
//
//    private Log logger = LogFactory.getLog(PayController.class);
//
//    @Autowired
//    PayService payService;
//
//    @RequestMapping(value = "/createPay" ,method = RequestMethod.POST)
//    @ResponseBody
//    public String createPay(){
////        Pingpp.apiKey = "sk_test_S0Si9CjH0K8GGKef58Lyz5e5";
//        Pingpp.apiKey = "sk_live_vvrPK090mv50CWDaLCv5i9uT";
//        Pingpp.privateKey = "MIICXQIBAAKBgQDMlMtr6RMmVFrUCi+ijxFoKnDjPKUsYnXu4WE9LwJIkspRFXHGSWOSoAfdTT4b/2YPeweCRC3sPAQi1x/59nL/S/T+RHLnAwKfdHm7RfzF4QlnF+9qW4H6ZnDCQk7eT+z3xO3GJXPDu5Pn51DJ3JXP278kCsXUYP0lVDALGc+rzQIDAQABAoGAFqtONzDmfFLQlbpjOOIdjatWQvfPXbGzW7sBqWPyHtSVMpLrvosXS4IMHQuYHEYaqxB47ohbhlfIJYws8OfMKicDW5BpJ4iuxFxMkl4e0/L+r3O8XWneMrhe8BBZeTvwGIWS7hiTmwzOmjWEwWgv4bFzcun3sP0TaQlATdks6QECQQD3Zr3ZIUr9wixq6A7h+MQUTvhoGD0tDOLHz4toANuaHPvUou2ZJ58c6jbclPBwAUMuJ9WDwGmqxmm1EFz5eD6hAkEA07EPbVaL84ttyFHqDqnmhVsYYAlVtos1tG1y8oD/6Sm4MimUY1haeGQ1lc3dnwQFQ3E4wyPFNjwoYsur0Wu5rQJBAM3Wtrg0Ig+1gX1FLDTI5GM9PSJmoGeeJAU44fUbWZsE6GYHVQYrWBAXsqOea9nUww9g5x3idkg3IvYZ8jFB4WECQQCl0VCbPaq8Z5/jxL5JJC6MkPGD8GvyZBL6NK1QZVmPwv0kVcuFEeUz3pLWt/38HAMfc+WZfUByHicoVA9PKEm1AkBds0SEJkyXYmxDikN3jYAfScmtejMw38K4XSN3KhRCCuyEgZcLDPn6vsTgG1wPvXMTbHr602VgrIX4KJws/c00";
//        Map<String, Object> chargeMap = new HashMap<String, Object>();
//        // 某些渠道需要添加extra参数，具体参数详见接口文档
//        chargeMap.put("amount", 1);
//        chargeMap.put("currency", "cny");
//        chargeMap.put("subject", "Your Subject");
//        chargeMap.put("body", "Your Body");
//        chargeMap.put("order_no", "201806040003");
//        chargeMap.put("channel", "alipay_pc_direct");
//        chargeMap.put("client_ip", "127.0.0.1");
//        Map<String, String> app = new HashMap<String, String>();
//        app.put("id", "app_LWz9i5DKWHG8Lyn5");
//        chargeMap.put("app", app);
//        //extra
//        Map<String, Object> extra = new HashMap<String, Object>();
//        extra.put("success_url", "http://www.baidu.com");
//
//        chargeMap.put("extra", extra);
//        try {
//            //发起交易请求
//            Charge charge = Charge.create(chargeMap);
//            System.out.println(charge.toString());
//            return charge.toString();
//        } catch (PingppException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    @RequestMapping(value = "/payNotify")
//    public String payNotify(HttpServletRequest request){
//        try {
//            System.out.println("支付异步推送开始");
////        Map<String, String[]> parameterMap = request.getParameterMap();
////        System.out.println(parameterMap.toString());
//            payService.saveNotifyLogs(request.getInputStream());
//            System.out.println("支付异步推送结束");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return "success";
//    }
//}
