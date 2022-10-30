# Mongodb change data streams in practise
### Requirements:
- only supported in replica sets

#### Create key to authenticate between mongo instances
```shell
openssl rand -base64 741 > m103-keyfile
```

### Other:
* Change streams duplicates listeners on each app-node
* Transactions are not supported by the MongoDB cluster to which this client is connected.

### Links
* https://www.mongodb.com/blog/post/an-introduction-to-change-streams
* https://www.mongodb.com/docs/manual/changeStreams/
