## Hey There! 
Thanks for trying this service with me!
### How to:
Please note that you have to be in ~/infrastructure/docker-compose folder.
* To start zookeeper before running kafka, use the command below:
``` docker-compose -f common.ymal -f zookeeper.yaml up ```

* To check if zookeeper is healthy:
``` echo ruok | nc localhost 2181```

  * Expect response like:
  ```imok```
* To run kafka cluster:
  * ``` docker-compose -f common.yaml -f kafka_cluster.yaml up ```
  * ``docker-compose -f common.yaml -f init_kafka.yaml up ``