#!/bin/bash

cluster_id_file="/tmp/cluster-id-dir/clusterId"
interval=3
while [ ! -e "$cluster_id_file_path" ] || [! -s "$cluster_id_file_path" ]; do
  echo "Waiting for cluster id to be created..."
  sleep $interval
done
echo "Cluster id: $(cat $cluster_id_file)"
echo -e "\nkafka-storage format --ignore-formatted -t $(cat $cluster_id_file) -c /kafka/config/kraft/server.properties" > /etc/confluent/docker/ensure