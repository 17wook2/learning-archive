JDBC(Java Database Connectivicy) : Persistence Layer 구현을 위한 자바 API
자바 어플리케이션에서 DBMS에 종속적이지 않게 JDBC API만을 이용하여 DB작업을 처리
각각의 DBMS는 이를 구현한 JDBC 드라이버를 제공한다.

## JDBC를 이용해 데이터를 가져오는 과정

JDBC를 사용해 데이터를 가져오기 전에 해야할 작업들은 다음과 같다

1. DB와 연결을 위한 연결 파라미터 설정
2. 연결 오픈
3. SQL문 선언
4. Statement 준비와 실행
5. 결과를 반복하는 루프 설정
6. 각 이터레이션에대한 작업 수행
7. 예외처리

## Spring JDBC Template

위의 복잡한 작업들을 해결하기 위해 Persistence Framework인 JDBC Template를 통해 간단한 작업으로 데이터베이스와 연동되는 시스템을 개발할 수 있다.
필자가 생각한 JDBC Template를 씀으로써 장점은 다음과 같다.

- 스프링을 통한 빈 주입방식의 데이터 연결 설정
- SQL Mapper를 통한 객체와 필드와의 매핑 및 RowMapper 재활용

JDBC Template를 통해 연결 파라미터 정의, SQL 문 지정, 파라미터 세팅, 각 이터레이션에 대한 작업 수행만 해주면 SQL Mapper가 SQL과 객체를 매핑해주기 때문에 번거로운 작업이 덜어진다.

### JDBC Template를 이용한 Select 구문

```
   private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers() {
        String getUsersQuery = "select userId,status,latitude,longitude from user";
        return this.jdbcTemplate.query(getUsersQuery, (rs, rowNum) ->
        new GetUserRes(rs.getInt("userId"), rs.getInt("status"),
        rs.getDouble("latitude"), rs.getDouble("longitude")));
    }

```

Spring을 통한 빈 주입방식으로 dataSource를 받고 JdbcTemplate 객체를 만들어 그 안에 주입해주었다. jdbcTemplate.query(SQL,RowMapper)를 통해 필드를 GetUserRes객체를 반환받았다.

### BeanPropertyRowMapper를 통해 RowMapper 재사용

### RowMapper

