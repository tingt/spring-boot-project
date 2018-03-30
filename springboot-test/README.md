# springboot-test
测试用例，包含controller测试及service测试
# controller测试
新建测试基类,加载所需资源
```java
@RunWith(SpringRunner.class)
@SpringBootTest
@Profile({ "test" })
public class BaseControllerTest {
    @Autowired
    public  WebApplicationContext wac;
    public MockMvc mvc;

    @Before
    public void setupMockMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
    }
}
```
针对每一个controller类建立测试类，测试类继承测试基类
```java
@Test
public void getUser() throws Exception{
    mvc.perform(MockMvcRequestBuilders.get("/user/1")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)
    )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("ttt"))
            .andDo(MockMvcResultHandlers.print());
}
```
MockMvcRequestBuilders建立请求，后面可跟get/pos/等请求类型。
对于含有@Responsebody的请求参数，需要先将其转换成json格式
```java
@Test
@Transactional
public void add() throws Exception{
    User user=new User();
    user.setUsername("spring");
    Gson gson = new Gson();
    String json = gson.toJson(user);
    mvc.perform(MockMvcRequestBuilders.post("/user/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json.getBytes())
    )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
}
```
@Transactional支持事务，测试完成后会自动回滚，不会污染测试环境

# service测试
新建测试基类
```java
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Profile({ "test" })
public class BaseServiceTest {
}
```
@Profile指明当前运行环境
为每个service方法建立测试类
```java
@Test
public void getUser(){
    User user=userService.getUser(1);
    Assert.assertThat(user.getUsername(),is("ttt"));
}
```
直接注入service,调用即可。