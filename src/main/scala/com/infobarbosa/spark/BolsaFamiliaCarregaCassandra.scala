package com.infobarbosa.spark

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.sql._
import com.datastax.spark.connector._
//import org.apache.spark.sql.SparkSession

object BolsaFamiliaCarregaCassandra
{
	case class Pagamento(pagamento: String)

	def main(args: Array[String])
	{
		println( "Parametro arquivo: " + args(0) )
		//Arquivo a ser lido
		val filename = args(0)

		//Define um objeto de configuracao
		val conf = new SparkConf()
			.setAppName("Bolsa Familia Carrega Cassandra")

		//Define um contexto com o cluster
		val sc = new SparkContext( conf )

		println( "lendo o arquivo: " + filename )

		//Abre o arquivo que queremos processar
		val linhas = sc.textFile( filename )

		println( "numero de linhas: " + linhas.count() )


		//=======================================
		val spark = SparkSession
		  .builder()
		  .appName("Spark SQL Pagamentos")
		  .config("spark.cassandra.connection.host", "192.168.56.111")
		  //.config("spark.cassandra.connection.host", "192.168.56.112")
		  //.config("spark.cassandra.connection.host", "192.168.56.113")
		  .getOrCreate()
		
		//spark.conf.set("spark.cassandra.connection.host", "192.168.56.111")

		val linhasDF = spark.createDataFrame( 
			linhas.map(pagamento => Pagamento( pagamento )) 
		)

		linhasDF.collect.foreach( println )

		linhasDF.write
			.format("org.apache.spark.sql.cassandra")
  			.options(Map( "table" -> "pagamentos", "keyspace" -> "bolsaf"))
  			.save()

		println("saveToCassandra >>> OK");

	}
}
