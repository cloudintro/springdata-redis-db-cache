# springboot application with spring data and redis db cache
http://localhost:8081/swagger-ui/index.html

- Redis is an open-source, in-memory key-value remote data structure store, used as a database, cache, and message broker.
- Redis database is an in memory database that persists data on disk.
- Jedis and Lettuce are two known drivers to connect to Redis database.
- Key in redis is binary safe String with a max size of 512MB.
- Value can be of type String, Hash, Set, List, Sorted Set,  Streams, Bitmaps, HyperLogLogs
 
## Redis on windows 
https://github.com/microsoftarchive/redis/releases/download/win-3.2.100/Redis-x64-3.2.100.zip
- Extract to Redis-x64-3.2.100 folder and go inside it.
- Double-click on redis-server.exe for server
- Double-click on redis-cli.exe for client
> redis-server.exe redis.windows.conf
