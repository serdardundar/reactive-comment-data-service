# reactive-comment-data-service

This project gets generated comments from the project [webflux-comment-service](https://github.com/serdardundar/webflux-comment-service) and saves them to mongodb and redis. Also an html page exists to show these saved comments.

### Prerequisites

Needed softwares:

* Any Java IDE (Intellij Recommended)
* Docker


### How to set up mongodb and redis

#### Mongodb setup

After installing docker, we can run mongodb image via docker. Here is the command:

```
docker run -p 27017:27017 -d mongo
```

29017 is the standard port for mongodb. We can see the running image by this command:
```
docker ps
```
Working image as seen:

```
a7098f62cd6b mongo "docker-entrypoint.s…"  5 days ago  Up 5 days  0.0.0.0:27017->27017/tcp   modest_montalcini
```

To reach mongodb data using an gui, i prefer to use Robo 3T. You can download [here](https://robomongo.org/download).

#### Redis setup

Like mongodb, docker is only tool we need to run redis. Here is command:

```
docker run -p 6379:6379 -d redis
```

Working image as seen:
```
69d0f34107eb redis "docker-entrypoint.s…" 13 hours ago  Up 13 hours   0.0.0.0:6379->6379/tcp   quirky_ride
```
6379 is the standart port for redis.

### Running the Project

After installing required tools, we can run project and see what's going on. This project works on 8080 port and comment generator project works on 8081 port. Our purpose is to get data from webflux-comment-service and save it to mongodb and redis, finally show them on some webpage. [http://localhost:8080](http://localhost:8080) is the home page.








