# Create efficient Reactive systems Reactor is a fourth-generation Reactive library for building non-blocking applications on
the JVM based on the Reactive Streams Specification

Multiple Modules are work together including Kafka Messaging

Asynchronous messaging technology called Apache Kafka.
#Start Zookeeper and Kafka Server - Use Bat File
## Create topic and partition
kafka-topics.bat  --zookeeper localhost:2181 --create --replication-factor 1 --partitions 1 --topic kafka_topic

## view topic
kafka-topics.sh --list --bootstrap-server localhost:9092

## start producer
kafka-console-producer.bat --broker-list localhost:9092 --topic kafka_topic

## start consumer
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic kafka_topic

Now you can start sending message from producers to consumers

#Reference :
0. https://kafka.apache.org/
1. https://dzone.com/articles/what-is-kafka
3. https://techbeacon.com/app-dev-testing/what-apache-kafka-why-it-so-popular-should-you-use-it