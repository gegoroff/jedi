FROM cassandra:3.11
ENV CASSANDRA_KEYSPACE jedi
COPY entrypoint-wrap.sh /entrypoint-wrap.sh
RUN chmod +x /entrypoint-wrap.sh
ENTRYPOINT ["/entrypoint-wrap.sh"]
CMD ["cassandra", "-f"]