# BigDataLabs
Laboratórios para testes de conceitos, técnicas e arquiteturas big data.

##Ambiente
O projeto assume um cluster Hadoop de 4 nodes, um cluster Spark de 4 nodes e um cluster Cassandra de 3 nodes.
Os clusters estão baseados em Ubuntu 14.04 LTS.
###Hadoop
Utilizada a versão 2.7.2 com JVM 1.7.0_79 instalada em 4 máquinas virtuais rodando em VirtualBox 5.0.28 com configuração de 2 núcleos de processamento e 1.5Gb de RAM cada.

###Spark
Instalado a versão spark-2.0.1-bin-hadoop2.7 nas máquinas do Hadoop.

###Cassandra
3 máquinas virtuais rodando em VirtualBox 5.0.28 com configuração de 2 núcleos de processamento e 1.5Gb de RAM cada.

Utilizada a versão 3.5 com JVM 1.8.0_77

A seguir estão os comandos de criação do keyspace do bolsa família e a tabela onde estão os dados brutos dos arquivos:

    CREATE KEYSPACE bolsaf WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 3};

    CREATE TABLE bolsaf.pagamentos(pagamento text, PRIMARY KEY((pagamento)));

###Rede
Seguem as configurações do arquivo /etc/hosts em todas as VMs

	192.168.56.100 hadoop-lab-ubuntu-master hadoop-master
	192.168.56.101 hadoop-lab-ubuntu-slave1 hadoop-slave1
	192.168.56.102 hadoop-lab-ubuntu-slave2 hadoop-slave2
	192.168.56.103 hadoop-lab-ubuntu-slave3 hadoop-slave3

	192.168.56.111 cassandra-lab-ubuntu-node1 cassandra-node1
	192.168.56.112 cassandra-lab-ubuntu-node2 cassandra-node2
	192.168.56.113 cassandra-lab-ubuntu-node3 cassandra-node3

O hostname em cada VM (/etc/hostname):

	192.168.56.100 => hadoop-lab-ubuntu-master 
	192.168.56.101 => hadoop-lab-ubuntu-slave1 
	192.168.56.102 => hadoop-lab-ubuntu-slave2 
	192.168.56.103 => hadoop-lab-ubuntu-slave3 

	192.168.56.111 => cassandra-lab-ubuntu-node1 
	192.168.56.112 => cassandra-lab-ubuntu-node2 
	192.168.56.113 => cassandra-lab-ubuntu-node3 

###Package

    sbt clean package

Como resultado será gerado um pacote como esse: target/scala-2.11/bolsa-familia-total-cidades-ordenado_2.11-1.0-SNAPSHOT.jar

###Uber Jar

    sbt assembly

Atentar para o arquivo assembly.sbt, debaixo do diretório ./project.

Como resultado será gerado um pacote como esse: target/scala-2.11/bolsa-familia-labs-assembly-1.0-SNAPSHOT.jar

