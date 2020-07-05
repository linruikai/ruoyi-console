package com.ruoyi.controller;

import com.ruoyi.constant.Constants;
import com.ruoyi.controller.view.BaseResponse;
import com.ruoyi.entiy.body.LoginBody;
import com.ruoyi.utils.Base64;
import com.ruoyi.utils.VerifyCodeUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: linruikai
 * @date: 2020/6/27 18:16
 */
@RestController
public class LoginController {

  @Autowired
  private StringRedisTemplate redisTemplate;

  /**
   * 生成验证码
   */
  @GetMapping("/getCode")
  public BaseResponse getCode(HttpServletResponse response) throws IOException {

    // 生成随机字串
    String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
    // 唯一标识
    String uuid = UUID.randomUUID().toString();
    String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
    redisTemplate.opsForValue()
        .set(verifyKey, verifyCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
    // 生成图片
    int w = 111, h = 36;
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
    try {
      HashMap ajax = new HashMap<>();
      ajax.put("uuid", uuid);
      ajax.put("img", Base64.encode(stream.toByteArray()));
      System.out.println(uuid);
      return BaseResponse.success(ajax);
    } catch (Exception e) {
      e.printStackTrace();
      return BaseResponse.fail("获取验证码失败");
    } finally {
      stream.close();
    }
  }

  @PostMapping("login")
  public BaseResponse login(@RequestBody LoginBody loginBody) {
    System.out.println(loginBody.getUsername());
    System.out.println(loginBody.getPassword());
    return BaseResponse.success();
  }
}
