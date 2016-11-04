package com.infobarbosa.spark

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object BolsaFamiliaTotalCidadesOrdenado
{
	def parse(linha: String): ((String, String), Double) = {
		val campos = linha.split("\t")

		try 
		{ 
		  val uf = campos(0)
		  val cidade = campos(2)
		  val valor = campos(10).replace(",", "").toDouble

		  ((uf, cidade), valor)
		} 
		catch 
		{
		  case e: Exception => println(linha + " - " + e.getMessage())
		  (("", ""), 0)
		}
	}


	def main(args: Array[String])
	{
		//Define um objeto de configuracao
		val conf = new SparkConf().setAppName("Bolsa Familia Total Cidades Ordenado")

		//Define um contexto com o cluster
		val sc = new SparkContext( conf )

		//Abre o arquivo que queremos processar
		val linhas = sc.textFile( "/Users/barbosa/labs/bolsafamilia/201608_BolsaFamiliaFolhaPagamento.csv" )

		//Obtem um RDD contendo apenas estado, cidade e valor de pagamento
		val pagamentos = linhas.map( parse )

		//Agrega os pagamentos por estado e cidade
		val totais = pagamentos.reduceByKey((x, y) => x + y)

		val totaisOrdenados = totais.map( x => (x._2, x._1) ).sortByKey()

		for(total <- totaisOrdenados){
			val valor = total._1
			val key = total._2
			val e = key._1 //estado
			val c = key._2 //cidade

			println( e + " " + c + ": " + valor )
		}
	}
}