![https://velog.velcdn.com/images/wook2pp/post/a5b50986-2750-4bf6-91b5-e1a7d49e771e/image.png](https://velog.velcdn.com/images/wook2pp/post/a5b50986-2750-4bf6-91b5-e1a7d49e771e/image.png)

RowMapper Interface를 살펴보면 RowMapper의 구현체는 mapRow함수를 구현해야하고, 현재 행에 대해서 행을 가공할 함수를 구현하는것으로 나온다. 반환 타입은 T 타입으로 입력받은 타입으로 반환한다.

### BeanPropertyRowMapper

직접 RowMapper를 다 구현하기는 번거로운 작업이기 때문에 Spring에서 빈 객체로 등록된 것을 알아서 RowMapper로 구현해주는 RowMapper 구현체가 있다.

![https://velog.velcdn.com/images/wook2pp/post/5846eeba-0a94-49d2-99ec-30d1217f766d/image.png](https://velog.velcdn.com/images/wook2pp/post/5846eeba-0a94-49d2-99ec-30d1217f766d/image.png)

아래와 같이 코드를 작성하면 GetWishlistRes 타입의 RowMapper를 알아서 구현해 준다.

```
private final RowMapper<GetWishlistRes> getWishlistResRowMapper =
BeanPropertyRowMapper.newInstance(GetWishlistRes.class);

```

이를 사용하면 코드가 아래와 같이 줄어든다.

```
public List<GetWishlistRes> getWishlists(int userId) {
        String getWishlistsQuery = "select * from wishlists where userId = ?";
        return jdbcTemplate.query(getWishlistsQuery,getWishlistResRowMapper,userId);
    }

```

### JDBC Template를 이용한 Insert 구문

```
String createUserProfileQuery = "insert into user_profile
(userEmail, userName, userGender, userBirthDate, userAddress, userProfileImageUrl, userPhoneNumber, host)
values (?,?,?,?,?,?,?,?) where userId = ?";
        this.jdbcTemplate.update(
        createUserProfileQuery,
        new Object[]{postUserReq.getUserEmail(),postUserReq.getUserName(),postUserReq.getUserGender(),postUserReq.getUserBirthDate(),postUserReq.getUserAddress(),postUserReq.getUserProfileImageUrl(),postUserReq.getUserPhoneNumber(),postUserReq.getHost()}
        );
        return useridx;

```

Insert 구문도 SQL문을 작성해주고 넣을 값을 매핑해주면 된다. 하지만 같은 반복된 getMapping으로 코드가 상당히 길어지는 것을 볼 수 있다.

### SimpleJdbcInsert를 통한 간편한 데이터 Insert

위의 JDBC template를 통해 insert 구문을 작성하면 반복되는 코드 작성이 많아져 가독성이 많이 떨어진다. 이를 해결하기 위해 SimpleJdbcInsert 를 사용하겠다.

![https://velog.velcdn.com/images/wook2pp/post/9ed926f5-94a0-4aaa-a27b-b6ca569b638b/image.png](https://velog.velcdn.com/images/wook2pp/post/9ed926f5-94a0-4aaa-a27b-b6ca569b638b/image.png)

SimpleJdbcInsert 설명을 보면 간편한 insert를 하게 해주고, 해주어야 할것은 테이블의 이름과 column name과 column values를 담은 map 객체를 반환해주면 된다고 한다.

아래의 코드와 같이 받아온 데이터소스를 통해 SimpleJdbcInsert 객체를 만들어준다.

```
this.wishlistJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("wishlists")
                .usingGeneratedKeyColumns("wishlistId");

```

만든 SimpleJdbcInsert객체 .execute를 통해 Insert를 수행할 수 있는데, 파라미터로 직접 map을 넣거나 혹은 SqlParameterSource를 구현한 객체를 넣어주면 된다.

![https://velog.velcdn.com/images/wook2pp/post/4824bb89-d161-4bc1-b88b-6b6d69118a43/image.png](https://velog.velcdn.com/images/wook2pp/post/4824bb89-d161-4bc1-b88b-6b6d69118a43/image.png)

```
public int createWishlist(PostWishlistReq postWishlistReq) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(postWishlistReq);
        return wishlistJdbcInsert.executeAndReturnKey(params).intValue();
    }

```

### SqlParameterSource Interface 를 구현한 BeanPropertySqlParameterSource를 통한 파라미터 매핑

SqlParameterSource를 보면 파라미터 값을 SQL parameter로 넣어주기 위한 인터페이스이다. Map이나 JavaBean을 감싸기 위한 의도라고 한다.

![https://velog.velcdn.com/images/wook2pp/post/988dae55-ec9a-4842-b351-22e0c4a03bc9/image.png](https://velog.velcdn.com/images/wook2pp/post/988dae55-ec9a-4842-b351-22e0c4a03bc9/image.png)

BeanPropertySqlParameterSOurce를 보면 SqlParameterSource의 구현체로 자바 빈 객체로부터 정보를 받아와 파라미터 값과 매핑해준다는 것을 볼 수 있다.

![https://velog.velcdn.com/images/wook2pp/post/0e14cd67-5232-4a76-a63b-fc8346500d7f/image.png](https://velog.velcdn.com/images/wook2pp/post/0e14cd67-5232-4a76-a63b-fc8346500d7f/image.png)

결론적으로 Insert 구문의 코드가 아래와 같이 두줄만에 깔끔하게 끝난다.

```
public int createWishlist(PostWishlistReq postWishlistReq) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(postWishlistReq);
        return wishlistJdbcInsert.executeAndReturnKey(params).intValue();
    }

```

### NamedParameterJdbcTemplate

Sql 구문에 ?를 통해서 parameter 값을 넣는데 ? 값은 어떤 값을 넣는건지 명확히 와닫지 않는 경우가 많다.
그렇기 때문에 NamedParameterJdbcTemplate를 사용하는것이 편하다.
아래 설명을 읽어보면 JDBC 기능을 가지고 있고, ? 대신 이름으로 파라미터를 쓸 수 있다고 되어있다.

![https://velog.velcdn.com/images/wook2pp/post/f6a6ce38-6b1f-44a1-977d-65f059e82b09/image.png](https://velog.velcdn.com/images/wook2pp/post/f6a6ce38-6b1f-44a1-977d-65f059e82b09/image.png)

아래와 같이 ?가 들어간 부분에 parameter 이름을 명시적으로 써주어 어떤 값이 들어오는지를 확실히 알 수 있다.