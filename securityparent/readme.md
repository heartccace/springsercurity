springBoot和springCloud版本对应关系：https://spring.io/projects/spring-cloud
在字符串中使用表达式：https://github.com/json-path/JsonPath


用户信息获取：UserDetailsService
处理用户校验逻辑: UserDetails
用户密码加密: PasswordEncoder

用户自定义登录界面：

在resources下创建resources、static文件夹，登录界面放到resources下面，css、js、图片等放到static下，注意在html中中引用到static文件夹中的文件时，html中不要加static前缀



认证流程：

UsernamePasswordAuthenticationFilter

```
public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: " + request.getMethod());
		}

		String username = obtainUsername(request); //从request中获取用户名
		String password = obtainPassword(request); // 从request中获取密码

		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}

		username = username.trim();

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);// 创建token

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);
		// 校验用户
		return this.getAuthenticationManager().authenticate(authRequest);
	}
```



AuthenticationManager对象是一个容纳AuthenticationProvider对象的容器。

### rememberme功能

认证成功后 调用RemeberMeService -> TokenRepository -> 将Token写入数据库

服务请求 -> RememberMeAuthenticationFilter-> 读取cookie中的Token





### OAuth协议中的授权模式

1. 授权码模式(authentication code)
2. 简化模式(implicit)
3. 密码模式(resource owner password credentials)
4. 客户端模式(client credentials)

OAuth角色：

privider：服务提供商 (serviceProvider 实现：AbstractOAuth2ServiceProvider)

- 认证服务器
- 资源服务器(用户基本信息)	

资源所有者(用户)

第三方应用



1. 用户访问第三方应用，第三方应用将用户导向服务提供商，让用户授权
2. 用户同意授权
3. 服务提供商返回授权码给第三方应用
4. 第三方应用携带授权码请求申请令牌
5. 发放令牌
6. 获取用户信息
7. 根据用户信息构建Authentication并放入SecurityContext中

OAuth2Operations（OAuth2Template完成OAuth的2-5流程）

AbstractOAuth2ApiBinding 对用户信息获取封装

Connection(OAuth2ConnectionFactory)：在第三方应用封装获取的用户信息

ConnectionFactory(OAuth2ConnectionFactory)

ApiAdapter 将不同提供商提供的信息转化成相应的信息

UserConnectionRepository(JdbcUsersConnectionFactory) 将应用用户于服务商用户相关联





Spring Security OAuth2

支持小程序、支持app、支持web应用

springSecurity在过滤器中加入OAuth2AuthenticationProcessingFilter过滤器进行验证





spring security Oauth2核心源码



获取令牌的请求 ->  TokenEndpoint(类似于controller)-> ClientDetailsService(InMemoryClientDetailsService)  -> ClientDetails -> TokenRequest -> TokenGranter(CompositeTokenGranter) -> OAuth2Request/Authentication -> OAuth2Authentication -> AuthrizationServerTokenService(DefaultTokenService)  { TokenStore TokenEnhancer}-> OAuth2AccessToken

ClientDetails封装了第三方应用的信息

TokenRequest封装了请求的信息并将ClientDetails放到里面

TokenRequest调用TokenGranter

TokenGranter封装了四种授权模式的实现 根据请求头中的grant_type获取具体实现，

授权模式的具体实现中包含了两个对象（OAuth2Request 和 Authentication）

OAuth2Request 封装了第三方应用客户端的信息

Authentication封装了请求中授权用户的信息

OAuth2Request 和Authentication组成了OAuth2Authentication 对象

AuthrizationServerTokenService根据传入的OAuth2Authentication 对象完成令牌的生成和储存

TokenEnhancer对生成的令牌做加强或者调整





### BasicAuthenticationFilter用于对请求头做处理