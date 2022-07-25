도메인은 ip에 특정 네이밍을 통해 ip를 쉽게 구분할 수 있게 해준다.
도메인의 풀 네임은 FQDN(Fully Qualified Domain Name) 으로 불리며, 이는 호스트와 도메인을 같이 표기한 것이다.
예를 들어 www.wook2.xyz에서 www가 호스트명이 되고, 가비아에서 산 도메인인 wook2.xyz가 도메인명이 되는 것이다.

## 서브도메인이란?

기본 도메인에 호스트만 다르게 붙여서 여러개의 도메인을 사용할 수 있게 해주는 방식이다.
dev.wook2.xyz, test.wook2.xyz 와 같이 기존의 도메인에 다른 이름의 호스트만 붙여서 사용하는 것이다. 즉 기존의 도메인에 가지치기를 통하여 도메인 1개를 바탕으로 여러개의 도메인을 사용할 수 있다.

## AWS Route53에 서브도메인 추가하기

Route53에 들어가 새 레코드를 생성해주면 되는데, 레코드 유형에 CNAME을 사용했다.
CNAME 설명을 보면 다른 도메인 이름과 일부 AWS 리소스로 트래픽 라우팅을 한다고 하는데,
예를 들어 값에 도메인 wook2.xyz를 입력하면 해당 도메인으로 라우팅이 되는 것이다.

![https://velog.velcdn.com/images/wook2pp/post/b7ccc932-6894-4bbc-b6d2-e2ab3702a6ef/image.png](https://velog.velcdn.com/images/wook2pp/post/b7ccc932-6894-4bbc-b6d2-e2ab3702a6ef/image.png)

아래와 같이 2개의 서브도메인을 통해 다른 도메인으로 라우팅하게 하였다.

![https://velog.velcdn.com/images/wook2pp/post/7b02fd0c-6f7b-4442-a91f-220b2619481f/image.png](https://velog.velcdn.com/images/wook2pp/post/7b02fd0c-6f7b-4442-a91f-220b2619481f/image.png)

---

## nginx 설정하기

서브 도메인 설정이 끝났고, 이제 서버에서 해당 요청을 처리해주면 된다.
nginx는 가상 호스트 방식을 이용해 해당 요청을 구분하는데, 가상 호스트는 말그대로 가상의 호스트가 존재하는 것처럼 행동하는 것이다.
dev.wook2.xyz , test.wook2.xyz 모두 wook2.xyz로 라우팅 되는데, 이때 클라이언트 요청 헤더에 도메인 명이 같이 들어가 서버에서는 이를 바탕으로 다른 페이지를 서비스 할 수 있다.
실제는 하나 컴퓨터에서 호스팅하지만, 이때 클라이언트의 입장에서 바라보면 마치 여러대의 컴퓨터가 호스팅하는 것처럼 느낀다는 것이다.

### nginx 기본 설정 파일

/etc/nginx/nginx.conf 파일이 nginx 기본 설정들이 모두 들어가 있는 곳이다.
해당 파일의 http 블록을 보면 include /etc/nginx/conf.d/*.conf, include /etc/nginx/sites-enabled/*  에 있는 설정 파일을 참조하겠다고 되어있다.

![https://velog.velcdn.com/images/wook2pp/post/fd9eee92-1c17-49cd-bb0a-52305891f9ae/image.png](https://velog.velcdn.com/images/wook2pp/post/fd9eee92-1c17-49cd-bb0a-52305891f9ae/image.png)

### sites-enabled, sites-available

- sites-available
설정을 저장하는 곳으로 이곳에 설정을 만든 것은 실제로 nginx에 반영되지는 않는다. 반영하기 위해서는 sites-enabled에 설정파일을 복사 또는 심볼릭링크를 걸어주어야 한다.
- sites-enable
sites-availiable에 작성한 설정을 사용하는 폴더

## 서브 도메인 설정하기

```
$ sudo cd /etc/nginx/sites-available
$ sudo vi dev.conf

```

conf 파일에 서버 블록을 생성하고 설정파일을 작성한다.

### 서브 도메인에 ssl 적용하기

let's encrypt를 통해 메인 도메인에 ssl을 적용했는데, certbot이 default 파일에 자동으로 설정한 ssl 설정을 참조하여 적용하였다.

![https://velog.velcdn.com/images/wook2pp/post/87dc2017-1072-4600-8663-83607094c31a/image.png](https://velog.velcdn.com/images/wook2pp/post/87dc2017-1072-4600-8663-83607094c31a/image.png)

기본 서버 블록을 작성했다.

```
server {
	server_name dev.wook2.xyz;

    location / {
      root /home/ubuntu/nginx_pages/dev;
      index index.html;
      try_files $uri $uri/ =404;
    }
}

```

### certbot으로 https 적용해주기

직접 서버블록을 작성할 필요없이 certbot을 통해 작성한 설정파일에 SSL을 적용시킬 수 있다.

```
sudo certbot --nginx -d dev.wook2.xyz

```

certbot 설정이 끝나면 dev.conf 파일이 다음과 같이 바뀐다

![https://velog.velcdn.com/images/wook2pp/post/929882c5-23cd-43e6-a652-b2cbb9ba7f83/image.png](https://velog.velcdn.com/images/wook2pp/post/929882c5-23cd-43e6-a652-b2cbb9ba7f83/image.png)

### 심볼릭링크 걸어주기

```
sudo ln -s /etc/nginx/sites-available/dev.conf /etc/nginx/sites-enabled/

```

### nginx 설정 테스트하기

```
sudo nginx -t

```

### nginx 재시작

```
sudo service nginx restart

```

### https 확인

아래와 같이 https가 잘 적용된 모습이고, http로 들어가도 https로 리다이렉트 된다.

![https://velog.velcdn.com/images/wook2pp/post/0d32358a-f2c0-4264-bc21-c9f566e3379e/image.png](https://velog.velcdn.com/images/wook2pp/post/0d32358a-f2c0-4264-bc21-c9f566e3379e/image.png)