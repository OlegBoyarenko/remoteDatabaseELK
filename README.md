
## Delployment 
Run `docker-compose up` to start app in dev-mode. Navigate to `http://localhost:8080/`.
user: ben
password: benspassword

## Description
Access-server have connect to database, and send by RMI connection interface to clinet. 
Logstash dynamically put new entitys from database to elasticsearch.
Logstash use to search data, and save logs.
Kibana use to visualize your Elasticsearch data and navigate the Elastic Stack `http://localhost:5601/`.s