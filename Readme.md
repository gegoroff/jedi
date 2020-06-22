# JediApi

Api reativa utilizando Spring WebFlux

### DataBase

Cassandra 3.11

docker run -it --rm --name cassandra-node1 -v /tmp/cassandradata:/var/lib/cassandra -p7000:7000 -p7001:7001 -p9042:9042 -p9160:9160 cassandra:3.11

CREATE KEYSPACE jedi
  WITH REPLICATION = { 
   'class' : 'SimpleStrategy', 
   'replication_factor' : 1 
  };

CREATE TABLE jedi.planet (
	id varchar PRIMARY KEY,
	name varchar,
	climate varchar,
	terrain varchar,
	film_appearances int
)