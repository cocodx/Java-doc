https://jwt.io/libraries?language=Java

引入依赖
```java
<dependency>
    <groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>3.2.0</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.7.0</version>
</dependency>
```

封装工具类JwtUtils  
##### 生成
```java
/**
 * token
 */
public static final int JWT_ERRCODE_NULL = 400;   //token不存在
public static final int JWT_ERRCODE_EXPIRE = 401; //token过期
public static final int JWT_ERRCODE_FAIL = 402;   //验证失败

/**
 * JWT
 */
public static final String JWT_SECERT = "234890asdfjka112vdnmew4isdf12";
public static final long JWT_TTL = 60 * 60 * 1000; //毫秒，60分钟
public static final long TEST_JWT_TTL = 60 * 1000; //毫秒，60秒
```

```java
public class JwtUtils {

    /**
     * 签发JWT
     * @param id
     * @param subject 可以是json数据，尽可能的少
     * @param ttlMillis
     * @return
     */
    public static String creteJWT(String id,String subject,long ttlMillis){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)  //主题
                .setIssuer("yyds")    //签发者
                .setIssuedAt(now)     //签发时间
                .signWith(signatureAlgorithm,secretKey);  // 签名算法以及密钥
        if (ttlMillis>=0){
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate);//设置过期时间
        }
        return builder.compact();
    }

    public static CheckResult validateJWT(String jwtStr){
        CheckResult checkResult = new CheckResult();
        Claims claims = null;
        try{
            claims = parseJWT(jwtStr);
            checkResult.setSuccess(true);
            checkResult.setClaims(claims);
        } catch (ExpiredJwtException e) {
            checkResult.setErrCode(SystemConstant.JWT_ERRCODE_EXPIRE);
            checkResult.setSuccess(false);
        } catch (SignatureException e) {
            checkResult.setErrCode(SystemConstant.JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        } catch (Exception e) {
            checkResult.setErrCode(SystemConstant.JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        }
        return checkResult;
    }

    /**
     * 解析JWT字符串
     * @param jwtStr
     * @return
     */
    public static Claims parseJWT(String jwtStr) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwtStr)
                .getBody();
    }

    /**
     * 生成加密key
     * @return
     */
    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.decode(SystemConstant.JWT_SECERT);
        SecretKey key = new SecretKeySpec(encodedKey,0,encodedKey.length,"AES");
        return key;
    }

    public static void main(String[] args) {
        //后端生成jwt
        String jwtStr = creteJWT("1", "jack",SystemConstant.TEST_JWT_TTL);
        System.out.println(jwtStr);
        //后端验证token
        CheckResult checkResult = validateJWT(jwtStr);
        System.out.println(checkResult.isSuccess());
        System.out.println(checkResult.getClaims());
        Claims claims = checkResult.getClaims();
        System.out.println(claims);
        System.out.println(claims.getSubject());
        //刷新token
        Claims claims2 = validateJWT(jwtStr).getClaims();
        String jwtStr2 = creteJWT(claims2.getId(),claims2.getSubject(),SystemConstant.TEST_JWT_TTL);
        System.out.println(jwtStr2);
    }

}
```
##### 总结
对比token和redis，自身可以存储载荷，不需要去数据库获取，减少代码量。