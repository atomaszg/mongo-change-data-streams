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
  * easiest option
  * what is the cost ?
* Recurring scheduler checking new offers (how to scale)
* insert offer and job (offer inserted, job not inserted - how to recover)
  * return 500 to customer:
    * save offer.status with status = ACCEPTED
    * he creates offer again and system will update existing doc and trigger job to insert again
    * ignore such offers during read - ugly :(
  * return 200 to customer:
    * save offer.status with status = ACCEPTED
    * return offer to customer
    * recurring scheduler fixing offers without jobs (offer.status = PENDING AND job for offer is null) => create job
      * how long it takes for 1 offer (1 sec)
      * how long it takes for 100 rps (lag above 1/sec) 
      * maybe multiple schedulers working on some kind of rand shard key
* insert only job - until it will not execute user can do GET and we dont have offer - ugly :(
* save offer and job in single document
  * create job from stored document
* Use mongo CDS on 1 node (maybe separate service) and publish events on hermes -> consume from hermes and create job

### Links
* https://www.mongodb.com/blog/post/an-introduction-to-change-streams
* https://www.mongodb.com/docs/manual/changeStreams/
* https://debezium.io/documentation/reference/stable/connectors/mongodb.html
