# BigDataLabs
Laboratórios para testes de conceitos, técnicas e arquiteturas big data.

###Package
Como resultado será gerado um pacote como esse: target/scala-2.11/bolsa-familia-total-cidades-ordenado_2.11-1.0-SNAPSHOT.jar

    sbt clean package

###Uber Jar
Atentar para o arquivo assembly.sbt, debaixo do diretório ./project.
Como resultado será gerado um pacote como esse: target/scala-2.11/bolsa-familia-labs-assembly-1.0-SNAPSHOT.jar

    sbt assembly

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

	bolsa-familia-total-cidades-ordenado_2.11-1.0-SNAPSHOT.jar

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