###spark-submit

    spark-submit \
	  --master "spark://cassandra-lab-ubuntu-node1:7077" \
	  --class "com.infobarbosa.spark.BolsaFamiliaTotalCidadesOrdenado" \
	  target/scala-2.11/bolsa-familia-total-cidades-ordenado_2.11-1.0-SNAPSHOT.jar \
	  "file:///data/in/teste.csv"

	spark-submit \
	  --master "spark://cassandra-lab-ubuntu-node1:7077" \
	  --class "com.infobarbosa.spark.BolsaFamiliaTotalCidadesOrdenado" \
	  target/scala-2.11/bolsa-familia-total-cidades-ordenado_2.11-1.0-SNAPSHOT.jar \
	  "file:///Users/barbosa/labs/bolsafamilia/teste.csv"

	spark-submit \
	  --master "spark://cassandra-lab-ubuntu-node1:7077" \
	  --class "com.infobarbosa.spark.BolsaFamiliaTotalCidadesOrdenado" \
	  /home/spark/bolsa-familia-total-cidades-ordenado_2.11-1.0-SNAPSHOT.jar \
	  "file:///data/in/teste.csv"

	spark-submit \
	  --master spark://cassandra-lab-ubuntu-node1:7077 \
	  --class com.infobarbosa.spark.BolsaFamiliaTotalCidadesOrdenado \
	  target/scala-2.11/bolsa-familia-total-cidades-ordenado_2.11-1.0-SNAPSHOT.jar \
	  "file:///data/in/teste.csv"

	spark-submit \
	  --master spark://cassandra-lab-ubuntu-node1:7077 \
	  --class com.infobarbosa.spark.BolsaFamiliaTotalCidadesOrdenado \
	  target/scala-2.11/bolsa-familia-total-cidades-ordenado_2.11-1.0-SNAPSHOT.jar \
	  "file:///Users/barbosa/labs/bolsafamilia/teste.csv"

	spark-submit \
	  --master spark://hadoop-master:7077 \
	  --class com.infobarbosa.spark.BolsaFamiliaTotalCidadesOrdenado \
	  --deploy-mode cluster \
	  target/scala-2.11/bolsa-familia-total-cidades-ordenado_2.11-1.0-SNAPSHOT.jar \
	  "hdfs:///data/in/teste.csv"


	spark-submit \
	  --master spark://hadoop-master:7077 \
	  --class com.infobarbosa.spark.BolsaFamiliaTotalCidadesOrdenado \
	  target/scala-2.11/bolsa-familia-total-cidades-ordenado_2.11-1.0-SNAPSHOT.jar \
	  "hdfs://hadoop-master:9000/data/in/teste.csv"

	spark-submit \
	  --master yarn \
	  --deploy-mode cluster \
	  --class com.infobarbosa.spark.BolsaFamiliaTotalCidadesOrdenado \
	  target/scala-2.11/bolsa-familia-total-cidades-ordenado_2.11-1.0-SNAPSHOT.jar \
	  "hdfs://hadoop-master:9000/data/in/teste.csv"


	spark-submit \
	  --master spark://hadoop-master:7077 \
	  --class com.infobarbosa.spark.BolsaFamiliaCarregaCassandra \
	  /Users/barbosa/labs/spark/lab5/target/scala-2.11/bolsa-familia-total-cidades-ordenado_2.11-1.0-SNAPSHOT.jar \
	  "hdfs://hadoop-master:9000/data/in/teste.csv"


	spark-submit \
	  --master spark://hadoop-master:7077 \
	  --class com.infobarbosa.spark.BolsaFamiliaCarregaCassandra \
	  target/scala-2.11/bolsa-familia-labs-assembly-1.0-SNAPSHOT.jar \
	  "hdfs://hadoop-master:9000/data/in/teste.csv"

	target/scala-2.11/bolsa-familia-labs-assembly-1.0-SNAPSHOT.jar

	spark-submit \
	  --master spark://hadoop-master:7077 \
	  --class com.infobarbosa.spark.BolsaFamiliaCarregaCassandra \
	  --deploy-mode cluster \
	  /home/hadoop/bolsa-familia-total-cidades-ordenado_2.11-1.0-SNAPSHOT.jar \
	  "hdfs://hadoop-master:9000/data/in/teste.csv"

	spark-submit \
	  --master spark://hadoop-master:7077 \
	  --class com.infobarbosa.spark.BolsaFamiliaCarregaCassandra \
	  --deploy-mode cluster \
	  /Users/barbosa/labs/lab5/target/scala-2.11/bolsa-familia-total-cidades-ordenado_2.11-1.0-SNAPSHOT.jar \
	  "hdfs://hadoop-master:9000/data/in/teste.csv"

	spark-submit \
	  --master spark://hadoop-master:6066 \
	  --class com.infobarbosa.spark.BolsaFamiliaCarregaCassandra \
	  target/scala-2.11/bolsa-familia-total-cidades-ordenado_2.11-1.0-SNAPSHOT.jar \
	  "hdfs://hadoop-master:9000/data/in/teste.csv"


	spark-submit \
	  --master spark://hadoop-master:7077 \
	  --deploy-mode yarn-cluster \
	  --class com.infobarbosa.spark.BolsaFamiliaCarregaCassandra \
	  target/scala-2.11/bolsa-familia-total-cidades-ordenado_2.11-1.0-SNAPSHOT.jar \
	  "hdfs://hadoop-master:9000/data/in/teste.csv"

