EC2를 이용해 해당 서버에 데이터베이스 서버를 구축할 수 있지만, 서버의 규모가 커져서 여러개의 서버가 필요하다면 데이터베이스 접근에 문제가 생길 것이다. 각각의 서버가 가지는 데이터가 항상 서로 같게 유지되어야 하기 때문이다. 
아마존에서는 AWS RDS를 통해 데이터베이스 서버를 따로 분리해 관리할수 있게 해주는 서비스를 제공한다.

## EC2에서 RDS 접근하기
RDS에 어떻게 접근할 것인가에 대해서는 다양한 방법이 있는데, 그중에서 vpc를 이용해 EC2로만 RDS에 접근할 수 있게 만드는 방법이 있었다.

>![](https://velog.velcdn.com/images/wook2pp/post/8347a219-4a12-4be1-827c-aae3c4a77aef/image.png)
출처: https://ndb796.tistory.com/224 [안경잡이개발자:티스토리]

내가 참고한 블로그에서는 EC2, RDS각각 서로 다른 서브넷을 생성했고, 각 서브넷에서 각각 보안그룹을 생성하여 EC2에서는 http https등의 요청에 대한 인바운드 규칙을 설정했고 RDS에서는 EC2에서 만든 보안그룹에서 오는 3306포트 요청만 받도록 보안그룹을 생성했다.
이런방식으로 설계하면 EC2에서 오는 db연결 요청만 받을 수 있고, 외부에서는 접속하지 못하는 DB가 완성된다.


## RDS 생성

RDS 생성화면에 default vpc와 서브넷 그룹을 설정하고 퍼블릭 액세스를 허용하고 데이터베이스를 생성한다.
![](https://velog.velcdn.com/images/wook2pp/post/8fedd484-87d9-499d-bf53-e4157e941d8f/image.png)

## 파라미터 그룹 설정 

RDS를 생성하면 파라미터 그룹에서 아래의 설정들을 필수적으로 해주어야 한다.
- time_zone (시간 설정)
- character Set (문자 인코딩 설정)
- collation (데이터의 정렬기준을 위한 설정)

RDS 인스턴스를 생성하면 기본으로 생성된 파라미터 그룹이 하나 있다. 
![](https://velog.velcdn.com/images/wook2pp/post/d8471778-1619-453a-bdb4-e38e46b7b042/image.png)

### time_zone 값을 Asia/Seoul로 변경
![](https://velog.velcdn.com/images/wook2pp/post/e7e802ca-d327-496c-a53f-d45cbeb16df6/image.png)

### 문자 인코딩 설정
char 로 검색후 나오는 모든 값을 utf8mb4로 설정해준다. 이모지 입력을 해결하기 위해 utf8mb4라는 charset이 나오게 되었다.
![](https://velog.velcdn.com/images/wook2pp/post/081326e5-8ee9-4154-b079-6e657cb62941/image.png)


### collation 설정

![](https://velog.velcdn.com/images/wook2pp/post/f27f6213-f958-4cce-9884-4f23050c4b0b/image.png)
![](https://velog.velcdn.com/images/wook2pp/post/f3dcb0c2-cbb6-4e92-82d4-a5556cf45adc/image.png)

### 데이터베이스에 적용하기
만든 RDS 설정에서 파라미터 그룹을 바꾸어 준다.
![](https://velog.velcdn.com/images/wook2pp/post/711c100a-07e5-4417-8446-e97e481d33d1/image.png)

### RDS 보안그룹 설정하기
보안그룹 인바운드 규칙에 일단 내 IP로 설정해 놓고 datagrip으로 접속해보았다.
![](https://velog.velcdn.com/images/wook2pp/post/1ee4a05b-ba42-4800-a490-023601b7bcca/image.png)

### Datagrip으로 연결하기
host에는 rds 엔드포인트를 적어주고 user와 password에는 rds 생성시 만든 username과 password를 적어준다.
![](https://velog.velcdn.com/images/wook2pp/post/2af5e247-4ef6-40f0-8220-1cd43d6da1ef/image.png)
설정이 잘 된채로 연결이 잘 되었다.
![](https://velog.velcdn.com/images/wook2pp/post/fb252d43-149b-49bd-916f-35c4b146dad4/image.png)
