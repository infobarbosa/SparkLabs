package com.infobarbosa.spark

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import com.datastax.spark.connector._

object BolsaFamiliaTotalCidadesOrdenado
{
	def parse(linha: String): (String, Double) = {
		val campos = linha.split("\t")

		try 
		{ 
		  val cidade = campos(0) + "|" + campos(2)
		  val valor = campos(10).replace(",", "").toDouble

		  (cidade, valor)
		} 
		catch 
		{
		  case e: Exception => println(linha + " - " + e.getMessage())
		  ("", 0)
		}
	}


	def main(args: Array[String])
	{
		println( "Parametro arquivo: " + args(0) )
		//Arquivo a ser lido
		val filename = args(0)

		//Define um objeto de configuracao
		val conf = new SparkConf( true )
			.setAppName("Bolsa Familia Total Cidades Ordenado")
			.set("spark.cassandra.connection.host", "192.168.56.111")

		//Define um contexto com o cluster
		val sc = new SparkContext( conf )

		println( "lendo o arquivo: " + filename )

		//Abre o arquivo que queremos processar
		val linhas = sc.textFile( filename )

		println( "numero de linhas: " + linhas.count() )
		
		//val linhas = sc.textFile("/Users/barbosa/labs/bolsafamilia/teste.csv")

		//Obtem um RDD contendo apenas estado, cidade e valor de pagamento
		//val pagamentos = linhas.map( parse )

		//Agrega os pagamentos por estado e cidade
		//val totais = pagamentos.reduceByKey((x, y) => x + y)

		//totais.saveToCassandra("bolsaf", "totais", SomeColumns("cidade", "total"))

	}
}
