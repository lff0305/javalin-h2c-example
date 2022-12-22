This is a simple example for how to create a http2c server on javalin 5.2

Test with `curl` for `h2c`: 
```
curl -v --http2 localhost:8080/
*   Trying localhost:8080...
* Connected to localhost (localhost) port 8080 (#0)
> GET / HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.74.0
> Accept: */*
> Connection: Upgrade, HTTP2-Settings
> Upgrade: h2c
> HTTP2-Settings: AAMAAABkAAQCAAAAAAIAAAAA
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 101 Switching Protocols
< Upgrade: h2c
< Connection: Upgrade
* Received 101
* Using HTTP2, server supports multi-use
* Connection state changed (HTTP/2 confirmed)
* Copying HTTP/2 data in stream buffer to connection buffer after upgrade: len=52
* Connection state changed (MAX_CONCURRENT_STREAMS == 128)!
< HTTP/2 200 
< server: Jetty(11.0.12)
< date: Thu, 22 Dec 2022 03:11:19 GMT
< content-type: text/plain
< content-length: 11
< 
* Connection #0 to host 10.12.10.19 left intact
Hello World
```

Test with `curl` for fallback to http1: 
```
~ curl -v localhost:8080/ 
*   Trying localhost:8080...
* Connected to localhost (localhost) port 8080 (#0)
> GET / HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.74.0
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 OK
< Date: Thu, 22 Dec 2022 03:11:23 GMT
< Content-Type: text/plain
< Content-Length: 11
< 
* Connection #0 to host 1
```
