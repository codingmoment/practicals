## Steps

1. Install and start Elasticsearch
2. Install and start Kibana
3. Install Logstash
4. Copy the file logstash-tcp.conf into the 'config' directory
5. Start Logstash using command
   logstash -f ..\config\logstash-tcp.conf
6. Add loggers in your application (our application already has it)
7. Add dependency
   implementation 'net.logstash.logback:logstash-logback-encoder:6.2'
8. Add logback-spring.xml
9. Start the servers
10. Open Kibana http://localhost:5601
11. Discover tab
12. Management tab - Index Patterns
13. Visualize tab
14. Dashboard tab