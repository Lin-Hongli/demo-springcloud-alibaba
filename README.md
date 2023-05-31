# demo-springcloud-alibaba
springcloud项目框架

###部署
1.准备Nginx配置 
1) /usr/local/nginx/conf 下先创建nginx.conf（否则无法挂载nginx容器的nginx.conf）
```shell
1. mkdir 333 -p /usr/local/nginx/conf
2. cd /usr/local/nginx/conf
3. vi nginx.conf
```
2) 写入配置
```shell
user  nginx;
worker_processes  auto;

error_log  /var/log/nginx/error.log notice;
pid        /var/run/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    keepalive_timeout  65;

    #gzip  on;

    include /etc/nginx/conf.d/*.conf;

    
    server{
       listen 80;
       server_name localhost;
       charset utf-8;

       location / {
          root  /usr/share/nginx/html/;
          try_files $uri $uri/ =404;
          index  index.html index.htm;
       }
       
       error_page   500 502 503 504  /50x.html;
       location = /50x.html {
           root   html;
       }
    }
}

```
2.运行docker-compose
